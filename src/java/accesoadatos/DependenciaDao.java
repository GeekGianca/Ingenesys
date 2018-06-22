package accesoadatos;

import java.util.List;
import model.Dependencia;


public interface DependenciaDao {
    public List<Dependencia> lista();
    public boolean editar(int codigo, String nombre);
    public Dependencia seleccionar(int id);
    public boolean guardar(Dependencia dep);
    public boolean eliminar(int id);
}
