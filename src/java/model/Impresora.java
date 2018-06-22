package model;


public class Impresora {
    private int codigoImpresora;
    private String nombreImpresora;
    private int codigoDependencia;

    public int getCodigoImpresora() {
        return codigoImpresora;
    }

    public void setCodigoImpresora(int codigoImpresora) {
        this.codigoImpresora = codigoImpresora;
    }

    public String getNombreImpresora() {
        return nombreImpresora;
    }

    public void setNombreImpresora(String nombreImpresora) {
        this.nombreImpresora = nombreImpresora;
    }

    public int getCodigoDependencia() {
        return codigoDependencia;
    }

    public void setCodigoDependencia(int codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }

    public Impresora() {
    }

    public Impresora(int codigoImpresora, String nombreImpresora, int codigoDependencia) {
        this.codigoImpresora = codigoImpresora;
        this.nombreImpresora = nombreImpresora;
        this.codigoDependencia = codigoDependencia;
    }
    
}
