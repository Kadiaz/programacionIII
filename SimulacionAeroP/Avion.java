/**
 * Representa un avión dentro del aeropuerto.
 * Tiempos fijos de proceso para aterrizaje y despegue.
 */
public class Avion
{
    private String matricula;
    private String aerolinea;
    private int    capacidad;

    // Tiempos fijos (en minutos)
    public static final int TIEMPO_ATERRIZAJE    = 5;
    public static final int TIEMPO_PREP_DESPEGUE = 10;
    public static final int TIEMPO_DESPEGUE      = 4;

    public Avion(String matricula, String aerolinea, int capacidad)
    {
        this.matricula  = matricula;
        this.aerolinea  = aerolinea;
        this.capacidad  = capacidad;
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