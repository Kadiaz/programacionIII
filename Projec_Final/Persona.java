// Estudiante.java
import java.util.LinkedList;

public class Estudiante extends Persona {
    private int semestre;
    private Double[][] notas;           // arreglo nativo [10 semestres][20 materias]
    private LinkedList<String> historialMaterias;

    public Estudiante(String nombre, String id, String email, int semestre) {
        super(nombre, id, email);
        this.semestre = semestre;
        this.notas = new Double[10][20];
        this.historialMaterias = new LinkedList<>();
    }

    // Getters
    public int getSemestre() { return semestre; }
    public Double[][] getNotas() { return notas; }
    public LinkedList<String> getHistorialMaterias() { return historialMaterias; }

    // Registrar una nota
    public void registrarNota(int semestre, int materia, double nota) {
        notas[semestre][materia] = nota;
    }

    // Calcular promedio de un semestre
    public double calcularPromedio(int semestre) {
        double suma = 0;
        int count = 0;
        for (int i = 0; i < 20; i++) {
            if (notas[semestre][i] != null) {
                suma += notas[semestre][i];
                count++;
            }
        }
        return count > 0 ? suma / count : 0.0;
    }

    // Sobrescritura obligatoria
    @Override
    public void mostrarInformacion() {
        System.out.println("==============================");
        System.out.println("ID:       " + getId());
        System.out.println("Nombre:   " + getNombre());
        System.out.println("Email:    " + getEmail());
        System.out.println("Semestre: " + semestre);
        System.out.println("Promedio acumulado: " + calcularPromedio(semestre - 1));
        System.out.println("==============================");
    }
}