/**
 * Representa un pasajero del aeropuerto.
 * Implementa Comparable para que PriorityQueue ordene
 * automáticamente por clase: VIP → Primera Clase → Económica
 */
public class Pasajero implements Comparable<Pasajero>
{
    // Enum con la clase del pasajero y su valor de prioridad numérica
    public enum ClasePasajero
    {
        VIP(1),
        PRIMERA_CLASE(2),
        ECONOMICA(3);

        private final int prioridad;

        ClasePasajero(int prioridad)
        {
            this.prioridad = prioridad;
        }

        public int getPrioridad()
        {
            return prioridad;
        }
    }

    private String id;
    private String nombre;
    private ClasePasajero clase;

    // Tiempos fijos de atención por proceso (en minutos)
    public static final int TIEMPO_CHECKIN   = 3;
    public static final int TIEMPO_SEGURIDAD = 2;
    public static final int TIEMPO_ABORDAJE  = 1;

    public Pasajero(String id, String nombre, ClasePasajero clase)
    {
        this.id     = id;
        this.nombre = nombre;
        this.clase  = clase;
    }

    /**
     * Permite a PriorityQueue comparar pasajeros por prioridad.
     * El menor número = mayor prioridad (VIP=1 sale primero).
     */
    @Override
    public int compareTo(Pasajero otro)
    {
        return Integer.compare(this.clase.getPrioridad(), otro.clase.getPrioridad());
    }

    public String getId()            { return id; }
    public String getNombre()        { return nombre; }
    public ClasePasajero getClase()  { return clase; }

    @Override
    public String toString()
    {
        return nombre + "(" + clase + ")";
    }
}