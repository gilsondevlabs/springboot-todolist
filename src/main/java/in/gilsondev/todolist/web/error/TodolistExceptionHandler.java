package in.gilsondev.todolist.web.error;

import in.gilsondev.todolist.exception.ErrorMessage;
import in.gilsondev.todolist.exception.TodolistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 * Classe responsável por tratar as mensagens de erro
 * para os controllers do projeto
 *
 * @author Gilson Filho
 */
@ControllerAdvice
public class TodolistExceptionHandler {

    @ExceptionHandler(TodolistException.class)
    public ResponseEntity<ErrorMessage> exceptionTodoListException(RuntimeException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setMessage(exception.getMessage());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionTodoListException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setMessage("Ocorreu um erro inesperado no servidor.");
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> exceptionEntityNotFoundException(EntityNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setMessage(exception.getMessage());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
