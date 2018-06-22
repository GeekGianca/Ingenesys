package accesoadatos;

import java.util.List;
import model.Entrada;

public interface EntradaDao {
    public List<Entrada> lista();
    public boolean editar(Entrada e);
    public Entrada seleccionar(int id);
    public boolean guardar(Entrada e);
    public boolean eliminar(int id);
}
