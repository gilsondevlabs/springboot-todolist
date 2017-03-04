package in.gilsondev.todolist.repository;

import in.gilsondev.todolist.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
