import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        //inicializamos usuario antes de cada prueba
        usuario = new Usuario("98765432B");
    }

    @Test
    void testDni() {
        //Testeamos estructura de DNI
        assertEquals("98765432B", usuario.getDni());
        assertNull(usuario.getNombre());
        assertNull(usuario.getCorreo());
    }

    @Test
    void testConstructorCompleto() {

        Usuario usuarioExtendido = new Usuario("98765432B", "Maria Lopez", "maria@example.com");
        assertEquals("98765432B", usuarioExtendido.getDni());
        assertEquals("Maria Lopez", usuarioExtendido.getNombre());
        assertEquals("maria@example.com", usuarioExtendido.getCorreo());
    }

    @Test
    void testSetters() {
        usuario.setNombre("Pedro Garcia");
        usuario.setCorreo("pedro@example.com");


        assertEquals("Pedro Garcia", usuario.getNombre());
        assertEquals("pedro@example.com", usuario.getCorreo());
    }

    @Test
    void testToString() {
        Usuario usuarioToString = new Usuario("98765432B", "Laura Ruiz", "laura@example.com");
        String expected = "Usuario{dni='98765432B', nombre='Laura Ruiz', correo='laura@example.com'}";


        assertEquals(expected, usuarioToString.toString());
    }

    @Test
    void testSettersNull() {
        //Testeamos valores null en campos de usuario
        usuario.setNombre(null);
        usuario.setCorreo(null);

        assertNull(usuario.getNombre());
        assertNull(usuario.getCorreo());
    }
}
