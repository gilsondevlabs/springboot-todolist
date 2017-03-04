package in.gilsondev.todolist.web.rest;

import in.gilsondev.todolist.domain.Project;
import in.gilsondev.todolist.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectResource {

    public static final String ID_PROJECT = "{id}";

    private static final String ID_PROJECT_VARIABLE = "id";

    final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping(path = "/" + ID_PROJECT)
    public ResponseEntity<Project> getProject(@PathVariable(ID_PROJECT_VARIABLE) Long id) {
        Optional<Project> project = projectService.findById(id);

        if(project.isPresent()) {
            return ResponseEntity.ok(project.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.save(project));
    }

    @PutMapping
    public ResponseEntity<?> updateProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.save(project));
    }

    @DeleteMapping(path = "/" + ID_PROJECT)
    public ResponseEntity<?> removeProject(@PathVariable(ID_PROJECT_VARIABLE) Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
