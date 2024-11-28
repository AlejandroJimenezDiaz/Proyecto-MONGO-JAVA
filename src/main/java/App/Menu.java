package App;

import dao.AlumnosDao;
import dao.ProfesorDao;
import model.Alumno;
import model.Profesor;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private ProfesorDao profesorDao = new ProfesorDao();
    private AlumnosDao alumnoDao = new AlumnosDao();

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("Menu:");
            System.out.println("1. Insertar un profesor");
            System.out.println("2. Insertar un alumno");
            System.out.println("3. Mostrar Alumnos y Profesores");
            System.out.println("4. Mostrar profesores");
            System.out.println("5. Mostrar alumnos");
            System.out.println("6. Buscar alumno por Email");
            System.out.println("7. Buscar profesor por rango de Edad");
            System.out.println("8. Actualizar rating profesor buscando por su Email");
            System.out.println("9. Dar de baja alumnos cuya calificación sea 5 o mayor");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("◄---------------------------------------------------------►");

            switch (opcion) {
                case 1:
                    insertarProfesor();
                    break;
                case 2:
                    insertarAlumno();
                    break;
                case 3:
                    mostrarDatos();
                    break;
                case 4:
                    mostrarProfesores();
                    break;
                case 5:
                    mostrarAlumnos();
                    break;
                case 6:
                    buscarAlumno();
                    break;
                case 7:
                    buscarProfesor();
                    break;
                case 8:
                    actualizarProfesor();
                    break;
                case 9:
                    darDeBajaAlumnos();
                    break;
                case 10:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 10);
    }

    private void insertarProfesor() {
        System.out.println("Introduzca los datos del profesor:");

        // Solicitar los datos del profesor
        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        System.out.print("Edad: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer (para evitar problemas con nextLine después de nextInt)

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Título: ");
        String title = scanner.nextLine();

        System.out.print("Sexo♀♂ (male/female): ");
        String gender = scanner.nextLine();

        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();

        System.out.print("Rating: ");
        Double rating = scanner.nextDouble();
        scanner.nextLine();  // Limpiar el buffer (para evitar problemas con nextLine después de nextDouble)

        // Crear la lista de materias
        ArrayList<String> subjects = new ArrayList<>();
        System.out.println("Introduzca las materias (si no hay más materias presione enter sin rellenar nada):");

        while (true) {
            System.out.print("Materia: ");
            String materia = scanner.nextLine();
            if (materia.isEmpty()) {
                break;  // Si el usuario no introduce nada, terminar la entrada de materias
            }
            subjects.add(materia);
        }

        // Crear el objeto Profesor y guardarlo en la base de datos
        Profesor profesor = new Profesor(rating, age, name, gender, email, phone, subjects, title);
        profesorDao.insertarProfesorPOJO(profesor);
        System.out.println("Profesor insertado exitosamente.");
    }

    private void insertarAlumno() {
        System.out.println("Introduzca los datos del alumno:");

        // Solicitar los datos del alumno
        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        System.out.print("Edad: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer después de nextInt

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Calificación: ");
        int calification = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer después de nextInt

        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();

        System.out.print("Sexo♀♂ (male/female): ");
        String gender = scanner.nextLine();

        System.out.print("Rating: ");
        Double rating = scanner.nextDouble();
        scanner.nextLine();  // Limpiar el buffer después de nextDouble

        System.out.print("Curso: ");
        String higher_grade = scanner.nextLine();

        // Crear el objeto Alumno
        Alumno alumno = new Alumno(rating, age, name, gender, email, phone, calification, higher_grade);

        // Insertar el alumno en la base de datos
        alumnoDao.insertarAlumnoPOJO(alumno);

        System.out.println("Alumno insertado exitosamente.");
    }

    private void mostrarDatos() {
        System.out.println("Mostrando todos los datos de profesores y alumnos:");
        profesorDao.obtenerTodosProfesoresPOJO();
        alumnoDao.obtenerTodosAlumnosPOJO();
    }

    private void mostrarProfesores() {
        System.out.println("Mostrando todos los profesores:");
        profesorDao.obtenerTodosProfesoresPOJO();
    }

    private void mostrarAlumnos() {
        System.out.println("Mostrando todos los alumnos:");
        alumnoDao.obtenerTodosAlumnosPOJO();
    }

    private void buscarAlumno() {
        System.out.print("Introduzca el email del alumno a buscar: ");
        String email = scanner.nextLine();
        alumnoDao.buscarAlumnoEmailPOJO(email);
    }

    private void buscarProfesor() {
        System.out.print("Introduzca el rango de edad (minima edad): ");
        int edadmin = scanner.nextInt();
        System.out.print("Introduzca el rango de edad (máxima edad): ");
        int edadmax = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer
        profesorDao.buscarProfesorRangoEdadPOJO(edadmax, edadmin);
    }

    private void actualizarProfesor() {
        System.out.print("Introduzca el email del profesor a actualizar: ");
        String email = scanner.nextLine();
        System.out.print("Introduzca la nueva calificación: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();  // Limpiar el buffer
        profesorDao.actualizarRatingSegunEmailPOJO(email, rating);
    }

    private void darDeBajaAlumnos() {
        alumnoDao.eliminarAlumnoNotaPOJO();
    }

}

