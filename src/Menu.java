
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args){

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while(!salir){

            System.out.println("1. Crear la base de datos");
            System.out.println("2. Importar XML a la base de datos");
            System.out.println("3. Seleccionar alumno");
            System.out.println("4. Alumnos con más y menos intervenciones");
            System.out.println("5. Borra la base de datos");
            System.out.println("6. Alumnos con intervenciones menor a la media");
            System.out.println("7. Alumnos con intervenciones mayor, igual y superior a valor dado");
            System.out.println("8. Información del alumno");
            System.out.println("9. Dar de alta a alumno nuevo");
            System.out.println("10. Modificar datos de alumno");
            System.out.println("11. Borrar alumno");
            System.out.println("12. Exportar Base de datos a XML");
            System.out.println("13. Salir");


            try {

                System.out.println("Escriba una de las opciones");
                opcion = sn.nextInt();

                switch(opcion) {
                    case 1:
                        System.out.println("Has seleccionado opción 1");
                        CrearBasedeDatos.creaBaseDeDatos();
                        break;
                    case 2:
                        System.out.println("Has seleccionado opción 2");
                        XMLaBaseDeDatos.xmlToDatabase();
                        break;
                    case 3:
                        System.out.println("Has seleccionado opción 3");
                        SeleccionAlumnoAleatorio.seleccionAlumno();
                        break;
                    case 4:
                        System.out.println("Has seleccionado opción 4");
                        EstadisticasAlumnos.estadisticasAlumnos();
                        break;
                    case 5:
                        System.out.println("Has seleccionado opción 5" );
                        BorrarBasedeDatos.borraBaseDeDatos();
                        break;
                    case 6:
                        System.out.println("Has seleccionado opción 6" );
                        MenorQueLaMedia.menorQueLaMedia();
                        break;
                    case 7:
                        System.out.println("Has seleccionado opción 7" );
                        AlumnosPorValorDado.alumnosPorValorDado();
                        break;
                    case 8:
                        System.out.println("Has seleccionado opción 8" );
                        InformacionAlumno.informacionAlumno();
                        break;
                    case 9:
                        System.out.println("Has seleccionado opción 9" );
                        GestionAlumnos.agregarAlumno();
                        break;
                    case 10:
                        System.out.println("Has seleccionado opción 10" );
                        GestionAlumnos.modificarAlumno();
                        break;
                    case 11:
                        System.out.println("Has seleccionado opción 11" );
                        GestionAlumnos.eliminarAlumno();
                        break;
                    case 12:
                        System.out.println("Has seleccionado opción 12" );
                        BaseDeDatosaXML.exportarDatos();
                        break;
                    case 13:
                        System.out.println("Has seleccionado opción 13" );
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 13");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe insertar un número");
                sn.next();
            }
        }
    }
}