package in.gilsondev.todolist.common.builder;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import in.gilsondev.todolist.domain.Project;
import in.gilsondev.todolist.domain.Task;
import lombok.RequiredArgsConstructor;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskBuilder {
    private final DataFactory factory = new DataFactory();

    private ProjectBuilder projectBuilder;


    public TaskBuilder() {
        this.projectBuilder = new ProjectBuilder();
        this.projectBuilder.buildFixtures();
    }

    private static void createValidTask() {

        Fixture.of(Project.class).addTemplate("projectValidWithoutID", new Rule() {{
            add("id", null);
            add("name", "Project");
            add("active", true);
        }});

        Project project = Fixture.from(Project.class).gimme("projectValidWithoutID");

        Fixture.of(Task.class).addTemplate(Templates.VALID.getTemplateName(), new Rule() {{
            add("id", random(Long.class, range(1L, 200L)));
            add("project", project);
            add("description", "Task description");
            add("createdAt", LocalDateTime.now());
            add("completed", random(true, false));
        }});

        Fixture.of(Task.class).addTemplate(Templates.VALID_WITHOUT_ID.getTemplateName(), new Rule() {{
            add("id", null);
            add("project", project);
            add("description", "Task description");
            add("createdAt", LocalDateTime.now());
            add("completed", random(true, false));
        }});
    }

    public static void buildFixtures() {
        createValidTask();
    }

    public static Task build(Templates templates) {
        return Fixture.from(Task.class).gimme(templates.getTemplateName());
    }

    public enum Templates {
        VALID("taskValid"),
        VALID_WITHOUT_ID("taskValidWithoutID");

        Templates(String templateName) {
            this.templateName = templateName;
        }

        private String templateName;

        public String getTemplateName() {
            return templateName;
        }
    }

}
