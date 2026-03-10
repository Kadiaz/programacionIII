import java.util.Scanner;

public class LogisticaOp {
    // Matriz de 10x10 para la visualización (etiquetas como [C], [B])
    private String[][] area = new String[10][10];
    
    // Array de 10 posiciones para los nombres de los buques
    private String[] muelle = new String[10];

    // Matriz paralela de 10x10 para almacenar los pesos numéricos
    private double[][] pesos = new double[10][10];

    public LogisticaOp() {
        // Inicializar área con espacios vacíos
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) area[i][j] = "[ ]";
        }
    }

    public void registrarBuque(int posicion, String nombre) {
        if (posicion >= 0 && posicion < 10) {
            if (muelle[posicion] == null) {
                muelle[posicion] = nombre;
                System.out.println("Buque registrado en muelle " + posicion);
            } else {
                System.out.println("¡ADVERTENCIA! El muelle " + posicion + " ya está ocupado.");
            }
        } else {
            System.out.println("Posición fuera de rango (0-9).");
        }
    }

    public void registrarContenedor(int columna, String origen, double peso) {
        boolean asignado = false;
        // Lógica de apilado: de la fila 9 (suelo) hacia la 0 (aire)
        for (int i = 9; i >= 0; i--) {
            if (area[i][columna].equals("[ ]")) {
                area[i][columna] = "[" + origen.substring(0, 1).toUpperCase() + "]";
                pesos[i][columna] = peso; 
                
                asignado = true;
                System.out.println("Contenedor ubicado en Fila " + i + ", Columna " + columna);
                break;
            }
        }
        if (!asignado) System.out.println("¡ADVERTENCIA! La columna " + columna + " está llena."); 
    }

    public void mostrarArea() {
        System.out.println("\n--- ESTADO ACTUAL DEL ÁREA (10x10) ---");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(area[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Método para que el Main funcione si llamas a mostrarEstado
    public void mostrarEstado() {
        mostrarArea();
    }

    public void listarPorOrigen() {
        System.out.println("\n--- CONTENEDORES AGRUPADOS POR ORIGEN ---");
        String origenVisto = ""; 
        boolean hayContenedores = false;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!area[i][j].equals("[ ]")) {
                    String origenActual = area[i][j];
                    hayContenedores = true;
                    
                    if (!origenVisto.contains(origenActual)) {
                        int contador = contarPorOrigen(origenActual);
                        System.out.println("Origen " + origenActual + ": " + contador + " contenedores.");
                        origenVisto += origenActual; 
                    }
                }
            }
        }
        if (!hayContenedores) System.out.println("No hay contenedores registrados.");
    }

    private int contarPorOrigen(String origen) {
        int cont = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (area[i][j].equals(origen)) cont++;
            }
        }
        return cont;
    }

    public double calcularPesoTotal() {
        double total = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) total += pesos[i][j];
        }
        return total;
    }
}