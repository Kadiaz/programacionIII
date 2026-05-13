// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GestionEstudiantes gestion = new GestionEstudiantes();
        GestionDeshacer deshacer = new GestionDeshacer(gestion);
        GestionMaterias gestionMaterias = new GestionMaterias(gestion);
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
            System.out.println("=== GESTION DE MATERIAS ===");
            System.out.println("5. Crear materia");
            System.out.println("6. Agregar prerequisito");
            System.out.println("7. Mostrar prerequisitos");
            System.out.println("8. Inscribir estudiante en materia");
            System.out.println("9. Cancelar inscripcion");
            System.out.println("10. Mostrar cola de espera");
            System.out.println("11. Listar todas las materias");
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
                    System.out.println("\n--- CREAR MATERIA ---");
                    System.out.print("Codigo: ");
                    String codigoMateria = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombreMateria = scanner.nextLine();
                    System.out.print("Cupos maximos: ");
                    int cupos = scanner.nextInt();
                    System.out.print("Creditos: ");
                    int creditos = scanner.nextInt();
                    scanner.nextLine();
                    gestionMaterias.crearMateria(codigoMateria, nombreMateria, cupos, creditos);
                    break;

                case 6:
                    System.out.println("\n--- AGREGAR PREREQUISITO ---");
                    System.out.print("Codigo de la materia: ");
                    String codMateria = scanner.nextLine();
                    System.out.print("Codigo del prerequisito: ");
                    String codPreReq = scanner.nextLine();
                    gestionMaterias.agregarPreRequisito(codMateria, codPreReq);
                    break;

                case 7:
                    System.out.println("\n--- MOSTRAR PREREQUISITOS ---");
                    System.out.print("Codigo de la materia: ");
                    String codVerPreReq = scanner.nextLine();
                    gestionMaterias.mostrarPreRequisitos(codVerPreReq);
                    break;

                case 8:
                    System.out.println("\n--- INSCRIBIR ESTUDIANTE ---");
                    System.out.print("ID del estudiante: ");
                    String idInscribir = scanner.nextLine();
                    System.out.print("Codigo de la materia: ");
                    String codInscribir = scanner.nextLine();
                    gestionMaterias.inscribirEstudiante(idInscribir, codInscribir);
                    break;

                case 9:
                    System.out.println("\n--- CANCELAR INSCRIPCION ---");
                    System.out.print("ID del estudiante: ");
                    String idCancelar = scanner.nextLine();
                    System.out.print("Codigo de la materia: ");
                    String codCancelar = scanner.nextLine();
                    gestionMaterias.cancelarInscripcion(idCancelar, codCancelar);
                    break;

                case 10:
                    System.out.println("\n--- COLA DE ESPERA ---");
                    System.out.print("Codigo de la materia: ");
                    String codCola = scanner.nextLine();
                    gestionMaterias.mostrarColaEspera(codCola);
                    break;

                case 11:
                    gestionMaterias.listarMaterias();
                    break;

                case 12:
                    try {
                        deshacer.deshacer();
                    } catch (PilaDeshacerVaciaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 13:
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