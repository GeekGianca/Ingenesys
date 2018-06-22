
package model;


public class Ciudad {
    private int codigoCiudad;
    private String nombreCiudad;

    public int getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(int codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Ciudad() {
    }

    public Ciudad(int codigoCiudad, String nombreCiudad) {
        this.codigoCiudad = codigoCiudad;
        this.nombreCiudad = nombreCiudad;
    }

    @Override
    public String toString() {
        return "Ciudad{" + "codigoCiudad=" + codigoCiudad + ", nombreCiudad=" + nombreCiudad + '}';
    }
    
}
