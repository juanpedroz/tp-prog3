package Clases;

public class Paquete {
    private int id_paquete;
    private String codIdentificador;
    private int peso;
    private boolean contieneAlimentos;
    private int urgencia;

    public Paquete(int id, String codIdentificador, int peso, boolean contieneAlimentos, int urgencia) {
        this.id_paquete = id;
        this.codIdentificador = codIdentificador;
        this.peso = peso;
        this.contieneAlimentos = contieneAlimentos;
        this.urgencia = urgencia;
    }

    public String toString(){
        return "Paquete: " + codIdentificador + " peso: " + peso + " contieneAlimentos: " + contieneAlimentos + " urgencia: " + urgencia;
    }
}

