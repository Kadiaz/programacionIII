 import java.util.Stack;
import java.util.Scanner;

public class GestionMPilas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 5;
        // Creamos un arreglo de pilas
        Stack<Integer>[] pilas = new Stack[n];
        for (int k = 0; k < n; k++) {
            pilas[k] = new Stack<>();
        }

        System.out.println("Ingrese pares (i, j). Use i=0 para terminar.");
        while (true) {
            System.out.print("i: ");
            int i = sc.nextInt();
            if (i == 0) break;
            
            System.out.print("j: ");
            int j = sc.nextInt();

            int indicePila = Math.abs(i) - 1; // Ajustamos al índice 0-4

            if (indicePila >= 0 && indicePila < n) {
                if (i > 0) {
                    pilas[indicePila].push(j);
                    System.out.println("Insertado " + j + " en P" + (indicePila + 1));
                } else {
                    if (!pilas[indicePila].isEmpty()) {
                        pilas[indicePila].pop();
                        System.out.println("Eliminado elemento de P" + (indicePila + 1));
                    } else {
                        System.out.println("Pila P" + (indicePila + 1) + " vacía.");
                    }
                }
            } else {
                System.out.println("Índice fuera de rango (1 a 5).");
            }
        }

        // Mostrar contenido final
        System.out.println("\n--- Contenido de las Pilas ---");
        for (int k = 0; k < n; k++) {
            System.out.println("P" + (k + 1) + ": " + pilas[k]);
        }
    }
}