/**
 * ═══════════════════════════════════════════════════════════
 *  SIMULACIÓN DE AEROPUERTO — Parcial II AF2-P2
 *
 *  Estructuras usadas (java.util):
 *    Queue + LinkedList  → colaAterrizaje, colaDespegue,
 *                          colaCheckIn, colaSeguridad
 *    PriorityQueue       → colaAbordaje (VIP > Primera > Económica)
 *    Stack               → pilaMaletas  (bodega LIFO)
 *
 *  Flujo de la simulación:
 *    1. Aterrizaje de aviones      (Queue FIFO)
 *    2. Check-in de pasajeros      (Queue FIFO → Seguridad)
 *    3. Control de seguridad       (Queue FIFO → Abordaje)
 *    4. Carga de maletas           (Stack push – LIFO)
 *    5. Abordaje con prioridad     (PriorityQueue – VIP primero)
 *    6. Despegue                   (Queue FIFO + tiempo prep)
 *    7. Descarga de maletas        (Stack pop – LIFO)
 * ═══════════════════════════════════════════════════════════
 */
public class Simulador
{
    public static void main(String[] args)
    {
        Aeropuerto aeropuerto = new Aeropuerto("Aeropuerto El Dorado - BOG");

        // ── Crear aviones 
        Avion a1 = new Avion("AV101", "Avianca", 180);
        Avion a2 = new Avion("LA202", "LATAM",   200);
        Avion a3 = new Avion("CO303", "Copa",    160);

        // ── Crear pasajeros 
        // Llegan en este orden al aeropuerto, pero el abordaje
        // respeta la prioridad de clase, NO el orden de llegada
        Pasajero p1 = new Pasajero("P01", "Carlos",    Pasajero.ClasePasajero.ECONOMICA);
        Pasajero p2 = new Pasajero("P02", "Valentina", Pasajero.ClasePasajero.VIP);
        Pasajero p3 = new Pasajero("P03", "Andres",    Pasajero.ClasePasajero.PRIMERA_CLASE);
        Pasajero p4 = new Pasajero("P04", "Maria",     Pasajero.ClasePasajero.ECONOMICA);
        Pasajero p5 = new Pasajero("P05", "Diego",     Pasajero.ClasePasajero.VIP);

        // ── Crear maletas 
        Maleta m1 = new Maleta("M01", 22.5, p1);
        Maleta m2 = new Maleta("M02", 18.0, p2);
        Maleta m3 = new Maleta("M03", 25.0, p3);

        // ════════════════════════════════════════════════════════
        separador("FASE 0 — Registro inicial");
        // ════════════════════════════════════════════════════════
        aeropuerto.agregarAvionAterrizaje(a1);
        aeropuerto.agregarAvionAterrizaje(a2);
        aeropuerto.agregarAvionAterrizaje(a3);

        aeropuerto.agregarPasajeroCheckIn(p1);
        aeropuerto.agregarPasajeroCheckIn(p2);
        aeropuerto.agregarPasajeroCheckIn(p3);
        aeropuerto.agregarPasajeroCheckIn(p4);
        aeropuerto.agregarPasajeroCheckIn(p5);

        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 1 — Aterrizaje de aviones  (Queue – poll)");
        // ════════════════════════════════════════════════════════
        aeropuerto.procesarAterrizaje(); // AV101 primero en llegar, primero en aterrizar
        aeropuerto.procesarAterrizaje(); // LA202
        aeropuerto.procesarAterrizaje(); // CO303
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 2 — Check-in  (Queue – poll → add a Seguridad)");
        // ════════════════════════════════════════════════════════
        aeropuerto.procesarCheckIn(); // Carlos
        aeropuerto.procesarCheckIn(); // Valentina
        aeropuerto.procesarCheckIn(); // Andres
        aeropuerto.procesarCheckIn(); // Maria
        aeropuerto.procesarCheckIn(); // Diego
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 3 — Seguridad  (Queue – poll → add a Abordaje)");
        // ════════════════════════════════════════════════════════
        // Aunque pasan en orden FIFO por seguridad,
        // PriorityQueue los reordena al entrar a la puerta
        aeropuerto.procesarSeguridad(); // Carlos   → colaAbordaje
        aeropuerto.procesarSeguridad(); // Valentina → colaAbordaje (VIP sube al frente)
        aeropuerto.procesarSeguridad(); // Andres
        aeropuerto.procesarSeguridad(); // Maria
        aeropuerto.procesarSeguridad(); // Diego
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 4 — Carga de maletas  (Stack – push)");
        // ════════════════════════════════════════════════════════
        // M01 entra primero → quedará en la base de la pila
        aeropuerto.cargarMaleta(m1); // M01 → base
        aeropuerto.cargarMaleta(m2); // M02
        aeropuerto.cargarMaleta(m3); // M03 → tope (saldrá primero)
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 5 — Abordaje  (PriorityQueue – poll por prioridad)");
        // ════════════════════════════════════════════════════════
        // Sin importar el orden de llegada al aeropuerto,
        // VIP siempre aborda primero
        aeropuerto.procesarAbordaje(); // Valentina (VIP)
        aeropuerto.procesarAbordaje(); // Diego     (VIP)
        aeropuerto.procesarAbordaje(); // Andres    (Primera Clase)
        aeropuerto.procesarAbordaje(); // Carlos    (Económica)
        aeropuerto.procesarAbordaje(); // Maria     (Económica)
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 6 — Despegue  (Queue – poll + tiempo prep)");
        // ════════════════════════════════════════════════════════
        aeropuerto.agregarAvionDespegue(a1);
        aeropuerto.agregarAvionDespegue(a2);
        aeropuerto.procesarDespegue(); // AV101: prep(10) + despegue(4) = 14 min
        aeropuerto.procesarDespegue(); // LA202
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 7 — Descarga de maletas  (Stack – pop LIFO)");
        // ════════════════════════════════════════════════════════
        // Sale en orden inverso a la carga: M03 → M02 → M01
        aeropuerto.descargarMaleta(); // M03 (tope)
        aeropuerto.descargarMaleta(); // M02
        aeropuerto.descargarMaleta(); // M01 (base)
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("SIMULACIÓN COMPLETADA");
        // ════════════════════════════════════════════════════════
        System.out.println("  Tiempo total simulado: "
                + aeropuerto.getTiempoTotal() + " minutos.\n");
    }

    /** Imprime un separador visual entre fases */
    private static void separador(String titulo)
    {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║  " + titulo);
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}