package accesoadatos;

import ConexionFactoria.ConfigConexion;
import ConexionFactoria.FactoriaMysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Entrada;
import model.TipoPapel;

public class TipoPapelImplementado implements TipoPapelDao {

    ConfigConexion conf;

    @Override
    public List<TipoPapel> lista() {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM TIPOPAPEL;");
        List<TipoPapel> listar = null;
        try {
            listar = new ArrayList<>();
            ResultSet rs = this.conf.query(sql.toString());
            while (rs.next()) {
                TipoPapel tp = new TipoPapel();
                tp.setCodigoTipopapel(rs.getString("CodTipoPapel"));
                tp.setNombreTipopapel(rs.getString("NombreTipoPapel"));
                tp.setCodigoEntrada(rs.getInt("ENTRADA_codigoEntrada"));
                listar.add(tp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return listar;
    }

    @Override
    public boolean editar(String codigo, String nombre, int codigoEntrada) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean ejecuto = false;
        String sql = String.format("UPDATE TIPOPAPEL SET NombreTipoPapel='%s', ENTRADA_codigoEntrada=%s WHERE CodTipoPapel='%s'", nombre, codigoEntrada, codigo);
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
    public TipoPapel seleccionar(int id) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        TipoPapel tp = null;
        String sql = String.format("SELECT * FROM TIPOPAPEL WHERE CodTipoPapel=%s", id);
        try {
            ResultSet rs = this.conf.query(sql);
            while (rs.next()) {
                tp = new TipoPapel();
                tp.setCodigoTipopapel(rs.getString("CodTipoPapel"));
                tp.setNombreTipopapel(rs.getString("NombreTipoPapel"));
                tp.setCodigoEntrada(rs.getInt("ENTRADA_codigoEntrada"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return tp;
    }

    @Override
    public boolean guardar(TipoPapel tp) {
        System.out.println("Guardando...");
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean guardar = false;
        try{
            String sql = String.format("INSERT INTO TIPOPAPEL(CodTipoPapel, NombreTipoPapel, ENTRADA_codigoEntrada) VALUES()", tp.getCodigoTipopapel(), tp.getNombreTipopapel(), tp.getCodigoEntrada());
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
            String sql = String.format("DELETE FROM TIPOPAPEL WHERE CodTipoPapel='%s'", id);
            eliminar = this.conf.execute(sql);
        }catch(Exception e){
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.conf.close();
        }
        return eliminar;
    }

}
