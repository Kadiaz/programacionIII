// GestionDeshacer.java
import java.util.Stack;

public class GestionDeshacer {

    private Stack<Estudiante> pilaDeshacer;
    private Stack<Estudiante> pilaRehacer;
    private GestionEstudiantes gestion;

    public GestionDeshacer(GestionEstudiantes gestionP) {
        pilaDeshacer = new Stack<>();
        pilaRehacer = new Stack<>();
        gestion = gestionP;
    }

    // Guarda el estudiante eliminado en la pila deshacer
    public void guardarEnPilaDeshacer(Estudiante estudianteP) {
        pilaDeshacer.push(estudianteP);
        pilaRehacer.clear(); // al hacer nueva acción se limpia rehacer
        System.out.println("Operacion guardada. Puede deshacerla.");
    }

    // Deshacer: restaura el estudiante eliminado
    public void deshacer() throws PilaDeshacerVaciaException {
        if (pilaDeshacer.isEmpty()) {
            throw new PilaDeshacerVaciaException(
                "Error: No hay operaciones para deshacer."
            );
        }
        Estudiante estudianteRestaurado = pilaDeshacer.pop();
        gestion.registrarEstudiante(
            estudianteRestaurado.getNombre(),
            estudianteRestaurado.getId(),
            estudianteRestaurado.getEmail(),
            estudianteRestaurado.getSemestre()
        );
        pilaRehacer.push(estudianteRestaurado);
        System.out.println("Operacion deshecha: " + estudianteRestaurado.getNombre() + " restaurado.");
    }

    // Rehacer: vuelve a eliminar el estudiante
    public void rehacer() throws PilaDeshacerVaciaException {
        if (pilaRehacer.isEmpty()) {
            throw new PilaDeshacerVaciaException(
                "Error: No hay operaciones para rehacer."
            );
        }
        Estudiante estudianteRehacer = pilaRehacer.pop();
        try {
            gestion.eliminarEstudiante(estudianteRehacer.getId());
            pilaDeshacer.push(estudianteRehacer);
            System.out.println("Operacion rehecha: " + estudianteRehacer.getNombre() + " eliminado nuevamente.");
        } catch (EstudianteNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}
