
package model;


public class Cliente {
    private int codigoCliente;
    private String nombreCliente;
    private int codigoCiudad;
    private int codigoSede;

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(int codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public int getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(int codigoSede) {
        this.codigoSede = codigoSede;
    }

    public Cliente() {
    }

    public Cliente(int codigoCliente, String nombreCliente, int codigoCiudad, int codigoSede) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.codigoCiudad = codigoCiudad;
        this.codigoSede = codigoSede;
    }

    @Override
    public String toString() {
        return "Cliente{" + "codigoCliente=" + codigoCliente + ", nombreCliente=" + nombreCliente + ", codigoCiudad=" + codigoCiudad + ", codigoSede=" + codigoSede + '}';
    }
    
}
