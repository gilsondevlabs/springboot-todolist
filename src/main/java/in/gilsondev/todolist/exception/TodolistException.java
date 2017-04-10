package in.gilsondev.todolist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Classe de exception especializada nos erros
 * negociais do projeto.
 *
 * @author Gilson Filho
 */
@Getter
public class TodolistException extends RuntimeException {
    private String message;

    public TodolistException(String message) {
        super(message);
        this.message = message;
    }

}
