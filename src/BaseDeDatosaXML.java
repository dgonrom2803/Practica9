import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BaseDeDatosaXML {
    public static void exportarDatos() {
        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/daw1", "root", "");

            // Obtener los nombres de todas las tablas en la base de datos
            PreparedStatement tablesStatement = conn.prepareStatement("SHOW TABLES");
            ResultSet tablesResultSet = tablesStatement.executeQuery();

            // Crear el objeto Document para construir el documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Crear el elemento raíz del documento XML
            Element rootElement = doc.createElement("database");
            doc.appendChild(rootElement);

            // Iterar sobre las tablas en la base de datos
            while (tablesResultSet.next()) {
                String tableName = tablesResultSet.getString(1);

                // Obtener los datos de cada tabla
                PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM " + tableName);
                ResultSet resultSet = selectStatement.executeQuery();

                // Obtener los metadatos del resultado de la consulta
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Iterar sobre los resultados de la consulta
                while (resultSet.next()) {
                    // Crear un elemento para cada fila de datos
                    Element rowElement = doc.createElement(tableName);
                    rootElement.appendChild(rowElement);

                    // Iterar sobre las columnas y crear elementos correspondientes
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        String columnValue = resultSet.getString(i);

                        Element columnElement = doc.createElement(columnName);
                        columnElement.appendChild(doc.createTextNode(columnValue));

                        rowElement.appendChild(columnElement);
                    }
                }

                resultSet.close();
                selectStatement.close();
            }

            // Solicitar al usuario la ruta de guardado del archivo XML
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese la ruta de guardado del archivo XML:");
            String rutaArchivo = scanner.nextLine();
            scanner.close();

            // Generar el archivo XML con los datos
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter(rutaArchivo));
            transformer.transform(source, result);

            System.out.println("Los datos se han exportado correctamente a '" + rutaArchivo + "'.");

            // Cerrar la conexión a la base de datos
            conn.close();

        } catch (SQLException | ParserConfigurationException | TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
