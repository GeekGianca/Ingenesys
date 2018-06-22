package accesoadatos;

import java.util.List;
import model.Ciudad;
import model.Cliente;


public interface ClienteDao {
    public List<Cliente> lista();
    public boolean editar(int CODCLIENTE, int CODCIUDAD, int CODSEDE);
    public Cliente seleccionar(int id);
    public boolean guardar(Cliente cliente, Ciudad ciudad);
    public boolean eliminar(int id);
}
