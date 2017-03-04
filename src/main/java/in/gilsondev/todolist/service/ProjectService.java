package in.gilsondev.todolist.service;

import in.gilsondev.todolist.domain.Project;
import in.gilsondev.todolist.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> findById(Long id) {
        return Optional.of(projectRepository.findOne(id));
    }

    // TODO Create test this
    public void delete(Long id) {
        projectRepository.delete(id);
    }
}
