public class Pasajero implements Comparable<Pasajero>
{
    public enum ClasePasajero
    {
        VIP(1),
        PRIMERA_CLASE(2),
        ECONOMICA(3);

        private final int prioridad;

        ClasePasajero(int valorPrioridad)
        {
            prioridad = valorPrioridad;
        }

        public int getPrioridad()
        {
            return prioridad;
        }
    }

    private String id;
    private String nombre;
    private ClasePasajero clase;

    public static final int TIEMPO_CHECKIN   = 3;
    public static final int TIEMPO_SEGURIDAD = 2;
    public static final int TIEMPO_ABORDAJE  = 1;

    public Pasajero(String idPasajero, String nombrePasajero, ClasePasajero clasePasajero)
    {
        id     = idPasajero;
        nombre = nombrePasajero;
        clase  = clasePasajero;
    }

    @Override
    public int compareTo(Pasajero otro)
    {
        return Integer.compare(clase.getPrioridad(), otro.clase.getPrioridad());
    }

    public String getId()           { return id;     }
    public String getNombre()       { return nombre; }
    public ClasePasajero getClase() { return clase;  }

    @Override
    public String toString()
    {
        return nombre + "(" + clase + ")";
    }
}