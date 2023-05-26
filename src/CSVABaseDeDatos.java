
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CSVABaseDeDatos {
    public static void importaCSV() {
        String csvFilePath = "C:\\Users\\diego\\OneDrive\\Escritorio\\participaciones.csv";
        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/daw1", "root", "");

            // Leer el archivo CSV
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;

            // Ignorar la primera línea que contiene los encabezados
            reader.readLine();

            // Preparar la consulta de inserción
            String insertQuery = "INSERT INTO alumno (nombre, intervenciones) VALUES (?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);

            // Recorrer cada línea del archivo CSV
            while ((line = reader.readLine()) != null) {
                // Dividir la línea en columnas separadas por comas
                String[] columns = line.split(",");

                // Verificar si la línea tiene al menos dos columnas
                if (columns.length >= 2) {
                    // Obtener los datos de cada columna
                    String nombre = columns[0];
                    int intervenciones = Integer.parseInt(columns[1]);

                    // Establecer los valores en la consulta de inserción
                    insertStatement.setString(1, nombre);
                    insertStatement.setInt(2, intervenciones);

                    // Ejecutar la consulta de inserción
                    insertStatement.executeUpdate();
                }
            }

            // Cerrar la conexión a la base de datos
            conn.close();

            System.out.println("Importación CSV completada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

