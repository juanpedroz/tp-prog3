package Servicios;

import Clases.Camion;
import Clases.Paquete;
import Reader.CSVReader;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Servicios {

    //Completar con las estructuras y métodos privados que se requieran.
    private List<Paquete> paquetes;
    private List<Camion> camiones;
    private Map<String, Paquete> paquetesXcodigo;
    private List<Paquete> paquetesConAlimentos;
    private List<Paquete> paquetesSinAlimentos;
    private Hashtable<Integer, List<Paquete>> paquetesXUrgencia;

    /*
     * Complejidad temporal: O(C + P) donde C es la cantidad de camiones y P la cantidad de paquetes.
     * La lectura de cada archivo es O(C) y O(P) respectivamente.
     * El bucle de inicialización de estructuras recorre los P paquetes haciendo operaciones O(1) en cada iteración.
     */
    public Servicios(String pathCamiones, String pathPaquetes) {
        this.camiones = CSVReader.cargarCamiones(pathCamiones);
        this.paquetes = CSVReader.cargarPaquetes(pathPaquetes);
        this.paquetesXcodigo = new Hashtable<>();
        this.paquetesConAlimentos = new ArrayList<>();
        this.paquetesSinAlimentos = new ArrayList<>();
        this.paquetesXUrgencia = new Hashtable<>();
        for (Paquete p : paquetes) {

            this.paquetesXcodigo.put(p.getCodIdentificador(),p); // Carga estructura SERVICIO 1

            if(p.getContieneAlimentos()){ // Carga estructuras SERVICIO 2
                this.paquetesConAlimentos.add(p);
            } else {
                this.paquetesSinAlimentos.add(p);
            }

            if (this.paquetesXUrgencia.containsKey(p.getUrgencia())) { // Carga estructura SERVICIO 3
                List<Paquete> listaPaquetes = paquetesXUrgencia.get(p.getUrgencia());
                listaPaquetes.add(p);
                this.paquetesXUrgencia.put(p.getUrgencia(),listaPaquetes);
            } else {
                List<Paquete> listaPaquetes = new ArrayList<>();
                listaPaquetes.add(p);
                this.paquetesXUrgencia.put(p.getUrgencia(),listaPaquetes);
            }
        }
    }
    /*
     * Complejidad temporal: O(1) promedio. Se accede directamente al paquete mediante su código en la Hashtable.
     */
    public Paquete servicio1(String codigoPaquete) {
        return paquetesXcodigo.get(codigoPaquete);
    }
    /*
     * Complejidad temporal: O(k) donde k es la cantidad de paquetes en la lista devuelta,
     * por la copia defensiva. La selección de la lista correcta es O(1).
     */
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        if(contieneAlimentos){
            return new ArrayList<>(paquetesConAlimentos);
        }
        return new ArrayList<>(paquetesSinAlimentos);
    }
    /*
     * Complejidad temporal: O(R + k) donde R = urgenciaMaxima - urgenciaMinima + 1 es el tamaño del rango
     * y k es la cantidad de paquetes en el resultado. R está acotado por 100 (rango válido 1-100),
     * por lo que en la práctica es O(k).
     */
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> listaPaquetes = new ArrayList<>();
        for (int i = urgenciaMinima; i <= urgenciaMaxima; i++) {
            if(paquetesXUrgencia.containsKey(i)){
                listaPaquetes.addAll(paquetesXUrgencia.get(i));
            }
        }
        return listaPaquetes;
    }
}
