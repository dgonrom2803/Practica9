
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
            System.out.println("5. Poner a 0 todas las intervenciones");
            System.out.println("6. Salir");


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
                        //Daw1.daw1();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 6");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe insertar un número");
                sn.next();
            }
        }
    }
}