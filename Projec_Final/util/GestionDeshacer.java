package util;
// GestionDeshacer.java

import estructuras.Pila;
import excepciones.EstudianteNoEncontradoException;
import excepciones.PilaDeshacerVaciaException;
import modelo.Estudiante;

public class GestionDeshacer {

    private Pila<Estudiante> pilaDeshacer;
    private Pila<Estudiante> pilaRehacer;
    private GestionEstudiantes gestion;

    public GestionDeshacer(GestionEstudiantes gestionP) {
        pilaDeshacer = new Pila<>();
        pilaRehacer = new Pila<>();
        gestion = gestionP;
    }

    // Guarda el estudiante eliminado en la pila deshacer
    public void guardarEnPilaDeshacer(Estudiante estudianteP) {
        pilaDeshacer.apilar(estudianteP);
        System.out.println("Operacion guardada. Puede deshacerla.");
    }

    // Deshacer: restaura el estudiante eliminado
    public void deshacer() throws PilaDeshacerVaciaException {
        if (pilaDeshacer.estaVacia()) {
            throw new PilaDeshacerVaciaException(
                "Error: No hay operaciones para deshacer."
            );
        }
        Estudiante estudianteRestaurado = pilaDeshacer.desapilar();
        gestion.registrarEstudiante(
            estudianteRestaurado.getNombre(),
            estudianteRestaurado.getId(),
            estudianteRestaurado.getEmail(),
            estudianteRestaurado.getSemestre()
        );
        pilaRehacer.apilar(estudianteRestaurado);
        System.out.println("Operacion deshecha: " + 
            estudianteRestaurado.getNombre() + " restaurado.");
    }

    // Rehacer: vuelve a eliminar el estudiante
    public void rehacer() throws PilaDeshacerVaciaException {
        if (pilaRehacer.estaVacia()) {
            throw new PilaDeshacerVaciaException(
                "Error: No hay operaciones para rehacer."
            );
        }
        Estudiante estudianteRehacer = pilaRehacer.desapilar();
        try {
            gestion.eliminarEstudiante(estudianteRehacer.getId());
            pilaDeshacer.apilar(estudianteRehacer);
            System.out.println("Operacion rehecha: " + 
                estudianteRehacer.getNombre() + " eliminado nuevamente.");
        } catch (EstudianteNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}