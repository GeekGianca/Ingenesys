
package model;


public class Dependencia {
    private int codigoDependencia;
    private String nombreDependencia;
    private int codigoSede;

    public int getCodigoDependencia() {
        return codigoDependencia;
    }

    public void setCodigoDependencia(int codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }

    public String getNombreDependencia() {
        return nombreDependencia;
    }

    public void setNombreDependencia(String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
    }

    public int getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(int codigoSede) {
        this.codigoSede = codigoSede;
    }

    public Dependencia() {
    }

    public Dependencia(int codigoDependencia, String nombreDependencia, int codigoSede) {
        this.codigoDependencia = codigoDependencia;
        this.nombreDependencia = nombreDependencia;
        this.codigoSede = codigoSede;
    }
    
}
