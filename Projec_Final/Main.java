// Main.java
import java.util.Scanner;

import excepciones.EstudianteNoEncontradoException;
import excepciones.PilaDeshacerVaciaException;
import modelo.Estudiante;
import util.GestionBatch;
import util.GestionDeshacer;
import util.GestionEdificios;
import util.GestionEstudiantes;
import util.GestionHorarios;
import util.GestionMaterias;
import util.GestionReportes;

public class Main {
    public static void main(String[] args) {

        GestionEstudiantes gestion = new GestionEstudiantes();
        GestionDeshacer deshacer = new GestionDeshacer(gestion);
        GestionMaterias gestionMaterias = new GestionMaterias(gestion);
        GestionHorarios gestionHorarios = new GestionHorarios();
        GestionEdificios gestionEdificios = new GestionEdificios();
        GestionReportes gestionReportes = new GestionReportes(gestion);
        GestionBatch gestionBatch = new GestionBatch(gestionMaterias);
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n============================================================");
            System.out.println("   REGISTRO ACADÉMICO - SISTEMA UNIVERSITARIO");
            System.out.println("============================================================");
            System.out.println("\n === GESTION DE ESTUDIANTES ===");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Buscar estudiante por ID");
            System.out.println("3. Listar todos los estudiantes");
            System.out.println("4. Eliminar estudiante");

            System.out.println("\n === GESTION DE MATERIAS ===");
            System.out.println("5. Crear materia");
            System.out.println("6. Agregar prerequisito");
            System.out.println("7. Mostrar prerequisitos");
            System.out.println("8. Inscribir estudiante en materia");
            System.out.println("9. Cancelar inscripcion");
            System.out.println("10. Mostrar cola de espera");
            System.out.println("11. Listar todas las materias");

            System.out.println("\n === GESTION DE HORARIOS ===");
            System.out.println("12. Agregar aula");
            System.out.println("13. Reservar horario");
            System.out.println("14. Liberar horario");
            System.out.println("15. Consultar disponibilidad");
            System.out.println("16. Mostrar horario de aula");
            System.out.println("17. Listar todas las aulas");

            System.out.println("\n === RUTAS ENTRE EDIFICIOS ===");
            System.out.println("18. Mostrar edificios");
            System.out.println("19. Agregar conexion entre edificios");
            System.out.println("20. Calcular ruta mas corta");
            System.out.println("21. Mostrar matriz de distancias");

            System.out.println("\n === REPORTES ACADEMICOS ===");
            System.out.println("22. Registrar nota");
            System.out.println("23. Ver reporte academico");
            System.out.println("24. Volver al reporte anterior");

            System.out.println("\n === PROCESAMIENTO BATCH ===");
            System.out.println("25. Ingresar solicitudes batch");
            System.out.println("26. Procesar cola batch");
            System.out.println("27. Ver solicitudes pendientes");

            System.out.println("\n === DESHACER / REHACER ===");
            System.out.println("28. Deshacer ultima operacion");
            System.out.println("29. Rehacer ultima operacion");
            
            System.out.println("\n=== SALIR ===");
            System.out.println("0. Salir");
            System.out.println("\n============================================================");
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
                    System.out.println("\n--- AGREGAR AULA ---");
                    System.out.print("Nombre del aula: ");
                    String nombreAula = scanner.nextLine();
                    System.out.print("Capacidad: ");
                    int capacidad = scanner.nextInt();
                    scanner.nextLine();
                    gestionHorarios.agregarAula(nombreAula, capacidad);
                    break;

                case 13:
                    System.out.println("\n--- RESERVAR HORARIO ---");
                    System.out.print("Nombre del aula: ");
                    String aulaReservar = scanner.nextLine();
                    System.out.println("Dia (0=Domingo, 1=Lunes, 2=Martes, 3=Miercoles, 4=Jueves, 5=Viernes, 6=Sabado): ");
                    int diaReservar = scanner.nextInt();
                    System.out.print("Hora de inicio (ejemplo: 8): ");
                    int horaReservar = scanner.nextInt();
                    System.out.print("Duracion (horas): ");
                    int duracionReservar = scanner.nextInt();
                    scanner.nextLine();
                    gestionHorarios.reservar(aulaReservar, diaReservar, horaReservar, duracionReservar);
                    break;

                case 14:
                    System.out.println("\n--- LIBERAR HORARIO ---");
                    System.out.print("Nombre del aula: ");
                    String aulaLiberar = scanner.nextLine();
                    System.out.println("Dia (0=Domingo, 1=Lunes, 2=Martes, 3=Miercoles, 4=Jueves, 5=Viernes, 6=Sabado): ");
                    int diaLiberar = scanner.nextInt();
                    System.out.print("Hora de inicio (ejemplo: 8): ");
                    int horaLiberar = scanner.nextInt();
                    System.out.print("Duracion (horas): ");
                    int duracionLiberar = scanner.nextInt();
                    scanner.nextLine();
                    gestionHorarios.liberar(aulaLiberar, diaLiberar, horaLiberar, duracionLiberar);
                    break;

                case 15:
                    System.out.println("\n--- CONSULTAR DISPONIBILIDAD ---");
                    System.out.print("Nombre del aula: ");
                    String aulaConsultar = scanner.nextLine();
                    System.out.println("Dia (0=Domingo, 1=Lunes, 2=Martes, 3=Miercoles, 4=Jueves, 5=Viernes, 6=Sabado): ");
                    int diaConsultar = scanner.nextInt();
                    System.out.print("Hora (ejemplo: 8): ");
                    int horaConsultar = scanner.nextInt();
                    scanner.nextLine();
                    gestionHorarios.consultarDisponibilidad(aulaConsultar, diaConsultar, horaConsultar);
                    break;

                case 16:
                    System.out.println("\n--- MOSTRAR HORARIO DE AULA ---");
                    System.out.print("Nombre del aula: ");
                    String aulaMostrar = scanner.nextLine();
                    gestionHorarios.mostrarHorario(aulaMostrar);
                    break;

                case 17:
                    gestionHorarios.listarAulas();
                    break;

                case 18:
                    gestionEdificios.mostrarEdificios();
                    break;

                case 19:
                    System.out.println("\n--- AGREGAR CONEXION ---");
                    gestionEdificios.mostrarEdificios();
                    System.out.print("Indice edificio origen: ");
                    int origen = scanner.nextInt();
                    System.out.print("Indice edificio destino: ");
                    int destino = scanner.nextInt();
                    System.out.print("Distancia en metros: ");
                    int distancia = scanner.nextInt();
                    scanner.nextLine();
                    gestionEdificios.agregarConexion(origen, destino, distancia);
                    break;

                case 20:
                    System.out.println("\n--- CALCULAR RUTA MAS CORTA ---");
                    gestionEdificios.mostrarEdificios();
                    System.out.print("Indice edificio origen: ");
                    int origenRuta = scanner.nextInt();
                    System.out.print("Indice edificio destino: ");
                    int destinoRuta = scanner.nextInt();
                    scanner.nextLine();
                    gestionEdificios.calcularRutaMasCorta(origenRuta, destinoRuta);
                    break;

                case 21:
                    gestionEdificios.mostrarMatriz();
                    break;

                case 22:
                    System.out.println("\n--- REGISTRAR NOTA ---");
                    System.out.print("ID del estudiante: ");
                    String idNota = scanner.nextLine();
                    System.out.print("Semestre (0-9): ");
                    int semestreNota = scanner.nextInt();
                    System.out.print("Numero de materia (0-19): ");
                    int materiaNum = scanner.nextInt();
                    System.out.print("Nota (0.0 - 5.0): ");
                    double nota = scanner.nextDouble();
                    scanner.nextLine();
                    gestionReportes.registrarNota(idNota, semestreNota, materiaNum, nota);
                    break;

                case 23:
                    System.out.println("\n--- VER REPORTE ACADEMICO ---");
                    System.out.print("ID del estudiante: ");
                    String idReporte = scanner.nextLine();
                    gestionReportes.verReporte(idReporte);
                    break;

                case 24:
                    gestionReportes.atras();
                    break;

                case 25:
                    System.out.println("\n--- INGRESAR SOLICITUDES BATCH ---");
                    gestionBatch.encolarSolicitudes(scanner);
                    break;

                case 26:
                    System.out.println("\n--- PROCESAR COLA BATCH ---");
                    gestionBatch.procesarCola();
                    break;

                case 27:
                    gestionBatch.verSolicitudesPendientes();
                    break;

                case 28:
                    try {
                        deshacer.deshacer();
                    } catch (PilaDeshacerVaciaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 29:
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