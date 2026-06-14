package Servicios;

import Clases.Camion;
import Clases.Paquete;
import Reader.CSVReader;

import java.util.*;

public class Servicios {
    //Completar con las estructuras y métodos privados que se requieran.
    private List<Paquete> paquetes;
    private List<Camion> camiones;
    private Map<String, Paquete> paquetesXcodigo;
    private List<Paquete> paquetesConAlimentos;
    private List<Paquete> paquetesSinAlimentos;
    private Hashtable<Integer, List<Paquete>> paquetesXUrgencia;

    /*
     * Expresar la complejidad temporal del constructor.
     */

    public Servicios(String pathCamiones, String pathPaquetes) {
        this.camiones = CSVReader.cargarCamiones(pathCamiones);
        this.paquetes = CSVReader.cargarPaquetes(pathPaquetes);

        /*
        * no me queda claro si está bien que el constructor tenga todo esto,
        * pero no se estarían generando todas las estructuras al instanciar el servicio.
        */
        for (Paquete p : paquetes) {
            this.paquetesXcodigo.put(p.getCodIdentificador(),p); //SERVICIO 1

            if(p.getContieneAlimentos()){ //SERVICIO 2
                this.paquetesConAlimentos.add(p);
            } else {
                this.paquetesSinAlimentos.add(p);
            }

            if (this.paquetesXUrgencia.containsKey(p.getUrgencia())) { //SERVICIO 3
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
     * Expresar la complejidad temporal del servicio 1.
     */
    //    Servicio 1: Dado un código de paquete (String), retornar toda la información del paquete asociado.
//    En caso de no existir, retornar null.
    public Paquete servicio1(String codigoPaquete) { // O(1)
        return paquetesXcodigo.get(codigoPaquete);
    }
    /*
     * Expresar la complejidad temporal del servicio 2.
     */
//    Servicio 2: Dado un booleano que indica si se buscan paquetes que contienen alimentos (true)
//    o que no contienen alimentos (false), retornar el listado de paquetes correspondiente.
    public List<Paquete> servicio2(boolean contieneAlimentos) { // O(1)
        if(contieneAlimentos){
            return paquetesConAlimentos;
        }
        return paquetesSinAlimentos;
    }
    /*
     * Expresar la complejidad temporal del servicio 3.
     */
//    Servicio 3: Dados dos valores enteros que representan un nivel de urgencia mínimo y máximo,
//    retornar todos los paquetes cuyo nivel de urgencia se encuentre dentro de ese rango (inclusive).
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) { // O(n) donde n es la diferencia entre urgenciaMaxima y urgenciaMinima
        List<Paquete> listaPaquetes = new ArrayList<>();
        for (int i = urgenciaMinima; i <= urgenciaMaxima; i++) {
            if(paquetesXUrgencia.containsKey(i)){
                listaPaquetes.addAll(paquetesXUrgencia.get(i));
            }
        }
        return listaPaquetes;
    }
}
