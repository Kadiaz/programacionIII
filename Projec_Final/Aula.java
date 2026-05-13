// Aula.java
public class Aula {
    private String nombre;
    private int capacidad;
    private boolean[][] horario; // [7 dias][24 horas]

    public Aula(String nombreP, int capacidadP) {
        nombre = nombreP;
        capacidad = capacidadP;
        horario = new boolean[7][24];
    }

    // 1. Reservar horario
    public void reservar(int diaP, int horaP, int duracionP) 
        throws HorarioConflictivoException {

        // Validar que hora + duracion no supere 23
        if (horaP + duracionP > 24) {
            System.out.println("Error: La hora " + horaP + " con duracion " + 
                duracionP + " horas supera el limite del dia (23:00).");
            return;
        }

        // Validar que la hora esté en rango válido
        if (horaP < 0 || horaP > 23) {
            System.out.println("Error: La hora debe estar entre 0 y 23.");
            return;
        }

        // Primero verificar disponibilidad de todas las horas
        for (int i = horaP; i < horaP + duracionP; i++) {
            if (horario[diaP][i]) {
                throw new HorarioConflictivoException(
                    "Error: " + obtenerNombreDia(diaP) + " " + i + 
                    ":00 ya esta reservado en aula " + nombre
                );
            }
    }

    // Si todo está libre, reservar
    for (int i = horaP; i < horaP + duracionP; i++) {
        horario[diaP][i] = true;
        System.out.println(obtenerNombreDia(diaP) + " " + i + ":00 -> RESERVADO");
    }
    System.out.println("Reserva exitosa en aula " + nombre);
}

    // 2. Liberar horario
    public void liberar(int diaP, int horaP, int duracionP) {
        for (int i = horaP; i < horaP + duracionP; i++) {
            horario[diaP][i] = false;
            System.out.println(obtenerNombreDia(diaP) + " " + i + ":00 -> LIBERADO");
        }
        System.out.println("Horario liberado en aula " + nombre);
    }

    // 3. Consultar disponibilidad
    public void consultarDisponibilidad(int diaP, int horaP) {
        if (horario[diaP][horaP]) {
            System.out.println(obtenerNombreDia(diaP) + " " + horaP + 
                ":00 en aula " + nombre + " -> OCUPADO");
        } else {
            System.out.println(obtenerNombreDia(diaP) + " " + horaP + 
                ":00 en aula " + nombre + " -> LIBRE");
        }
    }

    // Mostrar horario completo del aula
    public void mostrarHorario() {
        System.out.println("\n--- HORARIO AULA: " + nombre + " ---");
        String[] dias = {"Domingo", "Lunes", "Martes", "Miercoles", 
                         "Jueves", "Viernes", "Sabado"};
        for (int dia = 0; dia < 7; dia++) {
            for (int hora = 0; hora < 24; hora++) {
                if (horario[dia][hora]) {
                    System.out.println(dias[dia] + " " + hora + ":00 -> OCUPADO");
                }
            }
        }
    }

    // Obtener nombre del dia
    private String obtenerNombreDia(int diaP) {
        String[] dias = {"Domingo", "Lunes", "Martes", "Miercoles",
                         "Jueves", "Viernes", "Sabado"};
        return dias[diaP];
    }

    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public boolean[][] getHorario() { return horario; }
}
