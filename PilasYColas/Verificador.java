import java.util.Stack;
import java.util.Scanner;

public class Verificador{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la expresión: ");
        String expresion = sc.nextLine();
        
        if (estaEquilibrada(expresion)) {
            System.out.println("La expresión está equilibrada.");
        } else {
            System.out.println("La expresión NO está equilibrada.");
        }
    }

    public static boolean estaEquilibrada(String cadena) {
        Stack<Character> pila = new Stack<>();
        
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            
            // Si es apertura, se apila
            if (c == '(' || c == '[' || c == '{') {
                pila.push(c);
            } 
            // Si es cierre, verificamos el tope
            else if (c == ')' || c == ']' || c == '}') {
                if (pila.isEmpty()) return false;
                
                char tope = pila.pop();
                if (!esPareja(tope, c)) return false;
            }
        }
        return pila.isEmpty();
    }

    private static boolean esPareja(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') ||
               (apertura == '[' && cierre == ']') ||
               (apertura == '{' && cierre == '}');
    }
}