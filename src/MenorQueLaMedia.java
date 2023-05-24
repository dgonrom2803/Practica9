import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenorQueLaMedia {
    public static void menorQueLaMedia() {
        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/daw1", "root", "");

            // Obtener la media de intervenciones
            String avgQuery = "SELECT AVG(intervenciones) AS media FROM alumno";
            PreparedStatement avgStatement = conn.prepareStatement(avgQuery);
            ResultSet avgResult = avgStatement.executeQuery();

            double media = 0;
            if (avgResult.next()) {
                media = avgResult.getDouble("media");
            }

            // Obtener los alumnos cuyas intervenciones son menos que la media
            String selectQuery = "SELECT nombre, intervenciones FROM alumno WHERE intervenciones < ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setDouble(1, media);
            ResultSet resultSet = selectStatement.executeQuery();

            List<String> alumnosMenosIntervenciones = new ArrayList<>();
            while (resultSet.next()) {
                String nombreAlumno = resultSet.getString("nombre");
                int numIntervenciones = resultSet.getInt("intervenciones");
                alumnosMenosIntervenciones.add(nombreAlumno + " - Intervenciones: " + numIntervenciones);
            }

            if (alumnosMenosIntervenciones.isEmpty()) {
                System.out.println("No hay alumnos con menos intervenciones que la media.");
            } else {
                System.out.println("Alumnos con menos intervenciones que la media:");
                for (String alumno : alumnosMenosIntervenciones) {
                    System.out.println(alumno);
                }
            }

            // Cerrar la conexión a la base de datos
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}