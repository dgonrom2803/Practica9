import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class InformacionAlumno {
    public static void informacionAlumno() {
        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/daw1", "root", "");

            // Obtener el nombre del alumno ingresado por consola
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el nombre del alumno:");
            String nombreAlumno = scanner.nextLine();

            // Obtener la información del alumno
            String selectQuery = "SELECT * FROM alumno WHERE nombre = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setString(1, nombreAlumno);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int intervenciones = resultSet.getInt("intervenciones");
                String fechaIntervencion = resultSet.getString("fecha_intervencion");

                System.out.println("Información del alumno:");
                System.out.println("Nombre: " + nombreAlumno);
                System.out.println("Intervenciones: " + intervenciones);
                System.out.println("Fecha de intervención: " + fechaIntervencion);
            } else {
                System.out.println("No se encontró información para el alumno ingresado.");
            }

            // Cerrar la conexión a la base de datos
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

