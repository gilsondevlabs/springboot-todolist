package in.gilsondev.todolist.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.boot.test.json.JacksonTester;

public class ControllerTestCase<T> {
    protected JacksonTester<T> json;

    @Before
    public void setup() {
        ObjectMapper objectMappper = new ObjectMapper();
        objectMappper.findAndRegisterModules();
        JacksonTester.initFields(this, objectMappper);
    }
}
