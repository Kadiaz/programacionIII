// Persona.java
public abstract class Persona {
    private String nombre;
    private String id;
    private String email;

    public Persona(String nombre, String id, String email) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public String getEmail() { return email; }

    // Método abstracto que cada hijo debe sobrescribir
    public abstract void mostrarInformacion();
}