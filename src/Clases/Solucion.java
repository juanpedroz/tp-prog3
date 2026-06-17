package Clases;

import java.util.List;
import java.util.Map;

public class Solucion {

    private Map<Camion, List<Paquete>> asignaciones;
    private List<Paquete> noAsignados;
    private int metrica;
    private String metricaNombre;

    public Solucion(Map<Camion, List<Paquete>> asignaciones, List<Paquete> noAsignados, int metrica, String metricaNombre) {
        this.asignaciones = asignaciones;
        this.noAsignados = noAsignados;
        this.metrica = metrica;
        this.metricaNombre = metricaNombre;
    }

    public int getPesoNoAsignado() {
        int total = 0;
        for (Paquete p : noAsignados) {
            total += p.getPeso();
        }
        return total;
    }

    public Map<Camion, List<Paquete>> getAsignaciones() {
        return asignaciones;
    }

    public List<Paquete> getNoAsignados() {
        return noAsignados;
    }

    public int getMetrica() {
        return metrica;
    }

    @Override
    public String toString() {
        String resultado = "Solución obtenida:\n";
        for (Camion c : asignaciones.keySet()) {
            resultado += "  " + c.getPatente() + ": " + asignaciones.get(c) + "\n";
        }
        resultado += "Peso no asignado: " + getPesoNoAsignado() + " kg.\n";
        resultado += metricaNombre + ": " + metrica + "\n";
        return resultado;
    }
}
