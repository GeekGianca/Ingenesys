package accesoadatos;

import java.util.List;
import model.Salida;


public interface SalidaDao {
    public List<Salida> lista();
    public boolean editar(Salida salida);
    public Salida seleccionar(String id);
    public boolean guardar(Salida salida);
    public boolean eliminar(String id);
}
