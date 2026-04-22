import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Aeropuerto
{
    private String nombre;

    private Queue<Avion>            aterrizaje;
    private Queue<Avion>            despegue;
    private Queue<Pasajero>         checkIn;
    private Queue<Pasajero>         seguridad;
    private PriorityQueue<Pasajero> abordaje;
    private Stack<Maleta>           maletas;

    private int tiempoTotal;

    public Aeropuerto(String nombreAeropuerto)
    {
        nombre      = nombreAeropuerto;
        aterrizaje  = new LinkedList<>(); //Cola FIFO
        despegue    = new LinkedList<>();
        checkIn     = new LinkedList<>();
        seguridad   = new LinkedList<>();
        abordaje    = new PriorityQueue<>(); //Cola con prioridad
        maletas     = new Stack<>(); //Pila LIFO
        tiempoTotal = 0; //Tiempo de la simulación
    }

    //Aviones 

    public void agregarAvionAterrizaje(Avion avion)
    {
        aterrizaje.add(avion);
        System.out.println("  ✈  " + avion + " ingresó a cola de aterrizaje.");
    }

    public void procesarAterrizaje()
    {
        if (aterrizaje.isEmpty())
        {
            System.out.println("  [!] No hay aviones esperando para aterrizar.");
            return;
        }
        Avion avion = aterrizaje.poll();
        tiempoTotal += Avion.tiempoAterrizaje;
        System.out.println("  >>  " + avion + " aterrizó en "
                + Avion.tiempoAterrizaje + " min. | Reloj: " + tiempoTotal + " min.");
    }

    public void agregarAvionDespegue(Avion avion)
    {
        despegue.add(avion);
        System.out.println("  ✈  " + avion + " ingresó a cola de despegue.");
    }

    public void procesarDespegue()
    {
        if (despegue.isEmpty())
        {
            System.out.println("  [!] No hay aviones esperando para despegar.");
            return;
        }
        Avion avion = despegue.poll();
        int tiempoProceso = Avion.tiempoPreDespegue + Avion.tiempoDespegue;
        tiempoTotal += tiempoProceso;
        System.out.println("  >>  " + avion + " despegó | Prep(" + Avion.tiempoPreDespegue
                + ") + Despegue(" + Avion.tiempoDespegue + ") = "
                + tiempoProceso + " min. | Reloj: " + tiempoTotal + " min.");
    }

    //Pasajeros 

    public void agregarPasajeroCheckIn(Pasajero pasajero)
    {
        checkIn.add(pasajero);
        System.out.println("  +  " + pasajero + " ingresó a check-in.");
    }

    public void procesarCheckIn()
    {
        if (checkIn.isEmpty())
        {
            System.out.println("  [!] Cola de check-in vacía.");
            return;
        }
        Pasajero pasajero = checkIn.poll();
        tiempoTotal += Pasajero.tiempoCheckIn;
        System.out.println("  >>  Check-in: " + pasajero + " atendido en "
                + Pasajero.tiempoCheckIn + " min. | Reloj: " + tiempoTotal + " min.");
        seguridad.add(pasajero);
    }

    public void procesarSeguridad()
    {
        if (seguridad.isEmpty())
        {
            System.out.println("  [!] Cola de seguridad vacía.");
            return;
        }
        Pasajero pasajero = seguridad.poll();
        tiempoTotal += Pasajero.tiempoSeguridad;
        System.out.println("  >>  Seguridad: " + pasajero + " verificado en "
                + Pasajero.tiempoSeguridad + " min. | Reloj: " + tiempoTotal + " min.");
        abordaje.add(pasajero);
    }

    public void procesarAbordaje()
    {
        if (abordaje.isEmpty())
        {
            System.out.println("  [!] Cola de abordaje vacía.");
            return;
        }
        Pasajero pasajero = abordaje.poll();
        tiempoTotal += Pasajero.tiempoAbordaje;
        System.out.println("  >>  Abordaje: " + pasajero + " abordó | clase "
                + pasajero.getClase() + " | Reloj: " + tiempoTotal + " min.");
    }

    //Maletas 

    public void cargarMaleta(Maleta maleta)
    {
        maletas.push(maleta);
        tiempoTotal += Maleta.tiempoCarga;
        System.out.println("  [↓] Cargada: " + maleta
                + " | Reloj: " + tiempoTotal + " min.");
    }

    public void descargarMaleta()
    {
        if (maletas.empty())
        {
            System.out.println("  [!] La bodega está vacía.");
            return;
        }
        Maleta maleta = maletas.pop();
        tiempoTotal += Maleta.tiempoCarga;
        System.out.println("  [↑] Descargada: " + maleta
                + " | Reloj: " + tiempoTotal + " min.");
    }

    //Estado 

    public void mostrarEstado()
    {
        System.out.println("\n  ══════════════════════════════════════════════");
        System.out.println("  " + nombre + " | Tiempo acumulado: " + tiempoTotal + " min");
        System.out.println("  ══════════════════════════════════════════════");
        System.out.println("  Aterrizaje : " + aterrizaje);
        System.out.println("  Despegue   : " + despegue);
        System.out.println("  Check-in   : " + checkIn);
        System.out.println("  Seguridad  : " + seguridad);
        System.out.println("  Abordaje   : " + abordaje);
        System.out.println("  Maletas    : " + maletas);
        System.out.println("  ══════════════════════════════════════════════\n");
    }

    public int getTiempoTotal() { return tiempoTotal; }
}