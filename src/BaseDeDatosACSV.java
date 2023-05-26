import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BaseDeDatosACSV {
    public static void exportarBaseDeDatos() {
        try {
            Scanner scanner = new Scanner(System.in);

            // Solicitar la ruta del archivo CSV al usuario
            System.out.println("Ingrese la ruta del archivo CSV:");
            String csvFilePath = scanner.nextLine();

            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/daw1", "root", "");

            // Crear una declaración SQL para obtener los datos de la tabla "alumno"
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM alumno");

            // Obtener los metadatos del resultado de la consulta
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Crear un FileWriter para escribir en el archivo CSV
            FileWriter writer = new FileWriter(csvFilePath);

            // Escribir los nombres de las columnas en la primera línea del archivo CSV
            for (int i = 1; i <= columnCount; i++) {
                writer.append(metaData.getColumnName(i));
                if (i < columnCount) {
                    writer.append(",");
                }
            }
            writer.append("\n");

            // Conjunto para almacenar las entradas ya exportadas
            Set<String> entradasExportadas = new HashSet<>();

            // Escribir los datos de cada fila en el archivo CSV
            while (resultSet.next()) {
                String entrada = "";
                for (int i = 1; i <= columnCount; i++) {
                    entrada += resultSet.getString(i);
                    if (i < columnCount) {
                        entrada += ",";
                    }
                }

                // Verificar si la entrada ya ha sido exportada
                if (!entradasExportadas.contains(entrada)) {
                    writer.append(entrada);
                    writer.append("\n");

                    // Agregar la entrada al conjunto de entradas exportadas
                    entradasExportadas.add(entrada);
                }
            }

            // Cerrar recursos
            writer.flush();
            writer.close();
            resultSet.close();
            statement.close();
            conn.close();

            System.out.println("Exportación de la tabla 'alumno' a CSV completada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
