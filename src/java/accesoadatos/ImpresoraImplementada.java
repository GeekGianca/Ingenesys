package accesoadatos;

import ConexionFactoria.ConfigConexion;
import ConexionFactoria.FactoriaMysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Impresora;


public class ImpresoraImplementada implements ImpresoraDao{
    
    ConfigConexion conf;
    
    @Override
    public List<Impresora> lista() {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM IMPRESORA;");
        List<Impresora> listar = null;
        try {
            listar = new ArrayList<>();
            ResultSet rs = this.conf.query(sql.toString());
            while (rs.next()) {
                Impresora impr = new Impresora();
                impr.setCodigoImpresora(rs.getInt("CodImpresora"));
                impr.setNombreImpresora(rs.getString("NombreImpresora"));
                impr.setCodigoDependencia(rs.getInt("DEPENDENCIA_codigoDependencia"));
                listar.add(impr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return listar;
    }

    @Override
    public boolean editar(String nombre, int cod, int codigoDependencia) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean ejecuto = false;
        String sql = String.format("UPDATE IMPRESORA SET NombreImpresora='%s', DEPENDENCIA_codigoDependencia=%s WHERE CodImpresora=%s", nombre, codigoDependencia, cod);
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
    public Impresora seleccionar(int id) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        Impresora imp = null;
        String sql = String.format("SELECT * FROM ENTRADA WHERE codigoEntrada=%s", id);
        try {
            ResultSet rs = this.conf.query(sql);
            while (rs.next()) {
                imp = new Impresora();
                imp.setCodigoImpresora(rs.getInt("CodImpresora"));
                imp.setNombreImpresora(rs.getString("NombreImpresora"));
                imp.setCodigoDependencia(rs.getInt("DEPENDENCIA_codigoDependencia"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return imp;
    }

    @Override
    public boolean guardar(Impresora e) {
        System.out.println("Guardando...");
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean guardar = false;
        try{
            String sql = String.format("INSERT INTO IMPRESORA(CodImpresora, NombreImpresora, DEPENDENCIA_codigoDependencia) VALUES(%s, '%s', %s)",e.getCodigoImpresora(), e.getNombreImpresora(), e.getCodigoDependencia());
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
            String sql = String.format("DELETE FROM IMPRESORA WHERE CodImpresora=%s", id);
            eliminar = this.conf.execute(sql);
        }catch(Exception e){
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.conf.close();
        }
        return eliminar;
    }
    
}
