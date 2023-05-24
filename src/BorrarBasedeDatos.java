import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BorrarBasedeDatos {
    public static void borraBaseDeDatos() {
        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/", "root", "");

            // Crear una sentencia SQL para borrar la base de datos
            String dropDatabaseQuery = "DROP DATABASE daw1";

            // Crear un objeto Statement y ejecutar la sentencia SQL
            Statement statement = conn.createStatement();
            statement.executeUpdate(dropDatabaseQuery);

            System.out.println("La base de datos 'daw1' ha sido borrada exitosamente.");

            // Cerrar la conexión a la base de datos
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
