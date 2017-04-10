package in.gilsondev.todolist.domain;

import br.com.six2six.fixturefactory.Fixture;
import in.gilsondev.todolist.common.assertions.TaskAssert;
import in.gilsondev.todolist.common.builder.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
public class TaskEntityTest {

    @Before
    public void setup() {
        new ProjectBuilder().buildFixtures();
    }

    @Test
    public void shouldHaveValidTask() {
        Project project = Fixture.from(Project.class).gimme("valid");

        Task task = new Task();
        task.setId(1L);
        task.setProject(project);
        task.setDescription("Task Name");
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(true);

        TaskAssert.assertThat(task).isValid();
    }
}
