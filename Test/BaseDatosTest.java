import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

public class BaseDatosTest {
    private Connection conexion;

    @BeforeEach
    void setUp() throws SQLException {
        conexion = DriverManager.getConnection("jdbc:sqlite:usuarios.db");

        Statement stmt = conexion.createStatement();
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dni TEXT NOT NULL UNIQUE)";
        String sqlIngresos = "CREATE TABLE IF NOT EXISTS ingresos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dni TEXT NOT NULL, " +
                "concepto TEXT NOT NULL, " +
                "cantidad REAL NOT NULL, " +
                "FOREIGN KEY (dni) REFERENCES usuarios(dni))";
        String sqlGastos = "CREATE TABLE IF NOT EXISTS gastos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dni TEXT NOT NULL, " +
                "concepto TEXT NOT NULL, " +
                "cantidad REAL NOT NULL, " +
                "FOREIGN KEY (dni) REFERENCES usuarios(dni))";

        stmt.executeUpdate(sqlUsuarios);
        stmt.executeUpdate(sqlIngresos);
        stmt.executeUpdate(sqlGastos);

        stmt.executeUpdate("DELETE FROM usuarios");
        stmt.executeUpdate("DELETE FROM ingresos");
        stmt.executeUpdate("DELETE FROM gastos");
    }

    @AfterEach
    void tearDown() throws SQLException {
        Statement stmt = conexion.createStatement();
        stmt.executeUpdate("DELETE FROM usuarios");
        stmt.executeUpdate("DELETE FROM ingresos");
        stmt.executeUpdate("DELETE FROM gastos");
    }

    @Test
    void testRegistrarUsuarioYIngreso() {
        String dni = "12345678A";
        String nombre = "Juan Pérez";
        double ingreso = 1500.0;

        boolean registrado = registrarUsuario(dni);

        assertTrue(registrado, "El usuario no se ha registrado correctamente.");

        registrarIngreso(dni, "Sueldo", ingreso);

        double ingresosActuales = cargarIngresos(dni);
        assertEquals(ingreso, ingresosActuales, "El ingreso registrado no coincide con el valor esperado.");
    }

    public boolean registrarUsuario(String dni) {
        if (autenticarUsuario(dni)) {
            return false;
        }
        String sql = "INSERT INTO usuarios (dni) VALUES (?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean autenticarUsuario(String dni) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE dni = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public void registrarIngreso(String dni, String concepto, double cantidad) {
        String sql = "INSERT INTO ingresos (dni, concepto, cantidad) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.setString(2, concepto);
            stmt.setDouble(3, cantidad);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public double cargarIngresos(String dni) {
        String sql = "SELECT SUM(cantidad) FROM ingresos WHERE dni = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0;
        } catch (SQLException e) {
            return 0;
        }
    }
}
