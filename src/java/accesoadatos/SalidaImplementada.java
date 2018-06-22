package accesoadatos;

import ConexionFactoria.ConfigConexion;
import ConexionFactoria.FactoriaMysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Salida;


public class SalidaImplementada implements SalidaDao{
    
    ConfigConexion conf;
    
    @Override
    public List<Salida> lista() {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM SALIDAS;");
        List<Salida> listar = null;
        try {
            listar = new ArrayList<>();
            ResultSet rs = this.conf.query(sql.toString());
            while (rs.next()) {
                Salida sal = new Salida();
                sal.setCodigoSalida(rs.getString("CodigoSalidas"));
                sal.setFechaSalida(rs.getString("fechaSalida"));
                sal.setTipoSalida(rs.getString("tipoSalida"));
                sal.setCantidadSalida(rs.getInt("cantidadSalida"));
                sal.setCodigoTecnico(rs.getInt("TECNICO_codigoTecnico"));
                sal.setCodigoImpresora(rs.getInt("IMPRESORA_CodImpresora"));
                sal.setCodigoPapel(rs.getString("TIPOPAPEL_CodTipoPapel"));
                listar.add(sal);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return listar;
    }

    @Override
    public boolean editar(Salida salida) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        String sql = String.format("UPDATE SALIDAS SET fechaSalida='%s', tipoSalida='%s', cantidadSalida=%s, TECNICO_codigoTecnico=%s, IMPRESORA_CodImpresora=%s, TIPOPAPEL_CodTipoPapel='%s' WHERE CodigoSalidas='%s';", 
                salida.getFechaSalida(), //Fecha
                salida.getTipoSalida(), //Tipo Salida
                salida.getCantidadSalida(), //Cantidad Salida
                salida.getCodigoTecnico(), //Codigo tecnico
                salida.getCodigoImpresora(), //Codigo Impresora
                salida.getCodigoPapel(), //Codigo tipo papel
                salida.getCodigoSalida());//Codigo Salida
        boolean ejecuto = false;
        try {
            ejecuto = this.conf.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return ejecuto;
    }

    @Override
    public Salida seleccionar(String id) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        Salida sal = null;
        String sql = String.format("SELECT * FROM SALIDA WHERE CodigoSalida=%s", id);
        try {
            ResultSet rs = this.conf.query(sql);
            while (rs.next()) {
                sal = new Salida();
                sal.setCodigoSalida(rs.getString("CodigoSalidas"));
                sal.setFechaSalida(rs.getString("fechaSalida"));
                sal.setTipoSalida(rs.getString("tipoSalida"));
                sal.setCantidadSalida(rs.getInt("cantidadSalida"));
                sal.setCodigoTecnico(rs.getInt("TECNICO_codigoTecnico"));
                sal.setCodigoImpresora(rs.getInt("IMPRESORA_CodImpresora"));
                sal.setCodigoPapel(rs.getString("TIPOPAPEL_CodTipoPapel"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return sal;
    }

    @Override
    public boolean guardar(Salida salida) {
        System.out.println("Guardando...");
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean guardar = false;
        try{
            String sql = String.format("CALL INGENESYS.INSERT_SALIDA('%s', %s, %s, %s, %s)", salida.getTipoSalida(), salida.getCantidadSalida(), salida.getCodigoTecnico(), salida.getCodigoImpresora(), salida.getCodigoPapel());
            System.out.println("Obteniendo...");
            guardar = this.conf.execute(sql);
        }catch(Exception ex){
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            this.conf.close();
        }
        return guardar;
    }

    @Override
    public boolean eliminar(String id) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean eliminar = false;
        try{
            String sql = String.format("DELETE FROM SALIDAS WHERE CodigoSalidas='%s'", id);
            eliminar = this.conf.execute(sql);
        }catch(Exception e){
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.conf.close();
        }
        return eliminar;
    }
    
}
