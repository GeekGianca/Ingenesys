package model;


public class Salida {
    private String codigoSalida;
    private String fechaSalida;
    private String tipoSalida;
    private int cantidadSalida;
    private int codigoTecnico;
    private int codigoImpresora;
    private String codigoPapel;

    public String getCodigoSalida() {
        return codigoSalida;
    }

    public void setCodigoSalida(String codigoSalida) {
        this.codigoSalida = codigoSalida;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getTipoSalida() {
        return tipoSalida;
    }

    public void setTipoSalida(String tipoSalida) {
        this.tipoSalida = tipoSalida;
    }

    public int getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(int cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }

    public int getCodigoTecnico() {
        return codigoTecnico;
    }

    public void setCodigoTecnico(int codigoTecnico) {
        this.codigoTecnico = codigoTecnico;
    }

    public int getCodigoImpresora() {
        return codigoImpresora;
    }

    public void setCodigoImpresora(int codigoImpresora) {
        this.codigoImpresora = codigoImpresora;
    }

    public String getCodigoPapel() {
        return codigoPapel;
    }

    public void setCodigoPapel(String codigoPapel) {
        this.codigoPapel = codigoPapel;
    }

    public Salida() {
    }

    public Salida(String codigoSalida, String fechaSalida, String tipoSalida, int cantidadSalidad, int codigoTecnico, int codigoImpresora, String codigoPapel) {
        this.codigoSalida = codigoSalida;
        this.fechaSalida = fechaSalida;
        this.tipoSalida = tipoSalida;
        this.cantidadSalida = cantidadSalidad;
        this.codigoTecnico = codigoTecnico;
        this.codigoImpresora = codigoImpresora;
        this.codigoPapel = codigoPapel;
    }

    @Override
    public String toString() {
        return "Salida{\n" + "codigoSalida=" + codigoSalida + ",\nfechaSalida=" + fechaSalida + ",\ntipoSalida=" + tipoSalida + ",\ncantidadSalida=" + cantidadSalida + ",\ncodigoTecnico=" + codigoTecnico + ",\ncodigoImpresora=" + codigoImpresora + ",\ncodigoPapel=" + codigoPapel + '}';
    }
    
}
