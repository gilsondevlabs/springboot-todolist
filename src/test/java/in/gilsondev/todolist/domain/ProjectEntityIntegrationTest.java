package in.gilsondev.todolist.domain;

import br.com.six2six.fixturefactory.Fixture;
import in.gilsondev.todolist.common.assertions.ProjectAssert;
import in.gilsondev.todolist.common.builder.ProjectBuilder;
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

import javax.persistence.PersistenceException;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ProjectEntityIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        new ProjectBuilder().buildFixtures();
    }

    @Test
    public void shouldPersist() {
        Project project = Fixture.from(Project.class).gimme("validWithoutID");
        Project projectPersisted = entityManager.persistFlushFind(project);

        ProjectAssert.assertThat(projectPersisted).isValid();
    }

    @Test
    public void shouldNotPersistWithoutName() {
        Project project = Fixture.from(Project.class).gimme("withoutName");

        exception.expect(PersistenceException.class);
        entityManager.persistFlushFind(project);
    }

    @Test
    public void shouldNotPersistNameWithMore80Characters() {
        Project project = Fixture.from(Project.class).gimme("nameWithMore80Characters");

        exception.expect(PersistenceException.class);
        entityManager.persistFlushFind(project);
    }
}
