import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InterfazGrafica extends Frame {
    private Button eliminarButton;
    private Button crearButton;

    public InterfazGrafica() {
        // Configuración de la ventana
        setTitle("Interfaz de Base de Datos");
        setSize(300, 200);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        // Crear los botones
        eliminarButton = new Button("Eliminar Base de Datos");
        crearButton = new Button("Crear Base de Datos");

        // Configurar el diseño de la ventana
        setLayout(new FlowLayout());

        // Agregar los botones a la ventana
        add(eliminarButton);
        add(crearButton);

        // Agregar ActionListener al botón eliminar
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarBaseDatos();
            }
        });

        // Agregar ActionListener al botón crear
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearBaseDatos();
            }
        });

        setVisible(true);
    }

    // Método para eliminar la base de datos
    private void eliminarBaseDatos() {
        System.out.println("Eliminar la base de datos");
        BorrarBasedeDatos.borraBaseDeDatos();
    }

    // Método para crear la base de datos
    private void crearBaseDatos() {
        System.out.println("Crear la base de datos");
        CrearBasedeDatos.creaBaseDeDatos();
    }

    public static void main(String[] args) {
        InterfazGrafica interfaz = new InterfazGrafica();
    }
}
