package in.gilsondev.todolist.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe POJO que representa
 * o corpo do erro a ser retornado
 * para os clientes.
 *
 * @author Gilson Filho
 */
@Getter
@Setter
public class ErrorMessage {
    private Integer errorCode;
    private String message;
}
