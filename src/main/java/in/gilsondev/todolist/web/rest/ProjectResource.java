package in.gilsondev.todolist.web.rest;

import in.gilsondev.todolist.domain.Project;
import in.gilsondev.todolist.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectResource {

    final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id) {
        Optional<Project> project = projectService.findById(id);

        if(project.isPresent()) {
            return ResponseEntity.ok(project.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
