import com.fasterxml.jackson.databind.ObjectMapper;
import model.Desctop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ParsingJsonTest {
    ClassLoader cl = ParsingJsonTest.class.getClassLoader();

    @Test
    void parsingJsonTest() throws Exception {
        ObjectMapper om = new ObjectMapper();
        try (InputStream is = cl.getResourceAsStream("TestFile/Desctop.json");
             InputStreamReader isr = new InputStreamReader(is)) {
            Desctop des = om.readValue(isr, Desctop.class);
            Assertions.assertEquals("MSI z690", des.computerCase.motherboard);
            Assertions.assertEquals("Intel i5", des.computerCase.cpu);
            Assertions.assertEquals(32, des.computerCase.ram);
            Assertions.assertEquals("rtx 4070ti", des.computerCase.gpu);
            Assertions.assertEquals("DeepCool", des.computerCase.cpuCooling);
            Assertions.assertEquals("Cooler1", des.computerCase.caseCooling.get(0));
            Assertions.assertEquals("Cooler2", des.computerCase.caseCooling.get(1));
            Assertions.assertEquals("Cooler3", des.computerCase.caseCooling.get(2));
        }
    }
}
