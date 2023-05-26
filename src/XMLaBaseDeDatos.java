import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLaBaseDeDatos {
    public static void xmlABaseDeDatos() {
        String xmlFilePath = "src/daw1.xml";

        try {
            // Configurar la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/daw1", "root", "");

            // Crear un documento a partir del archivo XML
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Obtener la lista de nodos <alumno> del documento
            NodeList nodeList = doc.getElementsByTagName("alumno");

            // Recorrer los nodos <alumno> e insertar los datos en la base de datos
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    int intervenciones = Integer.parseInt(element.getElementsByTagName("intervenciones").item(0).getTextContent());
                    String fechaIntervencion = element.getElementsByTagName("fecha_intervencion").item(0).getTextContent();

                    // Verificar si el registro ya existe en la base de datos
                    String selectQuery = "SELECT COUNT(*) AS count FROM alumno WHERE nombre = ?";
                    PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
                    selectStatement.setString(1, nombre);
                    ResultSet resultSet = selectStatement.executeQuery();
                    resultSet.next();
                    int count = resultSet.getInt("count");

                    if (count == 0) {
                        // Insertar los datos en la tabla 'alumno'
                        String insertQuery = "INSERT INTO alumno (nombre, intervenciones, fecha_intervencion) VALUES (?, ?, ?)";
                        PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
                        insertStatement.setString(1, nombre);
                        insertStatement.setInt(2, intervenciones);
                        insertStatement.setString(3, fechaIntervencion);
                        insertStatement.executeUpdate();
                    }
                }
            }

            // Cerrar la conexión a la base de datos
            conn.close();

            System.out.println("Importación XML completada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
