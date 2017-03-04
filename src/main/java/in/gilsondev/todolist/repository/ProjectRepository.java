package in.gilsondev.todolist.repository;

import in.gilsondev.todolist.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository de Projetos
 *
 * @author Gilson Filho
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
