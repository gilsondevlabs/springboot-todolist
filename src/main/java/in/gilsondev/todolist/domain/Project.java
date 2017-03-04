package in.gilsondev.todolist.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entidade que representa os Projetos
 * no sistema de tarefas
 *
 * @author Gilson Filho
 */
@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    private boolean active;
}
