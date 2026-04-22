public class Avion
{
    private String matricula;
    private String aerolinea;
    private int    capacidad;

    public static final int tiempoAterrizaje    = 5;
    public static final int tiempoPreDespegue = 10;
    public static final int tiempoDespegue     = 4;

    public Avion(String matriculaAvion, String aerolineaAvion, int capacidadAvion)
    {
        matricula = matriculaAvion;
        aerolinea = aerolineaAvion;
        capacidad = capacidadAvion;
    }

    public String getMatricula() { return matricula; }
    public String getAerolinea() { return aerolinea; }
    public int    getCapacidad() { return capacidad;  }

    @Override //Verifica el método del compareTo
    public String toString()
    {
        return matricula + "[" + aerolinea + "]";
    }
}