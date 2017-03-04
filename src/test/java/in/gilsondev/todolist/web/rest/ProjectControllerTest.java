package in.gilsondev.todolist.web.rest;

import br.com.six2six.fixturefactory.Fixture;
import in.gilsondev.todolist.common.ControllerTestCase;
import in.gilsondev.todolist.common.assertions.ProjectAssert;
import in.gilsondev.todolist.common.builder.ProjectBuilder;
import in.gilsondev.todolist.domain.Project;
import in.gilsondev.todolist.service.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest extends ControllerTestCase<Project> {
    @MockBean
    private ProjectService projectService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        super.setup();
        new ProjectBuilder().buildFixtures();
    }

    @Test
    public void shouldReturnStatus200InProjectList() {
        Project project = Fixture.from(Project.class).gimme("valid");
        given(this.projectService.findAll()).willReturn(Collections.singletonList(project));

        ResponseEntity<String> response = this.restTemplate.getForEntity("/project", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnProjectList() throws IOException {
        Project project = Fixture.from(Project.class).gimme("valid");
        given(this.projectService.findAll()).willReturn(Collections.singletonList(project));

        ResponseEntity<String> response = this.restTemplate.getForEntity("/project", String.class);
        assertThat(this.json.parseObject(response.getBody())).isNotNull();
    }

    @Test
    public void shouldReturnStatus200InProjectDetail() {
        Project project = Fixture.from(Project.class).gimme("valid");
        given(this.projectService.findById(any(Long.class))).willReturn(Optional.of(project));

        ResponseEntity<String> response = this.restTemplate.getForEntity("/project/{id}", String.class, 1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnProject() {
        Project project = Fixture.from(Project.class).gimme("valid");
        given(this.projectService.findById(any(Long.class))).willReturn(Optional.of(project));

        ResponseEntity<Project> response = this.restTemplate.getForEntity("/project/{id}", Project.class, 1L);
        ProjectAssert.assertThat(response.getBody()).isValid();
    }

    // TODO Test persist project

    // TODO Test update project

    // TODO Test delete project
}
