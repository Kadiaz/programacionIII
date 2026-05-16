package modelo;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import excepciones.*;
import util.GestionEstudiantes;

import java.util.ArrayDeque;

/**
 * Clase materia que gestiona cupos, prerequisitos, inscripciones y cola de espera.
 * Usa LinkedList para prerequisitos y estudiantes inscritos,
 * y Queue para la cola de espera cuando los cupos estan llenos.
 */
public class Materia {
    private String codigo;
    private String nombre;
    private int cuposMaximos;
    private int cuposDisponibles;
    private int creditos;
    private LinkedList<String> preRequisitos;
    private LinkedList<String> estudiantesInscritos;
    private Queue<String> colaEspera;
    
    //Constructor de la clase.
    public Materia(String codigoP, String nombreP, int cuposMaximosP, int creditosP) {
        codigo = codigoP;
        nombre = nombreP;
        cuposMaximos = cuposMaximosP;
        cuposDisponibles = cuposMaximosP;
        creditos = creditosP;
        preRequisitos = new LinkedList<>();
        estudiantesInscritos = new LinkedList<>();
        colaEspera = new ArrayDeque<>();
    }

    // Agregar prerequisito
    public void agregarPreRequisito(String codigoMateriaP) {
        preRequisitos.add(codigoMateriaP);
        System.out.println("Prerequisito " + codigoMateriaP + " agregado a " + nombre);
    }

    // Verificar si el estudiante cumple los prerequisitos
    public boolean cumplePreRequisitos(LinkedList<String> historialP) {
        for (String preReq : preRequisitos) {
            if (!historialP.contains(preReq)) {
                return false;
            }
        }
        return true;
    }

    // Inscribir estudiante
    public void inscribirEstudiante(Estudiante estudianteP) 
        throws CupoLlenoException, PreRequisitoNoAprobadoException {

        if (!cumplePreRequisitos(estudianteP.getHistorialMaterias())) {
            throw new PreRequisitoNoAprobadoException(
                "Error: " + estudianteP.getNombre() + 
                " no cumple los prerequisitos de " + nombre
            );
        }

        if (cuposDisponibles == 0) {
            colaEspera.offer(estudianteP.getId());
            throw new CupoLlenoException(
                "Materia llena. " + estudianteP.getNombre() + 
                " agregado a cola de espera. Posicion: " + colaEspera.size()
            );
        }

        estudiantesInscritos.add(estudianteP.getId());
        estudianteP.getHistorialMaterias().add(codigo);
        cuposDisponibles--;
        System.out.println(estudianteP.getNombre() + " inscrito en " + nombre + 
            ". Cupos restantes: " + cuposDisponibles);
    }

    // Cancelar inscripcion y asignar al primero en cola
    public void cancelarInscripcion(String idEstudianteP, GestionEstudiantes gestionP) 
        throws EstudianteNoEncontradoException {

        if (!estudiantesInscritos.contains(idEstudianteP)) {
            throw new EstudianteNoEncontradoException(
                "Error: El estudiante no está inscrito en " + nombre
            );
        }

        estudiantesInscritos.remove(idEstudianteP);
        cuposDisponibles++;
        System.out.println("Cancelacion exitosa. Cupo liberado en " + nombre);

        // Asignar cupo al primero en cola
        if (!colaEspera.isEmpty()) {
            String idSiguiente = colaEspera.poll();
            try {
                Estudiante siguiente = gestionP.buscarEstudiante(idSiguiente);
                estudiantesInscritos.add(idSiguiente);
                siguiente.getHistorialMaterias().add(codigo);
                cuposDisponibles--;
                System.out.println("Cupo asignado a " + siguiente.getNombre() + 
                    " (primero en cola de espera)");
            } catch (EstudianteNoEncontradoException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Mostrar cola de espera
    public void mostrarColaEspera() {
        if (colaEspera.isEmpty()) {
            System.out.println("No hay estudiantes en cola de espera para " + nombre);
            return;
        }
        System.out.println("\n--- COLA DE ESPERA: " + nombre + " ---");
        int posicion = 1;
        for (String idEstudiante : colaEspera) {
            System.out.println("Posicion " + posicion + ": " + idEstudiante);
            posicion++;
        }
        System.out.println("Total en espera: " + colaEspera.size());
    }

    // Mostrar prerequisitos
    public void mostrarPreRequisitos() {
        if (preRequisitos.isEmpty()) {
            System.out.println(nombre + " no tiene prerequisitos.");
            return;
        }
        System.out.println("\n--- PREREQUISITOS DE " + nombre + " ---");
        for (String preReq : preRequisitos) {
            System.out.println("- " + preReq);
        }
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCuposDisponibles() { return cuposDisponibles; }
    public int getCuposMaximos() { return cuposMaximos; }
    public LinkedList<String> getEstudiantesInscritos() { return estudiantesInscritos; }
    public Queue<String> getColaEspera() { return colaEspera; }
    
    //Muestra la informacion general de la materia.
    public void mostrarInformacion() {
        System.out.println("==============================");
        System.out.println("Codigo:   " + codigo);
        System.out.println("Nombre:   " + nombre);
        System.out.println("Creditos: " + creditos);
        System.out.println("Cupos:    " + cuposDisponibles + "/" + cuposMaximos);
        System.out.println("==============================");
    }
}
