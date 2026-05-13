// GestionBatch.java
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GestionBatch {

    private Queue<String[]> colaSolicitudes;
    private GestionMaterias gestionMaterias;

    public GestionBatch(GestionMaterias gestionMateriasP) {
        colaSolicitudes = new LinkedList<>();
        gestionMaterias = gestionMateriasP;
    }

    // 1. Encolar solicitudes manualmente
    public void encolarSolicitudes(Scanner scannerP) {
        System.out.print("Cuantas solicitudes desea ingresar? ");
        int cantidad = scannerP.nextInt();
        scannerP.nextLine();

        for (int i = 1; i <= cantidad; i++) {
            System.out.println("\nSolicitud " + i + ":");
            System.out.print("  ID estudiante: ");
            String idEstudiante = scannerP.nextLine();
            System.out.print("  Codigo materia: ");
            String codigoMateria = scannerP.nextLine();
            colaSolicitudes.offer(new String[]{idEstudiante, codigoMateria});
        }
        System.out.println("\n" + cantidad + " solicitudes encoladas exitosamente.");
    }

    // 2. Procesar cola
    public void procesarCola() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("Error: No hay solicitudes en la cola.");
            return;
        }

        int total = colaSolicitudes.size();
        int exitosas = 0;
        int fallidas = 0;
        int contador = 1;

        System.out.println("\nProcesando cola...");

        while (!colaSolicitudes.isEmpty()) {
            String[] solicitud = colaSolicitudes.poll();
            String idEstudiante = solicitud[0];
            String codigoMateria = solicitud[1];

            System.out.print("[" + contador + "/" + total + "] " +
                idEstudiante + " -> " + codigoMateria + " -> ");

            try {
                gestionMaterias.inscribirEstudianteBatch(idEstudiante, codigoMateria);
                System.out.println("Exitosa");
                exitosas++;
            } catch (Exception e) {
                System.out.println("Fallida (" + e.getMessage() + ")");
                fallidas++;
            }
            contador++;
        }

        System.out.println("\n=== RESUMEN ===");
        System.out.println("Exitosas: " + exitosas);
        System.out.println("Fallidas: " + fallidas);
    }

    // 3. Ver solicitudes pendientes
    public void verSolicitudesPendientes() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }
        System.out.println("\n--- SOLICITUDES PENDIENTES ---");
        int posicion = 1;
        for (String[] solicitud : colaSolicitudes) {
            System.out.println("Posicion " + posicion + ": " +
                solicitud[0] + " -> " + solicitud[1]);
            posicion++;
        }
        System.out.println("Total pendientes: " + colaSolicitudes.size());
    }
}
