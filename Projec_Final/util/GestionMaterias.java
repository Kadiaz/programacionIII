package util;
// GestionMaterias.java
import java.util.HashMap;

import excepciones.CupoLlenoException;
import excepciones.EstudianteNoEncontradoException;
import excepciones.PreRequisitoNoAprobadoException;
import modelo.Estudiante;
import modelo.Materia;

public class GestionMaterias {

    private HashMap<String, Materia> materias;
    private GestionEstudiantes gestionEstudiantes;

    public GestionMaterias(GestionEstudiantes gestionEstudiantesP) {
        materias = new HashMap<>();
        gestionEstudiantes = gestionEstudiantesP;
    }

    // 1. Crear materia
    public void crearMateria(String codigoP, String nombreP, int cuposP, int creditosP) {
        if (materias.containsKey(codigoP)) {
            System.out.println("Error: Ya existe una materia con el codigo " + codigoP);
            return;
        }
        Materia nuevaMateria = new Materia(codigoP, nombreP, cuposP, creditosP);
        materias.put(codigoP, nuevaMateria);
        System.out.println("Materia " + nombreP + " creada exitosamente.");
    }

    // 2. Agregar prerequisito
    public void agregarPreRequisito(String codigoMateriaP, String codigoPreReqP) {
        Materia materia = materias.get(codigoMateriaP);
        if (materia == null) {
            System.out.println("Error: No existe la materia " + codigoMateriaP);
            return;
        }
        materia.agregarPreRequisito(codigoPreReqP);
    }

    // 3. Mostrar prerequisitos
    public void mostrarPreRequisitos(String codigoMateriaP) {
        Materia materia = materias.get(codigoMateriaP);
        if (materia == null) {
            System.out.println("Error: No existe la materia " + codigoMateriaP);
            return;
        }
        materia.mostrarPreRequisitos();
    }

    // 4. Inscribir estudiante
    public void inscribirEstudiante(String idEstudianteP, String codigoMateriaP) {
        Materia materia = materias.get(codigoMateriaP);
        if (materia == null) {
            System.out.println("Error: No existe la materia " + codigoMateriaP);
            return;
        }
        try {
            Estudiante estudiante = gestionEstudiantes.buscarEstudiante(idEstudianteP);
            materia.inscribirEstudiante(estudiante);
        } catch (EstudianteNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (CupoLlenoException e) {
            System.out.println(e.getMessage());
        } catch (PreRequisitoNoAprobadoException e) {
            System.out.println(e.getMessage());
        }
    }

    // 5. Cancelar inscripcion
    public void cancelarInscripcion(String idEstudianteP, String codigoMateriaP) {
        Materia materia = materias.get(codigoMateriaP);
        if (materia == null) {
            System.out.println("Error: No existe la materia " + codigoMateriaP);
            return;
        }
        try {
            materia.cancelarInscripcion(idEstudianteP, gestionEstudiantes);
        } catch (EstudianteNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    // 6. Mostrar cola de espera
    public void mostrarColaEspera(String codigoMateriaP) {
        Materia materia = materias.get(codigoMateriaP);
        if (materia == null) {
            System.out.println("Error: No existe la materia " + codigoMateriaP);
            return;
        }
        materia.mostrarColaEspera();
    }

    // 7. Listar todas las materias
    public void listarMaterias() {
        if (materias.isEmpty()) {
            System.out.println("No hay materias registradas.");
            return;
        }
        System.out.println("\n===== LISTA DE MATERIAS =====");
        materias.forEach((codigo, materia) -> {
            materia.mostrarInformacion();
        });
    }

    public HashMap<String, Materia> getMaterias() {
        return materias;
    }

    public void inscribirEstudianteBatch(String idEstudianteP, String codigoMateriaP) 
        throws Exception {
        Materia materia = materias.get(codigoMateriaP);
        if (materia == null) {
            throw new Exception("Materia " + codigoMateriaP + " no existe");
        }
        try {
            Estudiante estudiante = gestionEstudiantes.buscarEstudiante(idEstudianteP);
            materia.inscribirEstudiante(estudiante);
        } catch (EstudianteNoEncontradoException e) {
            throw new Exception("Estudiante " + idEstudianteP + " no encontrado");
        } catch (CupoLlenoException e) {
            throw new Exception("Cupo lleno en " + codigoMateriaP);
        } catch (PreRequisitoNoAprobadoException e) {
            throw new Exception("Prerequisito no aprobado");
        }
    }
}
