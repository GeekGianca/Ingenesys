
package accesoadatos;

import ConexionFactoria.ConfigConexion;
import ConexionFactoria.FactoriaMysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sede;

public class SedeImplementado implements SedeDao{

    ConfigConexion conf;
    
    @Override
    public List<Sede> lista() {
       this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM SEDE;");
        List<Sede> listar = null;
        try {
            listar = new ArrayList<>();
            ResultSet rs = this.conf.query(sql.toString());
            while (rs.next()) {
                Sede sede = new Sede();
                sede.setCodigoSede(rs.getInt("CodSede"));
                sede.setNombreSede(rs.getString("NombreSede"));
                listar.add(sede);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return listar;
    }

    @Override
    public boolean editar(int codsede, String nombre) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean ejecuto = false;
        String sql = String.format("UPDATE SEDE SET NombreSede='%s' WHERE CodSede=%s", nombre,codsede);
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
    public Sede seleccionar(int id) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        Sede sede = null;
        String sql = String.format("SELECT * FROM SEDE WHERE CodSede=%s", id);
        try {
            ResultSet rs = this.conf.query(sql);
            while (rs.next()) {
                sede = new Sede();
                sede.setCodigoSede(rs.getInt("CodSede"));
                sede.setNombreSede(rs.getString("NombreSede"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return sede;
    }

    @Override
    public boolean guardar(Sede sede) {
        System.out.println("Guardando...");
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean guardar = false;
        try{
            String sql = String.format("INSERT INTO SEDE(CodSede, NombreSede) VALUES(%s, '%s')",sede.getCodigoSede(), sede.getNombreSede());
            System.out.println("Obteniendo...");
            guardar = this.conf.execute(sql);
        }catch(Exception e){
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, e);
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
            String sql = String.format("DELETE FROM SEDE WHERE CodSede=%s", id);
            eliminar = this.conf.execute(sql);
        }catch(Exception e){
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.conf.close();
        }
        return eliminar;
    }
    
}
