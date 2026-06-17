package Algoritmos;

import Clases.Camion;
import Clases.Paquete;
import Clases.Solucion;

import java.util.*;

public class Greedy {

    /*
     * Se separan los paquetes en dos listas ordenadas por peso descendente: con alimentos y sin alimentos.
     * Primero se asignan los paquetes con alimentos a camiones refrigerados (son los únicos que pueden recibirlos),
     * luego los paquetes sin alimentos a cualquier camión con capacidad disponible.
     * En cada paso se selecciona el paquete más pesado aún no asignado para maximizar el peso asignado total.
     */
    public Solucion resolver(List<Camion> camiones, List<Paquete> paquetes) {
        int candidatosConsiderados = 0;

        List<Paquete> conAlimentos = new ArrayList<>();
        List<Paquete> sinAlimentos = new ArrayList<>();
        for (Paquete p : paquetes) {
            if (p.getContieneAlimentos()) {
                conAlimentos.add(p);
            } else {
                sinAlimentos.add(p);
            }
        }

        Collections.sort(conAlimentos, new Comparator<Paquete>() {
            public int compare(Paquete a, Paquete b) {
                return b.getPeso() - a.getPeso();
            }
        });
        Collections.sort(sinAlimentos, new Comparator<Paquete>() {
            public int compare(Paquete a, Paquete b) {
                return b.getPeso() - a.getPeso();
            }
        });

        Map<Camion, List<Paquete>> asignaciones = new HashMap<>();
        for (Camion c : camiones) {
            asignaciones.put(c, new ArrayList<>());
        }

        int[] capacidadRestante = new int[camiones.size()];
        for (int i = 0; i < camiones.size(); i++) {
            capacidadRestante[i] = camiones.get(i).getCapacidad();
        }

        List<Paquete> noAsignados = new ArrayList<>();

        // pasada 1: paquetes con alimentos en camiones refrigerados
        for (Paquete p : conAlimentos) {
            boolean asignado = false;
            for (int i = 0; i < camiones.size(); i++) {
                candidatosConsiderados++;
                Camion camion = camiones.get(i);
                if (camion.isRefrigerado() && capacidadRestante[i] >= p.getPeso()) {
                    asignaciones.get(camion).add(p);
                    capacidadRestante[i] -= p.getPeso();
                    asignado = true;
                    break;
                }
            }
            if (!asignado) {
                noAsignados.add(p);
            }
        }

        // pasada 2: paquetes sin alimentos en cualquier camión con capacidad
        for (Paquete p : sinAlimentos) {
            boolean asignado = false;
            for (int i = 0; i < camiones.size(); i++) {
                candidatosConsiderados++;
                if (capacidadRestante[i] >= p.getPeso()) {
                    Camion camion = camiones.get(i);
                    asignaciones.get(camion).add(p);
                    capacidadRestante[i] -= p.getPeso();
                    asignado = true;
                    break;
                }
            }
            if (!asignado) {
                noAsignados.add(p);
            }
        }

        return new Solucion(asignaciones, noAsignados, candidatosConsiderados, "Candidatos considerados");
    }
}
