public class Simulador
{
    public static void main(String[] args)
    {
        Aeropuerto aeropuerto = new Aeropuerto("Aeropuerto El Dorado - BOG");

        //Aviones 
        Avion a1 = new Avion("AV101", "Avianca", 180);
        Avion a2 = new Avion("LA202", "LATAM",   200);
        Avion a3 = new Avion("CO303", "CopaAir",    160);

        //Pasajeros 
        Pasajero p1 = new Pasajero("P01", "Carlos",    Pasajero.ClasePasajero.ECONOMICA);
        Pasajero p2 = new Pasajero("P02", "Laura", Pasajero.ClasePasajero.VIP);
        Pasajero p3 = new Pasajero("P03", "Andres",    Pasajero.ClasePasajero.PRIMERA_CLASE);
        Pasajero p4 = new Pasajero("P04", "Maria",     Pasajero.ClasePasajero.ECONOMICA);
        Pasajero p5 = new Pasajero("P05", "Diego",     Pasajero.ClasePasajero.VIP);

        //Maletas 
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
        separador("FASE 1 — Aterrizaje  (Queue – poll FIFO)");
        // ════════════════════════════════════════════════════════
        aeropuerto.procesarAterrizaje();
        aeropuerto.procesarAterrizaje();
        aeropuerto.procesarAterrizaje();
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 2 — Check-in  (Queue – poll → add a Seguridad)");
        // ════════════════════════════════════════════════════════
        aeropuerto.procesarCheckIn();
        aeropuerto.procesarCheckIn();
        aeropuerto.procesarCheckIn();
        aeropuerto.procesarCheckIn();
        aeropuerto.procesarCheckIn();
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 3 — Seguridad  (Queue – poll → add a Abordaje)");
        // ════════════════════════════════════════════════════════
        aeropuerto.procesarSeguridad();
        aeropuerto.procesarSeguridad();
        aeropuerto.procesarSeguridad();
        aeropuerto.procesarSeguridad();
        aeropuerto.procesarSeguridad();
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 4 — Carga de maletas  (Stack – push LIFO)");
        // ════════════════════════════════════════════════════════
        aeropuerto.cargarMaleta(m1);
        aeropuerto.cargarMaleta(m2);
        aeropuerto.cargarMaleta(m3);
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 5 — Abordaje  (PriorityQueue – VIP primero)");
        // ════════════════════════════════════════════════════════
        aeropuerto.procesarAbordaje();
        aeropuerto.procesarAbordaje();
        aeropuerto.procesarAbordaje();
        aeropuerto.procesarAbordaje();
        aeropuerto.procesarAbordaje();
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 6 — Despegue  (Queue – poll + tiempo prep)");
        // ════════════════════════════════════════════════════════
        aeropuerto.agregarAvionDespegue(a1);
        aeropuerto.agregarAvionDespegue(a2);
        aeropuerto.procesarDespegue();
        aeropuerto.procesarDespegue();
        aeropuerto.mostrarEstado();

        // ════════════════════════════════════════════════════════
        separador("FASE 7 — Descarga de maletas  (Stack – pop LIFO)");
        // ════════════════════════════════════════════════════════
        aeropuerto.descargarMaleta();
        aeropuerto.descargarMaleta();
        aeropuerto.descargarMaleta();
        aeropuerto.mostrarEstado();

        separador("SIMULACIÓN COMPLETADA");
        System.out.println("  Tiempo total simulado: "
                + aeropuerto.getTiempoTotal() + " minutos.\n");
    }

    private static void separador(String titulo)
    {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║  " + titulo);
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}