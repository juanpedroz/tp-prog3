import Clases.Camion;
import Clases.Paquete;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Camion> camiones = CSVReader.cargarCamiones("src/Datos/Camiones.csv");
        List<Paquete> paquetes = CSVReader.cargarPaquetes("src/Datos/Paquetes.csv");

        System.out.println("\n=== CAMIONES ===");
        for (Camion c : camiones) {
            System.out.println(c);
        }

        System.out.println("\n=== PAQUETES ===");
        for (Paquete p : paquetes) {
            System.out.println(p);
        }

    }
}
