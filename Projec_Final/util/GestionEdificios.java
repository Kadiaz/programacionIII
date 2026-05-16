package util;
// GestionEdificios.java
public class GestionEdificios {

    private String[] edificios;
    private int[][] distancias;
    private int numEdificios;
    private static final int INFINITO = Integer.MAX_VALUE / 2;

    public GestionEdificios() {
        numEdificios = 5;
        edificios = new String[numEdificios];
        distancias = new int[numEdificios][numEdificios];

        // Inicializar matriz con INFINITO (sin conexion)
        for (int i = 0; i < numEdificios; i++) {
            for (int j = 0; j < numEdificios; j++) {
                if (i == j) {
                    distancias[i][j] = 0;
                } else {
                    distancias[i][j] = INFINITO;
                }
            }
        }

        // Edificios predefinidos
        edificios[0] = "Ingenieria";
        edificios[1] = "Biblioteca";
        edificios[2] = "Cafeteria";
        edificios[3] = "Rectoria";
        edificios[4] = "Laboratorios";
    }

    // 1. Agregar conexion entre edificios
    public void agregarConexion(int origenP, int destinoP, int distanciaP) {
        if (origenP < 0 || origenP >= numEdificios || 
            destinoP < 0 || destinoP >= numEdificios) {
            System.out.println("Error: Indice de edificio invalido.");
            return;
        }
        distancias[origenP][destinoP] = distanciaP;
        distancias[destinoP][origenP] = distanciaP; // grafo no dirigido
        System.out.println("Conexion agregada: " + edificios[origenP] + 
            " <-> " + edificios[destinoP] + " (" + distanciaP + "m)");
    }

    // 2. Mostrar edificios
    public void mostrarEdificios() {
        System.out.println("\n--- EDIFICIOS ---");
        for (int i = 0; i < numEdificios; i++) {
            System.out.println(i + ": " + edificios[i]);
        }
    }

    // 3. Algoritmo Dijkstra
    public void calcularRutaMasCorta(int origenP, int destinoP) {
        int[] distancia = new int[numEdificios];
        boolean[] visitado = new boolean[numEdificios];
        int[] anterior = new int[numEdificios];

        // Inicializar
        for (int i = 0; i < numEdificios; i++) {
            distancia[i] = INFINITO;
            visitado[i] = false;
            anterior[i] = -1;
        }
        distancia[origenP] = 0;

        // Dijkstra
        for (int i = 0; i < numEdificios; i++) {
            // Encontrar el nodo no visitado con menor distancia
            int u = -1;
            for (int j = 0; j < numEdificios; j++) {
                if (!visitado[j] && (u == -1 || distancia[j] < distancia[u])) {
                    u = j;
                }
            }

            if (distancia[u] == INFINITO) break;
            visitado[u] = true;

            // Actualizar distancias vecinos
            for (int v = 0; v < numEdificios; v++) {
                if (distancias[u][v] != INFINITO && !visitado[v]) {
                    int nuevaDistancia = distancia[u] + distancias[u][v];
                    if (nuevaDistancia < distancia[v]) {
                        distancia[v] = nuevaDistancia;
                        anterior[v] = u;
                    }
                }
            }
        }

        // Mostrar resultado
        if (distancia[destinoP] == INFINITO) {
            System.out.println("No existe ruta entre " + 
                edificios[origenP] + " y " + edificios[destinoP]);
            return;
        }

        // Reconstruir ruta
        System.out.println("\n--- RESULTADO ---");
        System.out.print("Ruta mas corta: ");
        imprimirRuta(anterior, origenP, destinoP, distancias);
        System.out.println("\nDistancia TOTAL: " + distancia[destinoP] + " metros");
    }

    // Metodo auxiliar para imprimir la ruta
    private void imprimirRuta(int[] anteriorP, int origenP, 
                               int actualP, int[][] distanciasP) {
        if (actualP == origenP) {
            System.out.print(edificios[origenP]);
            return;
        }
        imprimirRuta(anteriorP, origenP, anteriorP[actualP], distanciasP);
        System.out.print(" -> " + edificios[actualP] + 
            " (" + distanciasP[anteriorP[actualP]][actualP] + "m)");
    }

    // 4. Mostrar matriz de distancias
    public void mostrarMatriz() {
        System.out.println("\n--- MATRIZ DE DISTANCIAS ---");
        System.out.printf("%15s", "");
        for (int i = 0; i < numEdificios; i++) {
            System.out.printf("%15s", edificios[i]);
        }
        System.out.println();
        for (int i = 0; i < numEdificios; i++) {
            System.out.printf("%15s", edificios[i]);
            for (int j = 0; j < numEdificios; j++) {
                if (distancias[i][j] == INFINITO) {
                    System.out.printf("%15s", "INF");
                } else {
                    System.out.printf("%15d", distancias[i][j]);
                }
            }
            System.out.println();
        }
    }
}
