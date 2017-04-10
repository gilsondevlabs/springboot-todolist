package in.gilsondev.todolist.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidade que representa
 * as tarefas do sistema
 *
 * @author Gilson Filho
 */
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Project project;

    private String description;

    private LocalDateTime createdAt;

    private boolean completed;

}
