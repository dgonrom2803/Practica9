import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBasedeDatos {

    public static void creaBaseDeDatos() {
        String url = "jdbc:mariadb://localhost:3306/";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            crearBaseDatos(connection);
            usarBaseDatos(connection);
            crearTabla(connection);
            System.out.println("La base de datos y la tabla se han creado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void crearBaseDatos(Connection connection) throws SQLException {
        String sql = "CREATE DATABASE daw1";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private static void usarBaseDatos(Connection connection) throws SQLException {
        String sql = "USE daw1";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private static void crearTabla(Connection connection) throws SQLException {
        String sql = "CREATE TABLE alumno (nombre VARCHAR(30) NULL DEFAULT NULL, intervenciones INT NULL DEFAULT NULL, fecha_intervencion DATE DEFAULT CURRENT_TIMESTAMP)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
}