// Main.java
import Excepciones.EstudianteNoEncontradoException;

public class Main {
    public static void main(String[] args) {

        GestionEstudiantes gestion = new GestionEstudiantes();

        // Registrar estudiantes
        gestion.registrarEstudiante("Ana Maria Gomez", "2024001", "ana@universidad.edu", 3);
        gestion.registrarEstudiante("Luis Perez", "2024002", "luis@universidad.edu", 2);

        // Listar
        gestion.listarEstudiantes();

        // Buscar existente
        try {
            Estudiante encontrado = gestion.buscarEstudiante("2024001");
            encontrado.mostrarInformacion();
        } catch (EstudianteNoEncontradoException e) {
            System.out.println(e.getMessage());
        }

        // Buscar no existente
        try {
            gestion.buscarEstudiante("9999");
        } catch (EstudianteNoEncontradoException e) {
            System.out.println(e.getMessage());
        }

        // Eliminar
        try {
            gestion.eliminarEstudiante("2024002");
            gestion.listarEstudiantes();
        } catch (EstudianteNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}