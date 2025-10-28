import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Datos de conexión (ajusta según tu caso)
    private static final String URL = "jdbc:mysql://localhost:3306/mediateca";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método que retorna la conexión
    public static Connection getConexion() {
        Connection conn = null;
        try {
            // Cargar el driver (ya incluido en tu .jar)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crear la conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ Error SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("❌ No se encontró el Driver JDBC");
        }
        return conn;
    }
}
