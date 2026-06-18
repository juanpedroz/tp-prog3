package Algoritmos;

import Clases.Camion;
import Clases.Paquete;
import Clases.Solucion;

import java.util.*;

public class Backtracking {

    private List<Camion> camiones;
    private List<Paquete> paquetes;
    private int estadosGenerados;
    private int mejorPesoNoAsignado;
    private Map<Camion, List<Paquete>> mejorAsignacion;
    private List<Paquete> mejoresNoAsignados;

    /*
     * Se intenta asignar cada paquete de a uno, probando colocarlo en cada camión válido
     * o dejarlo sin asignar. Se exploran todas las combinaciones posibles y se guarda
     * la asignación que minimiza el peso total no asignado.
     */
    public Solucion resolver(List<Camion> camiones, List<Paquete> paquetes) {
        this.camiones = camiones;
        this.paquetes = paquetes;
        this.estadosGenerados = 0; // Contador de estados generados
        this.mejorPesoNoAsignado = calcularPesoTotal();
        this.mejoresNoAsignados = new ArrayList<>(paquetes);
        this.mejorAsignacion = new HashMap<>();
        for (Camion c : camiones) {
            this.mejorAsignacion.put(c, new ArrayList<>());
        }

        int[] capacidadRestante = new int[camiones.size()];
        for (int i = 0; i < camiones.size(); i++) {
            capacidadRestante[i] = camiones.get(i).getCapacidad();
        }

        Map<Camion, List<Paquete>> asignacionActual = new HashMap<>();
        for (Camion c : camiones) {
            asignacionActual.put(c, new ArrayList<>());
        }

        bt(0, capacidadRestante, asignacionActual, new ArrayList<>(), 0);

        return new Solucion(mejorAsignacion, mejoresNoAsignados, estadosGenerados, "Estados generados");
    }

    private void bt(int indice, int[] capacidadRestante, Map<Camion, List<Paquete>> asignacionActual, List<Paquete> noAsignadosActual, int pesoNoAsignadoActual) {
        estadosGenerados++;

            // chequeo esto antes de preguntar si es solucion y no dentro asi corto cuando ya supera el peso no asignado (poda)
         if (pesoNoAsignadoActual >= mejorPesoNoAsignado) {
             return;

         }

        if (indice == paquetes.size()) {
            mejorPesoNoAsignado = pesoNoAsignadoActual;
            guardarMejorSolucion(asignacionActual, noAsignadosActual);
            return;
        }

        Paquete paquete = paquetes.get(indice);

        // probar asignar el paquete a cada camión válido
        for (int i = 0; i < camiones.size(); i++) {
            Camion camion = camiones.get(i);
            if ((!paquete.getContieneAlimentos() || camion.isRefrigerado()) && capacidadRestante[i] >= paquete.getPeso()) {
                
                capacidadRestante[i] -= paquete.getPeso();
                asignacionActual.get(camion).add(paquete);
                bt(indice + 1, capacidadRestante, asignacionActual, noAsignadosActual, pesoNoAsignadoActual);
                capacidadRestante[i] += paquete.getPeso();
                asignacionActual.get(camion).remove(paquete);
            }
        }

        // deja el paquete sin asignar
        noAsignadosActual.add(paquete);
        bt(indice + 1, capacidadRestante, asignacionActual, noAsignadosActual, pesoNoAsignadoActual + paquete.getPeso());
        noAsignadosActual.remove(noAsignadosActual.size() - 1);
    }

    private void guardarMejorSolucion(Map<Camion, List<Paquete>> asignacionActual, List<Paquete> noAsignadosActual) {
        mejorAsignacion = new HashMap<>();
        for (Camion c : asignacionActual.keySet()) {
            mejorAsignacion.put(c, new ArrayList<>(asignacionActual.get(c)));
        }
        mejoresNoAsignados = new ArrayList<>(noAsignadosActual);
    }

    private int calcularPesoTotal() {
        int total = 0;
        for (Paquete p : paquetes) {
            total += p.getPeso();
        }
        return total;
    }
}
