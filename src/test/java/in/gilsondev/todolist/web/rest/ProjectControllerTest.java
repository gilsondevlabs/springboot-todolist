package in.gilsondev.todolist.web.rest;

import br.com.six2six.fixturefactory.Fixture;
import in.gilsondev.todolist.common.ControllerTestCase;
import in.gilsondev.todolist.common.builder.ProjectBuilder;
import in.gilsondev.todolist.domain.Project;
import in.gilsondev.todolist.exception.TodolistException;
import in.gilsondev.todolist.service.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectResource.class)
public class ProjectControllerTest extends ControllerTestCase<Project> {
    @MockBean
    private ProjectService projectService;

    @Before
    public void setup() {
        super.setup();
        new ProjectBuilder().buildFixtures();
    }

    @Test
    public void shouldReturnProjectList() throws Exception {
        Project project = Fixture.from(Project.class).gimme("valid");
        given(this.projectService.findAll()).willReturn(Collections.singletonList(project));

        this.mockMvc.perform(get("/project")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andDo(print());
    }

    @Test
    public void shouldReturnProjectDetail() throws Exception {
        Project project = Fixture.from(Project.class).gimme("valid");
        given(this.projectService.findById(any(Long.class))).willReturn(Optional.of(project));

        this.mockMvc.perform(get("/project/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.active").exists());
    }

    @Test
    public void shouldReturnInvalidProjectDetail() throws  Exception {
        given(this.projectService.findById(any(Long.class))).willThrow(EntityNotFoundException.class);

        this.mockMvc.perform(get("/project/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").exists())
                .andExpect(jsonPath("$.message").isEmpty());
    }

    @Test
    public void shouldPersistProject() throws Exception {
        Project project = Fixture.from(Project.class).gimme("validWithoutID");
        Project projectWithId = Fixture.from(Project.class).gimme("valid");

        given(projectService.save(any(Project.class))).willReturn(projectWithId);

        this.mockMvc.perform(post("/project/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.json.write(project).getJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void shouldUpdateProject() throws Exception {
        Project projectWithId = Fixture.from(Project.class).gimme("valid");

        given(projectService.save(any(Project.class))).willReturn(projectWithId);

        this.mockMvc.perform(put("/project/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.json.write(projectWithId).getJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteProject() throws Exception {
        Project projectWithId = Fixture.from(Project.class).gimme("valid");

        this.mockMvc.perform(delete("/project/" + projectWithId.getId()))
                .andExpect(status().isNoContent());

        verify(projectService, times(1)).delete(projectWithId.getId());
    }
}
