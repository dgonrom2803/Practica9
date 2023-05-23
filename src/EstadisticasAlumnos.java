import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EstadisticasAlumnos {
    public static void estadisticasAlumnos() {
        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/daw1", "root", "");

            // Obtener el alumno con más intervenciones
            String maxQuery = "SELECT nombre, intervenciones FROM alumno ORDER BY intervenciones DESC LIMIT 1";
            PreparedStatement maxStatement = conn.prepareStatement(maxQuery);
            ResultSet maxResult = maxStatement.executeQuery();

            if (maxResult.next()) {
                String alumnoMax = maxResult.getString("nombre");
                int intervencionesMax = maxResult.getInt("intervenciones");
                System.out.println("El alumno con más intervenciones es: " + alumnoMax);
                System.out.println("Número de intervenciones: " + intervencionesMax);
            }

            // Obtener el alumno con menos intervenciones
            String minQuery = "SELECT nombre, intervenciones FROM alumno ORDER BY intervenciones ASC LIMIT 1";
            PreparedStatement minStatement = conn.prepareStatement(minQuery);
            ResultSet minResult = minStatement.executeQuery();

            if (minResult.next()) {
                String alumnoMin = minResult.getString("nombre");
                int intervencionesMin = minResult.getInt("intervenciones");
                System.out.println("El alumno con menos intervenciones es: " + alumnoMin);
                System.out.println("Número de intervenciones: " + intervencionesMin);
            }

            // Cerrar la conexión a la base de datos
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
