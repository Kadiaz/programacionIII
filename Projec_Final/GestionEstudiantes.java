// GestionEstudiantes.java
import java.util.HashMap;

public class GestionEstudiantes {

    private HashMap<String, Estudiante> estudiantes;

    public GestionEstudiantes() {
        estudiantes = new HashMap<>();
    }

    // 1. Registrar estudiante
    public void registrarEstudiante(String nombreP, String idP, String emailP, int semestreP) {
        Estudiante nuevoEstudiante = new Estudiante(nombreP, idP, emailP, semestreP);
        estudiantes.put(idP, nuevoEstudiante);
        System.out.println("Estudiante registrado exitosamente.");
    }

    // 2. Buscar estudiante por ID
    public Estudiante buscarEstudiante(String idP) throws EstudianteNoEncontradoException {
        if (!estudiantes.containsKey(idP)) {
            throw new EstudianteNoEncontradoException(
                "Error: No existe estudiante con ID: " + idP
            );
        }
        return estudiantes.get(idP);
    }

    // 3. Listar todos los estudiantes
    public void listarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        System.out.println("\n===== LISTA DE ESTUDIANTES =====");
        estudiantes.forEach((id, estudiante) -> {
            estudiante.mostrarInformacion();
        });
    }

    // 4. Eliminar estudiante
    public Estudiante eliminarEstudiante(String idP) throws EstudianteNoEncontradoException {
        Estudiante estudianteEliminado = buscarEstudiante(idP);
        estudiantes.remove(idP);
        System.out.println("Estudiante " + estudianteEliminado.getNombre() + " eliminado.");
        return estudianteEliminado;
    }

    public HashMap<String, Estudiante> getEstudiantes() {
        return estudiantes;
    }
}
