package in.gilsondev.todolist.common.assertions;

import in.gilsondev.todolist.domain.Task;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.time.LocalDateTime;

/**
 * Asserção customizada para os testes relacionados
 * a tarefas.
 *
 * @author Gilson Filho
 */
public class TaskAssert extends AbstractAssert<TaskAssert, Task> {
    public TaskAssert(Task actual) {
        super(actual, TaskAssert.class);
    }

    public static TaskAssert assertThat(Task actual) {
        return new TaskAssert(actual);
    }

    public TaskAssert isValid() {
        isNotNull();

        Assertions.assertThat(actual.getId()).isNotNull();
        Assertions.assertThat(actual.getId()).isNotNegative();
        Assertions.assertThat(actual.getDescription()).isNotNull();
        Assertions.assertThat(actual.getDescription().length()).isLessThanOrEqualTo(255);
        Assertions.assertThat(actual.getCreatedAt()).isNotNull();

        return this;
    }

    public TaskAssert hasId(Long id) {
        isNotNull();
        Assertions.assertThat(id).isNotNull();
        Assertions.assertThat(id).isNotNegative();

        return this;
    }

    public TaskAssert hasDescription(String description) {
        isNotNull();

        Assertions.assertThat(actual.getDescription()).isNotNull();
        Assertions.assertThat(actual.getDescription().length()).isLessThanOrEqualTo(255);

        return this;
    }

    public TaskAssert hasCreatedAt(LocalDateTime createdAt) {
        isNotNull();

        Assertions.assertThat(actual.getCreatedAt()).isNotNull();


        return this;
    }

    public TaskAssert isCompleted(boolean taskCompleted) {
       isNotNull();

       Assertions.assertThat(taskCompleted).isTrue();

       return this;
    }

    public TaskAssert isNew(boolean taskCompleted) {
        isNotNull();

        Assertions.assertThat(taskCompleted).isFalse();

        return this;
    }
}
