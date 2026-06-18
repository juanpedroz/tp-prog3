import Algoritmos.Backtracking;
import Algoritmos.Greedy;
import Clases.Solucion;
import Servicios.Servicios;

public class Main {

    public static void main(String[] args) {

        Servicios servicios = new Servicios("src/Datos/Camiones.csv", "src/Datos/Paquetes.csv");

        System.out.println("\n=== SERVICIOS ===");
        System.out.println("\n=== SERVICIO 1 ===");
        System.out.println(servicios.servicio1("P005"));
        System.out.println(servicios.servicio1("P999"));
        System.out.println("\n=== SERVICIO 2 ===");
        System.out.println(servicios.servicio2(true));
        System.out.println(servicios.servicio2(false));
        System.out.println("\n=== SERVICIO 3 ===");
        System.out.println(servicios.servicio3(1, 10));
        System.out.println(servicios.servicio3(50, 100));

        System.out.println("\n=== BACKTRACKING ===");
        Backtracking bt = new Backtracking();
        Solucion solucionBT = bt.resolver(servicios.getCamiones(), servicios.getPaquetes());
        System.out.println(solucionBT);

        System.out.println("\n=== GREEDY ===");
        Greedy greedy = new Greedy();
        Solucion solucionGreedy = greedy.resolver(servicios.getCamiones(), servicios.getPaquetes());
        System.out.println(solucionGreedy);
    }
}
