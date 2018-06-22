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


public class EntradaImplementado implements EntradaDao{
    
    ConfigConexion conf;
    
    @Override
    public List<Entrada> lista() {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ENTRADA;");
        List<Entrada> listar = null;
        try {
            listar = new ArrayList<>();
            ResultSet rs = this.conf.query(sql.toString());
            while (rs.next()) {
                Entrada entr = new Entrada();
                entr.setCodigoEntrada(rs.getInt("codigoEntrada"));
                entr.setFechaEntrada(rs.getString("FechaEntrada"));
                entr.setTipoEntrada(rs.getString("TipoEntrada"));
                entr.setCantidadEntrada(rs.getInt("CandEntrada"));
                entr.setCodigoTecnico(rs.getInt("TECNICO_codigoTecnico"));
                listar.add(entr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return listar;
    }

    @Override
    public boolean editar(Entrada e) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean ejecuto = false;
        String sql = String.format("UPDATE ENTRADA SET FechaEntrada='%s', TipoEntrada='%s', CandEntrada='%s', TECNICO_codigoTecnico=%s WHERE codigoEntrada=%s", e.getFechaEntrada(), e.getTipoEntrada(), e.getCantidadEntrada(), e.getCodigoTecnico(), e.getCodigoEntrada());
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
    public Entrada seleccionar(int id) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        Entrada e = null;
        String sql = String.format("SELECT * FROM ENTRADA WHERE codigoEntrada=%s", id);
        try {
            ResultSet rs = this.conf.query(sql);
            while (rs.next()) {
                e = new Entrada();
                e.setCodigoEntrada(rs.getInt("codigoEntrada"));
                e.setFechaEntrada(rs.getString("FechaEntrada"));
                e.setTipoEntrada(rs.getString("TipoEntrada"));
                e.setCantidadEntrada(rs.getInt("CandEntrada"));
                e.setCodigoTecnico(rs.getInt("TECNICO_codigoTecnico"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return e;
    }

    @Override
    public boolean guardar(Entrada e) {
        System.out.println("Guardando...");
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean guardar = false;
        try{
            String sql = String.format("CALL INGENESYS.INSERTAR_ENTRADA(%s, '%s', %s, %s)",e.getCodigoEntrada(), e.getTipoEntrada(), e.getCantidadEntrada(), e.getCodigoTecnico());
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
    public boolean eliminar(int id) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean eliminar = false;
        try{
            String sql = String.format("DELETE FROM ENTRADA WHERE codigoEntrada=%s", id);
            eliminar = this.conf.execute(sql);
        }catch(Exception e){
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.conf.close();
        }
        return eliminar;
    }
    
}
