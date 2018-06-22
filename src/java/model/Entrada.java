package model;


public class Entrada {
    private int codigoEntrada;
    private String fechaEntrada;
    private String tipoEntrada;
    private int cantidadEntrada;
    private int codigoTecnico;

    public int getCodigoEntrada() {
        return codigoEntrada;
    }

    public void setCodigoEntrada(int codigoEntrada) {
        this.codigoEntrada = codigoEntrada;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public int getCantidadEntrada() {
        return cantidadEntrada;
    }

    public void setCantidadEntrada(int cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }

    public int getCodigoTecnico() {
        return codigoTecnico;
    }

    public void setCodigoTecnico(int codigoTecnico) {
        this.codigoTecnico = codigoTecnico;
    }

    public Entrada() {
    }

    public Entrada(int codigoEntrada, String fechaEntrada, String tipoEntrada, int cantidadEntrada, int codigoTecnico) {
        this.codigoEntrada = codigoEntrada;
        this.fechaEntrada = fechaEntrada;
        this.tipoEntrada = tipoEntrada;
        this.cantidadEntrada = cantidadEntrada;
        this.codigoTecnico = codigoTecnico;
    }
    
}
