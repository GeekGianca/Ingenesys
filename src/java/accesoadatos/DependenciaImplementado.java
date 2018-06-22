package accesoadatos;

import ConexionFactoria.ConfigConexion;
import ConexionFactoria.FactoriaMysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dependencia;


public class DependenciaImplementado implements DependenciaDao{
    
    ConfigConexion conf;
    
    @Override
    public List<Dependencia> lista() {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM DEPENDENCIA;");
        List<Dependencia> listar = null;
        try {
            listar = new ArrayList<>();
            ResultSet rs = this.conf.query(sql.toString());
            while (rs.next()) {
                Dependencia dep = new Dependencia();
                dep.setCodigoDependencia(rs.getInt("codigoDependencia"));
                dep.setNombreDependencia(rs.getString("NombreDependencia"));
                dep.setCodigoSede(rs.getInt("SEDE_CodSede"));
                listar.add(dep);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return listar;
    }

    @Override
    public boolean editar(int codigo, String nombre) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean editar = false;
        String sql = String.format("UPDATE DEPENDENCIA SET NombreDependencia='%s' WHERE codigoDependencia=%s", nombre,codigo);
        try {
            editar = this.conf.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return editar;
    }

    @Override
    public Dependencia seleccionar(int id) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        Dependencia dep = null;
        String sql = String.format("SELECT * FROM SEDE WHERE CodSede=%s", id);
        try {
            ResultSet rs = this.conf.query(sql);
            while (rs.next()) {
                dep = new Dependencia();
                dep.setCodigoDependencia(rs.getInt("codigoDependencia"));
                dep.setNombreDependencia(rs.getString("NombreDependencia"));
                dep.setCodigoSede(rs.getInt("SEDE_CodSede"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return dep;
    }

    @Override
    public boolean guardar(Dependencia dep) {
        System.out.println("Guardando...");
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean guardar = false;
        try{
            String sql = String.format("INSERT INTO DEPENDENCIA(codigoDependencia, NombreDependencia, SEDE_CodSede) VALUES(%s, '%s', %s)",dep.getCodigoDependencia(), dep.getNombreDependencia(), dep.getCodigoSede());
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
            String sql = String.format("DELETE FROM DEPENDENCIA WHERE CodigoDependencia=%s", id);
            eliminar = this.conf.execute(sql);
        }catch(Exception e){
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.conf.close();
        }
        return eliminar;
    }
    
}
