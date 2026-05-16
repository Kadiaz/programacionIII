package modelo;

public abstract class Persona {
    private String nombre;
    private String id;
    private String email;

    public Persona(String nombreP, String idP, String emailP) {
        nombre = nombreP;
        id = idP;
        email = emailP;
    }

    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public String getEmail() { return email; }

    public abstract void mostrarInformacion();
}