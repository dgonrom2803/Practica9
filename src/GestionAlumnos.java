import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class GestionAlumnos {
    public static void agregarAlumno() {
        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/daw1", "root", "");

            // Obtener los datos del nuevo alumno por consola
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el nombre del nuevo alumno:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el número de intervenciones del nuevo alumno:");
            int intervenciones = scanner.nextInt();

            // Insertar el nuevo alumno en la base de datos
            String insertQuery = "INSERT INTO alumno (nombre, intervenciones) VALUES (?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setString(1, nombre);
            insertStatement.setInt(2, intervenciones);
            insertStatement.executeUpdate();

            System.out.println("El alumno se ha agregado correctamente.");

            // Cerrar la conexión a la base de datos
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modificarAlumno() {
        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/daw1", "root", "");

            // Obtener los datos del alumno a modificar por consola
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el nombre del alumno a modificar:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el nuevo número de intervenciones del alumno:");
            int intervenciones = scanner.nextInt();

            // Verificar si el alumno existe en la base de datos
            String selectQuery = "SELECT nombre FROM alumno WHERE nombre = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setString(1, nombre);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Actualizar los datos del alumno en la base de datos
                String updateQuery = "UPDATE alumno SET intervenciones = ? WHERE nombre = ?";
                PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                updateStatement.setInt(1, intervenciones);
                updateStatement.setString(2, nombre);
                updateStatement.executeUpdate();

                System.out.println("Los datos del alumno se han modificado correctamente.");
            } else {
                System.out.println("El alumno no existe en la base de datos.");
            }

            // Cerrar la conexión a la base de datos
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void eliminarAlumno() {
        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/daw1", "root", "");

            // Obtener el nombre del alumno a eliminar por consola
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el nombre del alumno a eliminar:");
            String nombre = scanner.nextLine();

            // Verificar si el alumno existe en la base de datos
            String selectQuery = "SELECT nombre FROM alumno WHERE nombre = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setString(1, nombre);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Eliminar el alumno de la base de datos
                String deleteQuery = "DELETE FROM alumno WHERE nombre = ?";
                PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
                deleteStatement.setString(1, nombre);
                deleteStatement.executeUpdate();

                System.out.println("El alumno se ha eliminado correctamente.");
            } else {
                System.out.println("El alumno no existe en la base de datos.");
            }

            // Cerrar la conexión a la base de datos
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
