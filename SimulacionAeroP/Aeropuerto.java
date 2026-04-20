import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Aeropuerto
{
    private String nombre;

    private Queue<Avion>           colaAterrizaje;
    private Queue<Avion>           colaDespegue;
    private Queue<Pasajero>        colaCheckIn;
    private Queue<Pasajero>        colaSeguridad;
    private PriorityQueue<Pasajero> colaAbordaje;
    private Stack<Maleta>          pilaMaletas;

    private int tiempoTotal;

    public Aeropuerto(String nombreAeropuerto)
    {
        nombre         = nombreAeropuerto;
        colaAterrizaje = new LinkedList<>();
        colaDespegue   = new LinkedList<>();
        colaCheckIn    = new LinkedList<>();
        colaSeguridad  = new LinkedList<>();
        colaAbordaje   = new PriorityQueue<>();
        pilaMaletas    = new Stack<>();
        tiempoTotal    = 0;
    }

    // ── Aviones 

    public void agregarAvionAterrizaje(Avion avion)
    {
        colaAterrizaje.add(avion);
        System.out.println("  ✈  " + avion + " ingresó a cola de aterrizaje.");
    }

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

    public void agregarAvionDespegue(Avion avion)
    {
        colaDespegue.add(avion);
        System.out.println("  ✈  " + avion + " ingresó a cola de despegue.");
    }

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

    // ── Pasajeros 

    public void agregarPasajeroCheckIn(Pasajero pasajero)
    {
        colaCheckIn.add(pasajero);
        System.out.println("  +  " + pasajero + " ingresó a check-in.");
    }

    public void procesarCheckIn()
    {
        if (colaCheckIn.isEmpty())
        {
            System.out.println("  [!] Cola de check-in vacía.");
            return;
        }
        Pasajero pasajero = colaCheckIn.poll();
        tiempoTotal += Pasajero.TIEMPO_CHECKIN;
        System.out.println("  >>  Check-in: " + pasajero + " atendido en "
                + Pasajero.TIEMPO_CHECKIN + " min. | Reloj: " + tiempoTotal + " min.");
        colaSeguridad.add(pasajero);
    }

    public void procesarSeguridad()
    {
        if (colaSeguridad.isEmpty())
        {
            System.out.println("  [!] Cola de seguridad vacía.");
            return;
        }
        Pasajero pasajero = colaSeguridad.poll();
        tiempoTotal += Pasajero.TIEMPO_SEGURIDAD;
        System.out.println("  >>  Seguridad: " + pasajero + " verificado en "
                + Pasajero.TIEMPO_SEGURIDAD + " min. | Reloj: " + tiempoTotal + " min.");
        colaAbordaje.add(pasajero);
    }

    public void procesarAbordaje()
    {
        if (colaAbordaje.isEmpty())
        {
            System.out.println("  [!] Cola de abordaje vacía.");
            return;
        }
        Pasajero pasajero = colaAbordaje.poll();
        tiempoTotal += Pasajero.TIEMPO_ABORDAJE;
        System.out.println("  >>  Abordaje: " + pasajero + " abordó | clase "
                + pasajero.getClase() + " | Reloj: " + tiempoTotal + " min.");
    }

    // ── Maletas 

    public void cargarMaleta(Maleta maleta)
    {
        pilaMaletas.push(maleta);
        tiempoTotal += Maleta.TIEMPO_CARGA;
        System.out.println("  [↓] Cargada: " + maleta
                + " | Reloj: " + tiempoTotal + " min.");
    }

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

    // ── Estado 

    public void mostrarEstado()
    {
        System.out.println("\n  ══════════════════════════════════════════════");
        System.out.println("  " + nombre + " | Tiempo acumulado: " + tiempoTotal + " min");
        System.out.println("  ══════════════════════════════════════════════");
        System.out.println("  Cola Aterrizaje : " + colaAterrizaje);
        System.out.println("  Cola Despegue   : " + colaDespegue);
        System.out.println("  Cola Check-in   : " + colaCheckIn);
        System.out.println("  Cola Seguridad  : " + colaSeguridad);
        System.out.println("  Cola Abordaje   : " + colaAbordaje);
        System.out.println("  Pila Maletas    : " + pilaMaletas);
        System.out.println("  ══════════════════════════════════════════════\n");
    }

    public int getTiempoTotal() { return tiempoTotal; }
}