*EXPLICACIÓN DEL CÓDIGO*
===================================
El código es una aplicación de consola impulsada por menú que permite al usuario crear y administrar una base de datos de estudiantes.
Aquí hay una explicación detallada de cómo funciona:


El programa comienza importando las clases necesarias de la biblioteca de utilidades de Java, que incluye las clases Scanner e
 
InputMismatchException.

El método principal crea un objeto Scanner e inicializa una variable booleana llamada "salir" en falso. También crea una variable

entera "opcion" para almacenar la selección del menú del usuario.

El programa entra en un bucle while que seguirá ejecutándose hasta que el usuario seleccione la opción "salir".

Dentro del bucle, el programa muestra un menú de opciones al usuario utilizando una serie de declaraciones println.

Luego, el programa solicita al usuario que ingrese una opción mediante la visualización del mensaje "Escriba una de las

opciones" y la lectura de la entrada del usuario utilizando el método nextInt del Scanner.

El programa utiliza una declaración switch para hacer coincidir la entrada del usuario con una de las opciones del menú.

Dependiendo de la entrada del usuario, el programa llama a un método específico para realizar una tarea determinada.

Si el usuario ingresa una entrada no entera o una opción que no está en el menú, el programa captura la excepción

InputMismatchException y muestra el mensaje "Debe insertar un número".

Si el usuario selecciona la opción "salir", el programa establece la variable "salir" en verdadero, lo que hará que el bucle

while salga y el programa termine.

En general, el programa es una aplicación de consola simple que permite al usuario interactuar con una base de datos de estudiantes

seleccionando varias opciones de menú.



*Título del Proyecto*
===================================
Control de participaciones del alumnado en clase.



*Descripción*
===================================
Explicación de qué hace nuestro proyecto, por qué es útil y cómo funciona:


Este proyecto es una aplicación de control de las participaciones del alumnado en clase.

Gracias a unos métodos podemos pasar un xml o csv a una base de datos creada por el método inicial si es requerido.

La aplicación tiene lo considerado para cumplir sus funciones, pero en caso de que el cliente desee aumentar las acciones

que realiza solo debería consultarlo con el equipo desarrollador. Finalmente esta aplicación te permite exportar la información

de la base de datos a un xml y a un csv, al igual podemos borrar la base de datos creada anteriormente.



*Instalación*
===================================
Para instalar y ejecutar este proyecto, siga estos pasos:


1) Clone el repositorio en su máquina local utilizando git clone https://github.com/dgonrom2803/Practica9.

2) Abra el proyecto en su IDE o editor de texto favorito.

3) Compile y ejecute el archivo Menu.java.



*Uso*
===================================
Instrucciones sobre cómo usar este proyecto:


Para usar este proyecto, simplemente ejecute el archivo Menu.java. El programa mostrará un menú de opciones al usuario.

El usuario puede seleccionar una opción ingresando el número correspondiente y presionando enter. El programa realizará entonces

la tarea seleccionada, como crear una nueva base de datos o seleccionar un estudiante al azar.



*Versión*
===================================
La versión actual de este proyecto es la 4.1.0, lanzada el 28 de mayo de 2023. Esta versión incluye las siguientes características y mejoras:

Nueva funcionalidad - Interfaz gráfica para la creación y borrado de bases de datos
Mejora de la funcionalidad - Gestión de alumnos
Corrección de errores mínimos