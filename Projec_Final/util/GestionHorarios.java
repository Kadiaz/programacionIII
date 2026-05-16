package util;
// GestionHorarios.java
import java.util.TreeMap;

import excepciones.HorarioConflictivoException;
import modelo.Aula;

public class GestionHorarios {

    private TreeMap<String, Aula> aulas;

    public GestionHorarios() {
        aulas = new TreeMap<>();
    }

    // 1. Agregar aula
    public void agregarAula(String nombreP, int capacidadP) {
        if (aulas.containsKey(nombreP)) {
            System.out.println("Error: Ya existe un aula con el nombre " + nombreP);
            return;
        }
        Aula nuevaAula = new Aula(nombreP, capacidadP);
        aulas.put(nombreP, nuevaAula);
        System.out.println("Aula " + nombreP + " agregada exitosamente.");
    }

    // 2. Reservar horario
    public void reservar(String nombreAulaP, int diaP, int horaP, int duracionP) {
        Aula aula = aulas.get(nombreAulaP);
        if (aula == null) {
            System.out.println("Error: No existe el aula " + nombreAulaP);
            return;
        }
        try {
            aula.reservar(diaP, horaP, duracionP);
        } catch (HorarioConflictivoException e) {
            System.out.println(e.getMessage());
        }
    }

    // 3. Liberar horario
    public void liberar(String nombreAulaP, int diaP, int horaP, int duracionP) {
        Aula aula = aulas.get(nombreAulaP);
        if (aula == null) {
            System.out.println("Error: No existe el aula " + nombreAulaP);
            return;
        }
        aula.liberar(diaP, horaP, duracionP);
    }

    // 4. Consultar disponibilidad
    public void consultarDisponibilidad(String nombreAulaP, int diaP, int horaP) {
        Aula aula = aulas.get(nombreAulaP);
        if (aula == null) {
            System.out.println("Error: No existe el aula " + nombreAulaP);
            return;
        }
        aula.consultarDisponibilidad(diaP, horaP);
    }

    // 5. Mostrar horario de un aula
    public void mostrarHorario(String nombreAulaP) {
        Aula aula = aulas.get(nombreAulaP);
        if (aula == null) {
            System.out.println("Error: No existe el aula " + nombreAulaP);
            return;
        }
        aula.mostrarHorario();
    }

    // 6. Listar todas las aulas ordenadas por nombre (TreeMap ya las ordena)
    public void listarAulas() {
        if (aulas.isEmpty()) {
            System.out.println("No hay aulas registradas.");
            return;
        }
        System.out.println("\n===== LISTA DE AULAS =====");
        aulas.forEach((nombre, aula) -> {
            System.out.println("Aula: " + nombre + 
                " | Capacidad: " + aula.getCapacidad());
        });
    }

    public TreeMap<String, Aula> getAulas() {
        return aulas;
    }
}
