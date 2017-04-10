package in.gilsondev.todolist.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;

public class ControllerTestCase<T> {
    protected JacksonTester<T> json;

    @Autowired
    protected MockMvc mockMvc;

    @Before
    public void setup() {
        ObjectMapper objectMappper = new ObjectMapper();
        objectMappper.findAndRegisterModules();
        JacksonTester.initFields(this, objectMappper);
    }
}
