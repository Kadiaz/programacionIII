import java.util;

public class SimulacionEsperanza {
    public static void main(String[] args) {
        double tiempoTotal = 7 * 60; // 7 horas en minutos
        double tiempoActual = 0;
        
        Queue<Double> filaUnica = new LinkedList<>();
        int atendidos = 0;
        double tiempoMaxEspera = 0;
        double sumaTamanosFila = 0;
        int maxFila = 0;
        int conteoMuestras = 0;
        
        // Tiempos de liberación de las 4 cajas (0 significa libre)
        double[] cajasFinAtencion = new double[4]; 
        boolean cuartaCajaAbierta = false;
        double tiempoCuartaCaja = 0;

        while (tiempoActual < tiempoTotal) {
            // 1. Llegada de clientes (cada 1 minuto promedio)
            if (Math.random() < 1.0) { // Simplificado: llega uno por minuto
                filaUnica.add(tiempoActual);
            }

            // 2. Lógica de la cuarta caja
            if (filaUnica.size() > 20) {
                if (!cuartaCajaAbierta) cuartaCajaAbierta = true;
            } else if (filaUnica.isEmpty()) {
                cuartaCajaAbierta = false;
            }
            
            if (cuartaCajaAbierta) tiempoCuartaCaja += 0.1; // Incremento de simulación

            // 3. Atender clientes
            int cajasActivas = cuartaCajaAbierta ? 4 : 3;
            for (int i = 0; i < cajasActivas; i++) {
                if (cajasFinAtencion[i] <= tiempoActual && !filaUnica.isEmpty()) {
                    double inicioEspera = filaUnica.poll();
                    double espera = tiempoActual - inicioEspera;
                    if (espera > tiempoMaxEspera) tiempoMaxEspera = espera;
                    
                    // Asignar tiempo de atención según la caja (Reglas del ejercicio)
                    double duracion = generarTiempo(i);
                    cajasFinAtencion[i] = tiempoActual + duracion;
                    atendidos++;
                }
            }

            // Estadísticas
            sumaTamanosFila += filaUnica.size();
            if (filaUnica.size() > maxFila) maxFila = filaUnica.size();
            conteoMuestras++;
            
            tiempoActual += 0.1; // Avanzamos en intervalos pequeños
        }

        System.out.println("--- ESTADÍSTICAS ---");
        System.out.println("Clientes atendidos: " + atendidos);
        System.out.println("Tamaño medio de fila: " + (sumaTamanosFila / conteoMuestras));
        System.out.println("Tamaño máximo de fila: " + maxFila);
        System.out.println("Tiempo máximo de espera: " + tiempoMaxEspera + " min");
        System.out.println("Tiempo abierta la 4ta caja: " + tiempoCuartaCaja + " min");
    }

    private static double generarTiempo(int caja) {
        switch (caja) {
            case 0: return 1.5 + (Math.random() * 1.0); // 1.5 a 2.5
            case 1: return 2.0 + (Math.random() * 3.0); // 2 a 5
            case 2: return 2.0 + (Math.random() * 2.0); // 2 a 4
            default: return 2.0 + (Math.random() * 2.5); // 2 a 4.5
        }
    }
}