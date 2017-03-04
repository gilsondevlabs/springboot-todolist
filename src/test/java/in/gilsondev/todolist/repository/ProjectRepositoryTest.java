package in.gilsondev.todolist.repository;

import br.com.six2six.fixturefactory.Fixture;
import in.gilsondev.todolist.common.builder.ProjectBuilder;
import in.gilsondev.todolist.domain.Project;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Before
    public void setup() {
        new ProjectBuilder().buildFixtures();
    }

    @Test
    public void shouldPersistProject() {
        Project project = Fixture.from(Project.class).gimme("valid");

        projectRepository.save(project);
    }
}