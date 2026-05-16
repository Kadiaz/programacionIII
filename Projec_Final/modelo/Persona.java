package modelo;

/**
 * Clase abstracta que representa una persona en el sistema universitario.
 * Es la base de la jerarquia de herencia del proyecto.
 * 
 * @author [Karen Arenas]
 * @version 1.0
 */
public abstract class Persona {
    private String nombre;
    private String id;
    private String email;

    //Constructor de la clase Persona.
    public Persona(String nombreP, String idP, String emailP) {
        nombre = nombreP;
        id = idP;
        email = emailP;
    }

    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public String getEmail() { return email; }
    
    /**
     * Metodo abstracto que cada subclase implementa
     * para mostrar su informacion especifica.
     */
    public abstract void mostrarInformacion();
}