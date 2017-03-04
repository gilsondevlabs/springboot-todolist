package in.gilsondev.todolist.domain;

import br.com.six2six.fixturefactory.Fixture;
import in.gilsondev.todolist.common.assertions.ProjectAssert;
import in.gilsondev.todolist.common.builder.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProjectEntityTest {

    @Before
    public void setup() {
        new ProjectBuilder().buildFixtures();
    }

    @Test
    public void shouldHaveLombokProperties() {
        Project project = Fixture.from(Project.class).gimme("valid");

        ProjectAssert.assertThat(project).isValid();
    }
}
