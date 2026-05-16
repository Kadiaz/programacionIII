package modelo;

import java.util.LinkedList;

public class Estudiante extends Persona {
    private int semestre;
    private Double[][] notas;
    private LinkedList<String> historialMaterias;

    public Estudiante(String nombreP, String idP, String emailP, int semestreP) {
        super(nombreP, idP, emailP);
        semestre = semestreP;
        notas = new Double[10][20];
        historialMaterias = new LinkedList<>();
    }

    public int getSemestre() { return semestre; }
    public Double[][] getNotas() { return notas; }
    public LinkedList<String> getHistorialMaterias() { return historialMaterias; }

    public void registrarNota(int semestreP, int materiaP, double notaP) {
        notas[semestreP][materiaP] = notaP;
    }

    public double calcularPromedio(int semestreP) {
        double suma = 0;
        int count = 0;
        for (int i = 0; i < 20; i++) {
            if (notas[semestreP][i] != null) {
                suma += notas[semestreP][i];
                count++;
            }
        }
        return count > 0 ? suma / count : 0.0;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("==============================");
        System.out.println("ID:       " + getId());
        System.out.println("Nombre:   " + getNombre());
        System.out.println("Email:    " + getEmail());
        System.out.println("Semestre: " + semestre);
        System.out.println("Promedio: " + calcularPromedio(semestre - 1));
        System.out.println("==============================");
    }
}
