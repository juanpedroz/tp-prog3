import Clases.Camion;
import Clases.Paquete;
import Reader.CSVReader;
import Servicios.Servicios;

import java.util.List;

public class Main {

    public static void main(String[] args) {
//        List<Camion> camiones = CSVReader.cargarCamiones("src/Datos/Camiones.csv");
//        List<Paquete> paquetes = CSVReader.cargarPaquetes("src/Datos/Paquetes.csv");

        Servicios servicios = new Servicios("src/Datos/Camiones.csv","src/Datos/Paquetes.csv");

//        System.out.println("\n=== CAMIONES ===");
//        for (Camion c : camiones) {
//            System.out.println(c);
//        }
//
//        System.out.println("\n=== PAQUETES ===");
//        for (Paquete p : paquetes) {
//            System.out.println(p);
//        }

        System.out.println("\n=== SERVICIOS ===");
        System.out.println(servicios.servicio1("P005"));
        System.out.println(servicios.servicio1("P999")); // debería dar null

        System.out.println(servicios.servicio2(true));
        System.out.println(servicios.servicio2(false));

        System.out.println(servicios.servicio3(1, 10));
        System.out.println(servicios.servicio3(50, 100));

    }
}
