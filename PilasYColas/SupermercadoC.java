import java.util;

public class SupermercadoC {
    public static void main(String[] args) {
        // 25 carritos disponibles inicialmente
        Queue<Integer> carritosDisponibles = new LinkedList<>();
        for (int i = 1; i <= 25; i++) carritosDisponibles.add(i);

        // 3 cajas de pago
        List<Queue<Integer>> cajas = new ArrayList<>();
        for (int i = 0; i < 3; i++) cajas.add(new LinkedList<>());

        // Simulamos la llegada de 30 clientes
        for (int clienteId = 1; clienteId <= 30; clienteId++) {
            System.out.println("Llega el cliente " + clienteId);

            // 1. Verificar carritos
            if (carritosDisponibles.isEmpty()) {
                System.out.println("  Esperando carrito...");
                // En una simulación real aquí habría un bucle de espera
            }

            Integer carrito = carritosDisponibles.poll();
            System.out.println("  Cliente " + clienteId + " tomó el carrito #" + carrito);

            // 2. Selección de caja (la que tenga menos gente)
            Queue<Integer> mejorCaja = cajas.get(0);
            int indiceCaja = 0;
            for (int j = 1; j < cajas.size(); j++) {
                if (cajas.get(j).size() < mejorCaja.size()) {
                    mejorCaja = cajas.get(j);
                    indiceCaja = j;
                }
            }
            mejorCaja.add(clienteId);
            System.out.println("  Cliente " + clienteId + " se unió a la cola de la Caja " + (indiceCaja + 1));

            // 3. Simular que alguien paga (para liberar carritos)
            if (clienteId % 3 == 0) { // Cada 3 clientes, alguien termina de pagar
                for (int c = 0; c < 3; c++) {
                    if (!cajas.get(c).isEmpty()) {
                        cajas.get(c).poll();
                        carritosDisponibles.add(100 + clienteId); // El carrito vuelve a estar libre
                        System.out.println("  ¡Un cliente pagó en Caja " + (c+1) + "! Carrito liberado.");
                    }
                }
            }
        }
    }
}