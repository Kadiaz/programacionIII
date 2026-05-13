// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GestionEstudiantes gestion = new GestionEstudiantes();
        GestionDeshacer deshacer = new GestionDeshacer(gestion);
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n============================================================");
            System.out.println("   PLANIFICACIÓN ACADÉMICA - SISTEMA UNIVERSITARIO");
            System.out.println("============================================================");
            System.out.println("=== GESTION DE ESTUDIANTES ===");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Buscar estudiante por ID");
            System.out.println("3. Listar todos los estudiantes");
            System.out.println("4. Eliminar estudiante");
            System.out.println("=== DESHACER / REHACER ===");
            System.out.println("5. Deshacer ultima operacion");
            System.out.println("6. Rehacer ultima operacion");
            System.out.println("0. Salir");
            System.out.println("============================================================");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRO DE ESTUDIANTE ---");
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Semestre actual: ");
                    int semestre = scanner.nextInt();
                    scanner.nextLine();
                    gestion.registrarEstudiante(nombre, id, email, semestre);
                    break;

                case 2:
                    System.out.println("\n--- BUSCAR ESTUDIANTE ---");
                    System.out.print("ID: ");
                    String idBuscar = scanner.nextLine();
                    try {
                        Estudiante encontrado = gestion.buscarEstudiante(idBuscar);
                        encontrado.mostrarInformacion();
                    } catch (EstudianteNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    gestion.listarEstudiantes();
                    break;

                case 4:
                    System.out.println("\n--- ELIMINAR ESTUDIANTE ---");
                    System.out.print("ID: ");
                    String idEliminar = scanner.nextLine();
                    try {
                        Estudiante eliminado = gestion.eliminarEstudiante(idEliminar);
                        deshacer.guardarEnPilaDeshacer(eliminado);
                    } catch (EstudianteNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        deshacer.deshacer();
                    } catch (PilaDeshacerVaciaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        deshacer.rehacer();
                    } catch (PilaDeshacerVaciaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida, intente de nuevo.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}