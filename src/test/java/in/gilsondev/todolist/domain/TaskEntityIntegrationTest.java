package in.gilsondev.todolist.domain;

import br.com.six2six.fixturefactory.Fixture;
import in.gilsondev.todolist.common.assertions.TaskAssert;
import in.gilsondev.todolist.common.builder.TaskBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Classe de teste de integração feito
 * com a entidade de tarefas do sistema
 *
 * @author Gilson Filho
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class TaskEntityIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        TaskBuilder.buildFixtures();
    }

    @Test
    public void shouldPersist() {
        Task task = TaskBuilder.build(TaskBuilder.Templates.VALID_WITHOUT_ID);
        Project project = entityManager.persistFlushFind(task.getProject());
        task.setProject(project);
        Task taskPersisted = entityManager.persistFlushFind(task);

        TaskAssert.assertThat(taskPersisted).isValid();
    }
}
