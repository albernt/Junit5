import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @Test
    void testValidacionDni() {
        String dniValido = "12345678A";
        assertTrue(dniValido.matches("\\d{8}[A-Za-z]"));

        String dniInvalido = "12345678";
        assertFalse(dniInvalido.matches("\\d{8}[A-Za-z]"));
    }
}
