// Main.java
import util.*;
import modelo.*;
import excepciones.*;
import estructuras.*;
import java.util.Scanner;

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

        // Arreglo de módulos principales
        String[] modulos = {
            "[1] Gestion de Estudiantes",
            "[2] Gestion de Materias",
            "[3] Gestion de Horarios",
            "[4] Rutas entre Edificios",
            "[5] Reportes Academicos",
            "[6] Procesamiento Batch",
            "[7] Deshacer / Rehacer",
            "[0] Salir"
        };

        int opcion;
        do {
            System.out.println("\n====================================================");
            System.out.println("     PLANIFICACION ACADEMICA - S_U");
            System.out.println("======================================================");
            for (String modulo : modulos) {
                System.out.println("  " + modulo);
            }
            System.out.println("========================================================");
            System.out.print("  Seleccione un modulo: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1: // ESTUDIANTES
                    String[] opEstudiantes = {
                        "[1] Registrar estudiante",
                        "[2] Buscar estudiante por ID",
                        "[3] Listar todos los estudiantes",
                        "[4] Eliminar estudiante",
                        "[0] Volver al menu principal"
                    };
                    int opEst;
                    do {
                        System.out.println("\n============================================================");
                        System.out.println("              GESTION DE ESTUDIANTES");
                        System.out.println("============================================================");
                        for (String op : opEstudiantes) {
                            System.out.println("  " + op);
                        }
                        System.out.println("============================================================");
                        System.out.print("  Seleccione una opcion: ");
                        opEst = scanner.nextInt();
                        scanner.nextLine();

                        switch (opEst) {
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
                                System.out.println("\nPresione Enter para continuar...");
                                scanner.nextLine();
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

                            case 0:
                                System.out.println("Volviendo al menu principal...");
                                break;

                            default:
                                System.out.println("Opcion no valida.");
                        }
                    } while (opEst != 0);
                    break;

                case 2: // MATERIAS
                    String[] opMaterias = {
                    "[1] Crear materia",
                    "[2] Listar todas las materias",
                    "[3] Inscribir estudiante",
                    "[4] Cancelar inscripcion",
                    "[5] Mostrar cola de espera",
                    "[6] Agregar prerequisito",
                    "[7] Mostrar prerequisitos",
                    "[0] Volver al menu principal"
                };
                    int opMat;
                    do {
                        System.out.println("\n============================================================");
                        System.out.println("               GESTION DE MATERIAS");
                        System.out.println("============================================================");
                        for (String op : opMaterias) {
                            System.out.println("  " + op);
                        }
                        System.out.println("============================================================");
                        System.out.print("  Seleccione una opcion: ");
                        opMat = scanner.nextInt();
                        scanner.nextLine();

                        switch (opMat) {
                            case 1:
                                // Crear materia
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

                            case 2:
                                // Listar materias
                                gestionMaterias.listarMaterias();
                                System.out.println("\nPresione Enter para continuar...");
                                scanner.nextLine();
                                break;

                            case 3:
                                // Inscribir estudiante
                                System.out.println("\n--- INSCRIBIR ESTUDIANTE ---");
                                System.out.print("ID del estudiante: ");
                                String idInscribir = scanner.nextLine();
                                System.out.print("Codigo de la materia: ");
                                String codInscribir = scanner.nextLine();
                                gestionMaterias.inscribirEstudiante(idInscribir, codInscribir);
                                break;

                            case 4:
                                // Cancelar inscripcion
                                System.out.println("\n--- CANCELAR INSCRIPCION ---");
                                System.out.print("ID del estudiante: ");
                                String idCancelar = scanner.nextLine();
                                System.out.print("Codigo de la materia: ");
                                String codCancelar = scanner.nextLine();
                                gestionMaterias.cancelarInscripcion(idCancelar, codCancelar);
                                break;

                            case 5:
                                // Cola de espera
                                System.out.println("\n--- COLA DE ESPERA ---");
                                System.out.print("Codigo de la materia: ");
                                String codCola = scanner.nextLine();
                                gestionMaterias.mostrarColaEspera(codCola);
                                break;

                            case 6:
                                // Agregar prerequisito
                                System.out.println("\n--- AGREGAR PREREQUISITO ---");
                                System.out.print("Codigo de la materia: ");
                                String codMateria = scanner.nextLine();
                                System.out.print("Codigo del prerequisito: ");
                                String codPreReq = scanner.nextLine();
                                gestionMaterias.agregarPreRequisito(codMateria, codPreReq);
                                break;

                            case 7:
                                // Mostrar prerequisitos
                                System.out.println("\n--- MOSTRAR PREREQUISITOS ---");
                                System.out.print("Codigo de la materia: ");
                                String codVerPreReq = scanner.nextLine();
                                gestionMaterias.mostrarPreRequisitos(codVerPreReq);
                                break;

                            case 0:
                                System.out.println("Volviendo al menu principal...");
                                break;

                            default:
                                System.out.println("Opcion no valida.");
                        }
                    } while (opMat != 0);
                    break;

                case 3: // HORARIOS
                    String[] opHorarios = {
                        "[1] Agregar aula",
                        "[2] Reservar horario",
                        "[3] Liberar horario",
                        "[4] Consultar disponibilidad",
                        "[5] Mostrar horario de aula",
                        "[6] Listar todas las aulas",
                        "[0] Volver al menu principal"
                    };
                    int opHor;
                    do {
                        System.out.println("\n============================================================");
                        System.out.println("               GESTION DE HORARIOS");
                        System.out.println("============================================================");
                        for (String op : opHorarios) {
                            System.out.println("  " + op);
                        }
                        System.out.println("============================================================");
                        System.out.print("  Seleccione una opcion: ");
                        opHor = scanner.nextInt();
                        scanner.nextLine();

                        switch (opHor) {
                            case 1:
                                System.out.println("\n--- AGREGAR AULA ---");
                                System.out.print("Nombre del aula: ");
                                String nombreAula = scanner.nextLine();
                                System.out.print("Capacidad: ");
                                int capacidad = scanner.nextInt();
                                scanner.nextLine();
                                gestionHorarios.agregarAula(nombreAula, capacidad);
                                break;

                            case 2:
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

                            case 3:
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

                            case 4:
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

                            case 5:
                                System.out.println("\n--- MOSTRAR HORARIO DE AULA ---");
                                System.out.print("Nombre del aula: ");
                                String aulaMostrar = scanner.nextLine();
                                gestionHorarios.mostrarHorario(aulaMostrar);
                                break;

                            case 6:
                                gestionHorarios.listarAulas();
                                System.out.println("\nPresione Enter para continuar...");
                                scanner.nextLine();
                                break;

                            case 0:
                                System.out.println("Volviendo al menu principal...");
                                break;

                            default:
                                System.out.println("Opcion no valida.");
                        }
                    } while (opHor != 0);
                    break;

                case 4: // EDIFICIOS
                    String[] opEdificios = {
                        "[1] Mostrar edificios",
                        "[2] Agregar conexion entre edificios",
                        "[3] Calcular ruta mas corta",
                        "[4] Mostrar matriz de distancias",
                        "[0] Volver al menu principal"
                    };
                    int opEdi;
                    do {
                        System.out.println("\n============================================================");
                        System.out.println("             RUTAS ENTRE EDIFICIOS");
                        System.out.println("============================================================");
                        for (String op : opEdificios) {
                            System.out.println("  " + op);
                        }
                        System.out.println("============================================================");
                        System.out.print("  Seleccione una opcion: ");
                        opEdi = scanner.nextInt();
                        scanner.nextLine();

                        switch (opEdi) {
                            case 1:
                                gestionEdificios.mostrarEdificios();
                                System.out.println("\nPresione Enter para continuar...");
                                scanner.nextLine();
                                break;

                            case 2:
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

                            case 3:
                                System.out.println("\n--- CALCULAR RUTA MAS CORTA ---");
                                gestionEdificios.mostrarEdificios();
                                System.out.print("Indice edificio origen: ");
                                int origenRuta = scanner.nextInt();
                                System.out.print("Indice edificio destino: ");
                                int destinoRuta = scanner.nextInt();
                                scanner.nextLine();
                                gestionEdificios.calcularRutaMasCorta(origenRuta, destinoRuta);
                                break;

                            case 4:
                                gestionEdificios.mostrarMatriz();
                                System.out.println("\nPresione Enter para continuar...");
                                scanner.nextLine();
                                break;

                            case 0:
                                System.out.println("Volviendo al menu principal...");
                                break;

                            default:
                                System.out.println("Opcion no valida.");
                        }
                    } while (opEdi != 0);
                    break;

                case 5: // REPORTES
                    String[] opReportes = {
                        "[1] Registrar nota",
                        "[2] Ver reporte academico",
                        "[3] Volver al reporte anterior",
                        "[0] Volver al menu principal"
                    };
                    int opRep;
                    do {
                        System.out.println("\n============================================================");
                        System.out.println("               REPORTES ACADEMICOS");
                        System.out.println("============================================================");
                        for (String op : opReportes) {
                            System.out.println("  " + op);
                        }
                        System.out.println("============================================================");
                        System.out.print("  Seleccione una opcion: ");
                        opRep = scanner.nextInt();
                        scanner.nextLine();

                        switch (opRep) {
                            case 1:
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

                            case 2:
                                System.out.println("\n--- VER REPORTE ACADEMICO ---");
                                System.out.print("ID del estudiante: ");
                                String idReporte = scanner.nextLine();
                                gestionReportes.verReporte(idReporte);
                                break;

                            case 3:
                                gestionReportes.atras();
                                break;

                            case 0:
                                System.out.println("Volviendo al menu principal...");
                                break;

                            default:
                                System.out.println("Opcion no valida.");
                        }
                    } while (opRep != 0);
                    break;

                case 6: // BATCH
                    String[] opBatch = {
                        "[1] Ingresar solicitudes batch",
                        "[2] Procesar cola batch",
                        "[3] Ver solicitudes pendientes",
                        "[0] Volver al menu principal"
                    };
                    int opBat;
                    do {
                        System.out.println("\n============================================================");
                        System.out.println("              PROCESAMIENTO BATCH");
                        System.out.println("============================================================");
                        for (String op : opBatch) {
                            System.out.println("  " + op);
                        }
                        System.out.println("============================================================");
                        System.out.print("  Seleccione una opcion: ");
                        opBat = scanner.nextInt();
                        scanner.nextLine();

                        switch (opBat) {
                            case 1:
                                System.out.println("\n--- INGRESAR SOLICITUDES BATCH ---");
                                gestionBatch.encolarSolicitudes(scanner);
                                break;

                            case 2:
                                System.out.println("\n--- PROCESAR COLA BATCH ---");
                                gestionBatch.procesarCola();
                                break;

                            case 3:
                                gestionBatch.verSolicitudesPendientes();
                                break;

                            case 0:
                                System.out.println("Volviendo al menu principal...");
                                break;

                            default:
                                System.out.println("Opcion no valida.");
                        }
                    } while (opBat != 0);
                    break;

                case 7: // DESHACER/REHACER
                    String[] opDeshacer = {
                        "[1] Deshacer ultima operacion",
                        "[2] Rehacer ultima operacion",
                        "[0] Volver al menu principal"
                    };
                    int opDes;
                    do {
                        System.out.println("\n============================================================");
                        System.out.println("               DESHACER / REHACER");
                        System.out.println("============================================================");
                        for (String op : opDeshacer) {
                            System.out.println("  " + op);
                        }
                        System.out.println("============================================================");
                        System.out.print("  Seleccione una opcion: ");
                        opDes = scanner.nextInt();
                        scanner.nextLine();

                        switch (opDes) {
                            case 1:
                                try {
                                    deshacer.deshacer();
                                } catch (PilaDeshacerVaciaException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case 2:
                                try {
                                    deshacer.rehacer();
                                } catch (PilaDeshacerVaciaException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case 0:
                                System.out.println("Volviendo al menu principal...");
                                break;

                            default:
                                System.out.println("Opcion no valida.");
                        }
                    } while (opDes != 0);
                    break;

                case 0:
                    System.out.println("\nGracias por usar el sistema!");
                    break;

                default:
                    System.out.println("Modulo no valido, intente de nuevo.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}