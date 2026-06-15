package Clases;

public class Camion {
    private int id_camion;
    private String patente;
    private int capacidad;
    private boolean refrigerado;

    public Camion(int id, String patente, int capacidad, boolean refrigerado) {
        this.id_camion = id;
        this.patente = patente;
        this.capacidad = capacidad;
        this.refrigerado = refrigerado;
    }

    public int getId() {
        return id_camion;
    }

    public String getPatente() {
        return patente;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public String toString(){
        return "Camion: " + patente + " capacidad: " + capacidad + " refrigerado: " + refrigerado;
    }
}
