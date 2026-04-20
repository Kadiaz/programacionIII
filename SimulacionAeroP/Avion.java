public class Avion
{
    private String matricula;
    private String aerolinea;
    private int    capacidad;

    public static final int TIEMPO_ATERRIZAJE    = 5;
    public static final int TIEMPO_PREP_DESPEGUE = 10;
    public static final int TIEMPO_DESPEGUE      = 4;

    public Avion(String matriculaAvion, String aerolineaAvion, int capacidadAvion)
    {
        matricula = matriculaAvion;
        aerolinea = aerolineaAvion;
        capacidad = capacidadAvion;
    }

    public String getMatricula() { return matricula; }
    public String getAerolinea() { return aerolinea; }
    public int    getCapacidad() { return capacidad;  }

    @Override
    public String toString()
    {
        return matricula + "[" + aerolinea + "]";
    }
}