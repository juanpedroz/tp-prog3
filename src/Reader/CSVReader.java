package Reader;

import Clases.Camion;
import Clases.Paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Camion> cargarCamiones(String path) {
        List<Camion> camiones = new ArrayList<>();
        int cantCamiones = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linea;
            cantCamiones = Integer.parseInt(br.readLine());

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");

                int id = Integer.parseInt(partes[0]);
                String patente = partes[1];
                boolean refrigerado = partes[2].equals("1");
                int capacidad = Integer.parseInt(partes[3]);

                Camion c = new Camion(id, patente, capacidad, refrigerado);
                camiones.add(c);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println( "Cargados " + camiones.size() + " camiones de "+ cantCamiones);
        return camiones;
    }

    public static List<Paquete> cargarPaquetes(String path/*, Map<String, Paquete> mapa*/) {
        List<Paquete> paquetes = new ArrayList<>();
        int cantPaquetes = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linea;
            cantPaquetes = Integer.parseInt(br.readLine());

            //br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");

                int id = Integer.parseInt(partes[0]);
                String codigo = partes[1];
                int peso = Integer.parseInt(partes[2]);
                boolean alimentos = partes[3].equals("1");
                int urgencia = Integer.parseInt(partes[4]);

                Paquete p = new Paquete(id, codigo, peso, alimentos, urgencia);

                paquetes.add(p);
                //mapa.put(codigo, p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println( "Cargados " + paquetes.size() + " paquetes de "+ cantPaquetes);
        return paquetes;
    }
}
