package in.gilsondev.todolist.common.builder;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import in.gilsondev.todolist.domain.Project;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

// TODO Refatorar classe ProjectBuilder
@Component
public class ProjectBuilder {

    private final DataFactory factory = new DataFactory();

    private void createValidProject() {
        Fixture.of(Project.class).addTemplate("valid", new Rule() {{
          add("id", random(Long.class, range(1L, 200L)));
          add("name", "Project");
          add("active", true);
        }});

        Fixture.of(Project.class).addTemplate("validWithRandomName", new Rule() {{
            add("id", random(Long.class, range(1L, 200L)));
            add("name", "Project");
            add("active", random(true, false));
        }});

        Fixture.of(Project.class).addTemplate("validWithoutID", new Rule() {{
            add("id", null);
            add("name", random("Project", "Project Test", factory.getRandomChars(4, 30)));
            add("active", true);
        }});

    }

    private void createInvalidProject() {
        Fixture.of(Project.class).addTemplate("withoutName").inherits("validWithoutID", new Rule(){{
            add("name", null);
        }});

        Fixture.of(Project.class).addTemplate("nameWithMore80Characters").inherits("validWithoutID", new Rule(){{
            add("name", factory.getRandomChars(81, 100));
        }});
    }

    public void buildFixtures() {
        this.createValidProject();
        this.createInvalidProject();
    }
}
