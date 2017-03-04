package in.gilsondev.todolist.service;

import br.com.six2six.fixturefactory.Fixture;
import in.gilsondev.todolist.common.assertions.ProjectAssert;
import in.gilsondev.todolist.common.builder.ProjectBuilder;
import in.gilsondev.todolist.domain.Project;
import in.gilsondev.todolist.repository.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
public class ProjectServiceTest {
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Before
    public void setup() {
        new ProjectBuilder().buildFixtures();
    }

    @Test
    public void shouldListProjects() {
        Project project = Fixture.from(Project.class).gimme("valid");

        given(projectRepository.findAll()).willReturn(Collections.singletonList(project));

        assertThat(projectService.findAll()).isNotEmpty();
    }

    @Test
    public void shouldPersistProject() {
        Project project = Fixture.from(Project.class).gimme("validWithoutID");
        Project projectWithID = Fixture.from(Project.class).gimme("valid");

        given(projectRepository.save(any(Project.class))).willReturn(projectWithID);

        Project projectPersisted = projectService.save(project);

        ProjectAssert.assertThat(projectPersisted).isValid();
    }

    @Test
    public void shouldFindProjectById() {
        Project projectWithID = Fixture.from(Project.class).gimme("valid");

        given(projectRepository.findOne(any(Long.class))).willReturn(projectWithID);

        Optional<Project> project = projectService.findById(1L);

        ProjectAssert.assertThat(project.get()).isValid();
    }
}
