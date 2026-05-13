// GestionReportes.java
import java.util.Stack;

public class GestionReportes {

    private GestionEstudiantes gestionEstudiantes;
    private Stack<String> historialReportes; // navegacion entre reportes

    public GestionReportes(GestionEstudiantes gestionEstudiantesP) {
        gestionEstudiantes = gestionEstudiantesP;
        historialReportes = new Stack<>();
    }

    // 1. Registrar nota
    public void registrarNota(String idEstudianteP, int semestreP, 
                               int materiaP, double notaP) {
        try {
            Estudiante estudiante = gestionEstudiantes.buscarEstudiante(idEstudianteP);

            if (semestreP < 0 || semestreP > 9) {
                System.out.println("Error: Semestre debe estar entre 0 y 9.");
                return;
            }
            if (materiaP < 0 || materiaP > 19) {
                System.out.println("Error: Materia debe estar entre 0 y 19.");
                return;
            }
            if (notaP < 0.0 || notaP > 5.0) {
                System.out.println("Error: Nota debe estar entre 0.0 y 5.0.");
                return;
            }

            estudiante.registrarNota(semestreP, materiaP, notaP);
            System.out.println("Nota " + notaP + " registrada exitosamente.");

        } catch (EstudianteNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    // 2. Ver reporte academico completo
    public void verReporte(String idEstudianteP) {
        try {
            Estudiante estudiante = gestionEstudiantes.buscarEstudiante(idEstudianteP);
            Double[][] notas = estudiante.getNotas();

            System.out.println("\n--- REPORTE ACADEMICO ---");
            System.out.println("Estudiante: " + estudiante.getNombre() + 
                " (ID: " + estudiante.getId() + ")");

            double promedioAcumulado = 0;
            int totalMaterias = 0;
            int materiasAprobadas = 0;
            int materiasReprobadas = 0;

            for (int sem = 0; sem < 10; sem++) {
                boolean tieneMaterias = false;
                double sumaSemestre = 0;
                int countSemestre = 0;

                for (int mat = 0; mat < 20; mat++) {
                    if (notas[sem][mat] != null) {
                        if (!tieneMaterias) {
                            System.out.println("\nSemestre " + (sem + 1) + ":");
                            tieneMaterias = true;
                        }
                        System.out.println("  Materia " + (mat + 1) + 
                            ": " + notas[sem][mat]);
                        sumaSemestre += notas[sem][mat];
                        countSemestre++;
                        totalMaterias++;

                        if (notas[sem][mat] >= 3.0) {
                            materiasAprobadas++;
                        } else {
                            materiasReprobadas++;
                        }
                    }
                }

                if (tieneMaterias) {
                    double promedioSem = sumaSemestre / countSemestre;
                    promedioAcumulado += sumaSemestre;
                    System.out.println("  Promedio semestre: " + 
                        String.format("%.2f", promedioSem));
                }
            }

            System.out.println("\n=== RESUMEN ===");
            if (totalMaterias > 0) {
                System.out.println("Promedio acumulado: " + 
                    String.format("%.2f", promedioAcumulado / totalMaterias));
            } else {
                System.out.println("Promedio acumulado: 0.0 (sin notas aun)");
            }
            System.out.println("Materias aprobadas:  " + materiasAprobadas);
            System.out.println("Materias reprobadas: " + materiasReprobadas);

            // Guardar en historial de reportes
            historialReportes.push(idEstudianteP);

        } catch (EstudianteNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    // 3. Navegacion hacia atras en reportes
    public void atras() {
        if (historialReportes.isEmpty()) {
            System.out.println("Error: No hay reportes anteriores.");
            return;
        }
        historialReportes.pop(); // quita el actual
        if (historialReportes.isEmpty()) {
            System.out.println("No hay reportes anteriores.");
            return;
        }
        String idAnterior = historialReportes.peek();
        System.out.println("Volviendo al reporte anterior...");
        verReporte(idAnterior);
    }
}
