package accesoadatos;

import java.util.List;
import model.Tecnico;


public interface TecnicoDao {
    public List<Tecnico> lista();
    public boolean editar(Tecnico tec);
    public Tecnico seleccionar(int id);
    public boolean guardar(Tecnico tec);
    public boolean eliminar(int id);
}
