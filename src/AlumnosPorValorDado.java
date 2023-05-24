import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlumnosPorValorDado {
    public static void alumnosPorValorDado() {
        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/daw1", "root", "");

            // Obtener el valor de intervenciones ingresado por consola
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el número de intervenciones:");
            int valorIntervenciones = scanner.nextInt();

            // Obtener los alumnos con intervenciones superiores, iguales o inferiores al valor ingresado
            String selectQuery = "SELECT nombre, intervenciones FROM alumno WHERE intervenciones >= ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, valorIntervenciones);
            ResultSet resultSet = selectStatement.executeQuery();

            List<String> alumnosPorIntervenciones = new ArrayList<>();
            while (resultSet.next()) {
                String nombreAlumno = resultSet.getString("nombre");
                int numIntervenciones = resultSet.getInt("intervenciones");
                alumnosPorIntervenciones.add(nombreAlumno + " - Intervenciones: " + numIntervenciones);
            }

            if (alumnosPorIntervenciones.isEmpty()) {
                System.out.println("No hay alumnos con ese número de intervenciones o más.");
            } else {
                System.out.println("Alumnos con número de intervenciones igual o superior a " + valorIntervenciones + ":");
                for (String alumno : alumnosPorIntervenciones) {
                    System.out.println(alumno);
                }
            }

            // Obtener los alumnos con intervenciones inferiores al valor ingresado
            String selectLessQuery = "SELECT nombre, intervenciones FROM alumno WHERE intervenciones < ?";
            PreparedStatement selectLessStatement = conn.prepareStatement(selectLessQuery);
            selectLessStatement.setInt(1, valorIntervenciones);
            ResultSet resultSetLess = selectLessStatement.executeQuery();

            List<String> alumnosMenosIntervenciones = new ArrayList<>();
            while (resultSetLess.next()) {
                String nombreAlumno = resultSetLess.getString("nombre");
                int numIntervenciones = resultSetLess.getInt("intervenciones");
                alumnosMenosIntervenciones.add(nombreAlumno + " - Intervenciones: " + numIntervenciones);
            }

            if (alumnosMenosIntervenciones.isEmpty()) {
                System.out.println("No hay alumnos con menos intervenciones que " + valorIntervenciones + ".");
            } else {
                System.out.println("Alumnos con número de intervenciones inferior a " + valorIntervenciones + ":");
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

