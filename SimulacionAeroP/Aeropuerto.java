import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Clase central del aeropuerto.
 * Coordina todas las estructuras de datos usando java.util:
 *
 *   Queue   → colaAterrizaje, colaDespegue, colaCheckIn, colaSeguridad
 *   PriorityQueue → colaAbordaje  (VIP → Primera Clase → Económica)
 *   Stack   → pilaMaletas         (bodega del avión, LIFO)
 */
public class Aeropuerto
{
    private String nombre;

    // ── Colas de aviones (FIFO)
    private Queue<Avion> colaAterrizaje;
    private Queue<Avion> colaDespegue;

    // ── Colas de pasajeros (FIFO) 
    private Queue<Pasajero> colaCheckIn;
    private Queue<Pasajero> colaSeguridad;

    // ── Cola de abordaje con prioridad 
    // PriorityQueue usa compareTo() de Pasajero: VIP(1) > Primera(2) > Económica(3)
    private PriorityQueue<Pasajero> colaAbordaje;

    // ── Bodega del avión (LIFO) 
    private Stack<Maleta> pilaMaletas;

    // Reloj interno de la simulación (minutos acumulados)
    private int tiempoTotal;

    public Aeropuerto(String nombre)
    {
        this.nombre          = nombre;
        this.colaAterrizaje  = new LinkedList<>();
        this.colaDespegue    = new LinkedList<>();
        this.colaCheckIn     = new LinkedList<>();
        this.colaSeguridad   = new LinkedList<>();
        this.colaAbordaje    = new PriorityQueue<>();
        this.pilaMaletas     = new Stack<>();
        this.tiempoTotal     = 0;
    }

    //  AVIONES
    /** Agrega un avión a la cola de aterrizaje */
    public void agregarAvionAterrizaje(Avion avion)
    {
        colaAterrizaje.add(avion);
        System.out.println("  ✈  " + avion + " ingresó a cola de aterrizaje.");
    }

    /**
     * Atiende al primer avión en cola de aterrizaje (poll = desencolar).
     * Suma el tiempo fijo de aterrizaje al reloj.
     */
    public void procesarAterrizaje()
    {
        if (colaAterrizaje.isEmpty())
        {
            System.out.println("  [!] No hay aviones esperando para aterrizar.");
            return;
        }
        Avion avion = colaAterrizaje.poll();
        tiempoTotal += Avion.TIEMPO_ATERRIZAJE;
        System.out.println("  >>  " + avion + " aterrizó en "
                + Avion.TIEMPO_ATERRIZAJE + " min. | Reloj: " + tiempoTotal + " min.");
    }

    /** Agrega un avión a la cola de despegue */
    public void agregarAvionDespegue(Avion avion)
    {
        colaDespegue.add(avion);
        System.out.println("  ✈  " + avion + " ingresó a cola de despegue.");
    }

    /**
     * Atiende al primer avión en cola de despegue.
     * Tiempo total = preparación + despegue.
     */
    public void procesarDespegue()
    {
        if (colaDespegue.isEmpty())
        {
            System.out.println("  [!] No hay aviones esperando para despegar.");
            return;
        }
        Avion avion = colaDespegue.poll();
        int tiempoProceso = Avion.TIEMPO_PREP_DESPEGUE + Avion.TIEMPO_DESPEGUE;
        tiempoTotal += tiempoProceso;
        System.out.println("  >>  " + avion + " despegó | Prep(" + Avion.TIEMPO_PREP_DESPEGUE
                + ") + Despegue(" + Avion.TIEMPO_DESPEGUE + ") = "
                + tiempoProceso + " min. | Reloj: " + tiempoTotal + " min.");
    }

    //  PASAJEROS
    /** Agrega un pasajero a la cola de check-in */
    public void agregarPasajeroCheckIn(Pasajero p)
    {
        colaCheckIn.add(p);
        System.out.println("  +  " + p + " ingresó a check-in.");
    }

    /**
     * Atiende al siguiente pasajero en check-in (poll).
     * Lo mueve automáticamente a la cola de seguridad.
     */
    public void procesarCheckIn()
    {
        if (colaCheckIn.isEmpty())
        {
            System.out.println("  [!] Cola de check-in vacía.");
            return;
        }
        Pasajero p = colaCheckIn.poll();
        tiempoTotal += Pasajero.TIEMPO_CHECKIN;
        System.out.println("  >>  Check-in: " + p + " atendido en "
                + Pasajero.TIEMPO_CHECKIN + " min. | Reloj: " + tiempoTotal + " min.");
        colaSeguridad.add(p);   // pasa a seguridad
    }

    /**
     * Atiende al siguiente pasajero en seguridad (poll).
     * Lo mueve a la cola de abordaje con prioridad.
     */
    public void procesarSeguridad()
    {
        if (colaSeguridad.isEmpty())
        {
            System.out.println("  [!] Cola de seguridad vacía.");
            return;
        }
        Pasajero p = colaSeguridad.poll();
        tiempoTotal += Pasajero.TIEMPO_SEGURIDAD;
        System.out.println("  >>  Seguridad: " + p + " verificado en "
                + Pasajero.TIEMPO_SEGURIDAD + " min. | Reloj: " + tiempoTotal + " min.");
        colaAbordaje.add(p);    // PriorityQueue lo ubica según compareTo()
    }

    /**
     * Aborda al pasajero de mayor prioridad (poll en PriorityQueue).
     * VIP siempre sale primero, sin importar el orden de llegada.
     */
    public void procesarAbordaje()
    {
        if (colaAbordaje.isEmpty())
        {
            System.out.println("  [!] Cola de abordaje vacía.");
            return;
        }
        Pasajero p = colaAbordaje.poll();
        tiempoTotal += Pasajero.TIEMPO_ABORDAJE;
        System.out.println("  >>  Abordaje: " + p + " abordó | clase "
                + p.getClase() + " | Reloj: " + tiempoTotal + " min.");
    }

    //  MALETAS  (Stack – LIFO)
    /** Carga una maleta en la bodega (push) */
    public void cargarMaleta(Maleta maleta)
    {
        pilaMaletas.push(maleta);
        tiempoTotal += Maleta.TIEMPO_CARGA;
        System.out.println("  [↓] Cargada: " + maleta
                + " | Reloj: " + tiempoTotal + " min.");
    }

    /** Descarga la última maleta cargada (pop – LIFO) */
    public void descargarMaleta()
    {
        if (pilaMaletas.empty())
        {
            System.out.println("  [!] La bodega está vacía.");
            return;
        }
        Maleta maleta = pilaMaletas.pop();
        tiempoTotal += Maleta.TIEMPO_CARGA;
        System.out.println("  [↑] Descargada: " + maleta
                + " | Reloj: " + tiempoTotal + " min.");
    }

    //  ESTADO GENERAL
    public void mostrarEstado()
    {
        System.out.println("\n  ══════════════════════════════════════════════");
        System.out.println("  " + nombre + " | Tiempo acumulado: " + tiempoTotal + " min");
        System.out.println("  ══════════════════════════════════════════════");
        System.out.println("  Cola Aterrizaje  : " + colaAterrizaje);
        System.out.println("  Cola Despegue    : " + colaDespegue);
        System.out.println("  Cola Check-in    : " + colaCheckIn);
        System.out.println("  Cola Seguridad   : " + colaSeguridad);
        System.out.println("  Cola Abordaje    : " + colaAbordaje);
        System.out.println("  Pila Maletas     : " + pilaMaletas);
        System.out.println("  ══════════════════════════════════════════════\n");
    }

    public int getTiempoTotal() { return tiempoTotal; }
}