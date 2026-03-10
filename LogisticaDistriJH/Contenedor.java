public class Contenedor {
    private double peso;
    private String origen;

    public Contenedor(double peso, String origen) {
        this.peso = peso;
        this.origen = origen;
    }

    // Getters para acceder a los datos de forma segura
    public double getPeso() {
        return peso;
    }

    public String getOrigen() {
        return origen;
    }
}