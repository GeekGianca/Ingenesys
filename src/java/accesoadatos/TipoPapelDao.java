package accesoadatos;

import java.util.List;
import model.TipoPapel;


public interface TipoPapelDao {
    public List<TipoPapel> lista();
    public boolean editar(String codigo, String nombre, int codigoEntrada);
    public TipoPapel seleccionar(int id);
    public boolean guardar(TipoPapel tp);
    public boolean eliminar(String id);
}
