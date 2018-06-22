package model;

import java.math.BigInteger;


public class TipoPapel {
    private String codigoTipopapel;
    private String nombreTipopapel;
    private int codigoEntrada;

    public String getCodigoTipopapel() {
        return codigoTipopapel;
    }

    public void setCodigoTipopapel(String codigoTipopapel) {
        this.codigoTipopapel = codigoTipopapel;
    }

    public String getNombreTipopapel() {
        return nombreTipopapel;
    }

    public void setNombreTipopapel(String nombreTipopapel) {
        this.nombreTipopapel = nombreTipopapel;
    }

    public int getCodigoEntrada() {
        return codigoEntrada;
    }

    public void setCodigoEntrada(int codigoEntrada) {
        this.codigoEntrada = codigoEntrada;
    }

    public TipoPapel() {
    }

    public TipoPapel(String codigoTipopapel, String nombreTipopapel, int codigoEntrada) {
        this.codigoTipopapel = codigoTipopapel;
        this.nombreTipopapel = nombreTipopapel;
        this.codigoEntrada = codigoEntrada;
    }
    
}
