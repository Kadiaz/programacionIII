import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LogisticaOp op = new LogisticaOp();
        int opcion = 0;

        do {
            System.out.println("\n--- LOGÍSTICA DISTRIBUCIONES JH ---");
            System.out.println("1. Registrar Buque"); 
            System.out.println("2. Registrar Contenedor");
            System.out.println("3. Ver Peso Total"); 
            System.out.println("4. Mostrar Mapa de Puestos");
            System.out.println("5. Listar Agrupados"); 
            System.out.println("6. Salir"); 
            System.out.print("Seleccione: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Posición de muelle (0-9): ");
                    int pMuelle = sc.nextInt();
                    System.out.print("Nombre del buque: ");
                    op.registrarBuque(pMuelle, sc.next());
                    break;
                case 2:
                    op.mostrarEstado();
                    System.out.print("Columna para el contenedor (0-9): ");
                    int col = sc.nextInt();
                    System.out.print("País de origen: ");
                    String ori = sc.next();
                    System.out.print("Peso (kg): ");
                    double w = sc.nextDouble();
                    op.registrarContenedor(col, ori, w);
                    break;
                case 3:
                    System.out.println("Peso total en puerto: " + op.calcularPesoTotal() + " kg");
                    break;
                case 4:
                    op.mostrarArea();
                    break;
                case 5: // O el número que corresponda a "Listar agrupado"
                    op.listarPorOrigen();
                    break;
                case 6 : //El programa termina
                      System.out.println("Proceso terminado");
            }
        } while (opcion != 6); 
    }

}