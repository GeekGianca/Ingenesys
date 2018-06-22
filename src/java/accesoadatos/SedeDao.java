
package accesoadatos;

import java.util.List;
import model.Sede;


public interface SedeDao {
    public List<Sede> lista();
    public boolean editar(int codsede, String nombre);
    public Sede seleccionar(int id);
    public boolean guardar(Sede sede);
    public boolean eliminar(int id);
}
