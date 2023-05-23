import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SeleccionAlumnoAleatorio {
    public static void seleccionAlumno() {
        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/daw1", "root", "");

            // Obtener los datos de la tabla 'alumno' de la base de datos
            String selectQuery = "SELECT nombre, intervenciones FROM alumno";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();

            // Crear listas para almacenar los nombres e intervenciones de los alumnos
            List<String> nombres = new ArrayList<>();
            List<Integer> intervenciones = new ArrayList<>();

            // Obtener los datos de la base de datos y almacenarlos en las listas correspondientes
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int numIntervenciones = resultSet.getInt("intervenciones");
                nombres.add(nombre);
                intervenciones.add(numIntervenciones);
            }

            // Buscar el alumno con menos intervenciones
            int menosIntervenciones = Integer.MAX_VALUE;
            for (int intervencion : intervenciones) {
                if (intervencion < menosIntervenciones) {
                    menosIntervenciones = intervencion;
                }
            }

            // Crear una lista para almacenar las posiciones de los alumnos con menos intervenciones
            List<Integer> posicionesAlumno = new ArrayList<>();
            for (int i = 0; i < intervenciones.size(); i++) {
                if (intervenciones.get(i) == menosIntervenciones) {
                    posicionesAlumno.add(i);
                }
            }

            // Elegir un alumno aleatorio de la lista de alumnos con menos intervenciones
            Random random = new Random();
            int posicionAleatoria = posicionesAlumno.get(random.nextInt(posicionesAlumno.size()));
            String corrector = nombres.get(posicionAleatoria);
            System.out.println("El alumno encargado de corregir es: " + corrector);

            // Crear un scanner para verificar si el alumno ha realizado o no el ejercicio
            Scanner hechoNoHecho = new Scanner(System.in);
            System.out.println("¿El alumno ha realizado el ejercicio?");
            System.out.println("- Sí. Introduzca 'yes'");
            System.out.println("- No. Introduzca 'no'");
            String respuesta = hechoNoHecho.nextLine();

            // Actualizar las intervenciones del alumno si ha realizado el ejercicio
            if (respuesta.equals("yes")) {
                int actualizacionIntervenciones = menosIntervenciones + 1;
                intervenciones.set(posicionAleatoria, actualizacionIntervenciones);

                // Actualizar los datos en la base de datos
                String updateQuery = "UPDATE alumno SET intervenciones = ? WHERE nombre = ?";
                PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                updateStatement.setInt(1, actualizacionIntervenciones);
                updateStatement.setString(2, corrector);
                updateStatement.executeUpdate();

                System.out.println("El número de intervenciones del alumno ha aumentado");

            } else if (respuesta.equals("no")) {
                System.out.println("No se han realizado cambios");
                seleccionAlumno();

            } else {
                System.out.println("Debe insertar una de las opciones por defecto");
            }

            // Cerrar la conexión a la base de datos
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
