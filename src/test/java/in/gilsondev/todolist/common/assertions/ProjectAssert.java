package in.gilsondev.todolist.common.assertions;

import in.gilsondev.todolist.domain.Project;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

/**
 * Custom assertion to Project entity
 */
public class ProjectAssert extends AbstractAssert<ProjectAssert, Project> {

    public ProjectAssert(Project actual) {
        super(actual, ProjectAssert.class);
    }

    public static ProjectAssert assertThat(Project actual) {
        return new ProjectAssert(actual);
    }

    public ProjectAssert isValid() {
        isNotNull();

        Assertions.assertThat(actual.getId()).isNotNull();
        Assertions.assertThat(actual.getName()).isNotNull();
        Assertions.assertThat(actual.getName().length()).isLessThanOrEqualTo(80);

        return this;
    }

    public ProjectAssert hasId(Long id) {
        isNotNull();
        Assertions.assertThat(actual.getId()).isEqualTo(id);

        return this;
    }

    public ProjectAssert hasId() {
        isNotNull();
        Assertions.assertThat(actual.getId()).isNotNull();

        return this;
    }

    public ProjectAssert hasName(String name) {
        isNotNull();
        Assertions.assertThat(actual.getName()).isEqualTo(name);

        return this;
    }

    public ProjectAssert isActive() {
        isNotNull();
        Assertions.assertThat(actual.isActive()).isTrue();

        return this;
    }

    public ProjectAssert isInative() {
        isNotNull();
        Assertions.assertThat(actual.isActive()).isFalse();

        return this;
    }
}
