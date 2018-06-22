package accesoadatos;

import java.util.List;
import model.Impresora;

public interface ImpresoraDao {
    public List<Impresora> lista();
    public boolean editar(String nombre, int cod, int codigoDependencia);
    public Impresora seleccionar(int id);
    public boolean guardar(Impresora e);
    public boolean eliminar(int id);
}
