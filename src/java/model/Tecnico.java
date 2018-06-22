package model;


public class Tecnico {
    private int codigoTecnico;
    private String nombreTecnico;
    private String cargoTecnico;
    private String telefono;

    public int getCodigoTecnico() {
        return codigoTecnico;
    }

    public void setCodigoTecnico(int codigoTecnico) {
        this.codigoTecnico = codigoTecnico;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    public String getCargoTecnico() {
        return cargoTecnico;
    }

    public void setCargoTecnico(String cargoTecnico) {
        this.cargoTecnico = cargoTecnico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Tecnico() {
    }

    public Tecnico(int codigoTecnico, String nombreTecnico, String cargoTecnico, String telefono) {
        this.codigoTecnico = codigoTecnico;
        this.nombreTecnico = nombreTecnico;
        this.cargoTecnico = cargoTecnico;
        this.telefono = telefono;
    }
    
}
