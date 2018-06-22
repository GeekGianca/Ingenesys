package model;


public class Sede {
    private int codigoSede;
    private String nombreSede;

    public int getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(int codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public Sede() {
    }

    public Sede(int codigoSede, String nombreSede) {
        this.codigoSede = codigoSede;
        this.nombreSede = nombreSede;
    }
    
}
