package accesoadatos;

import ConexionFactoria.ConfigConexion;
import ConexionFactoria.FactoriaMysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tecnico;


public class TecnicoImplementado implements TecnicoDao{
    
    ConfigConexion conf;
    
    @Override
    public List<Tecnico> lista() {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM TECNICO;");
        List<Tecnico> listar = null;
        try {
            listar = new ArrayList<>();
            ResultSet rs = this.conf.query(sql.toString());
            while (rs.next()) {
                Tecnico tec = new Tecnico();
                tec.setCodigoTecnico(rs.getInt("codigoTecnico"));
                tec.setNombreTecnico(rs.getString("NombreTecnico"));
                tec.setCargoTecnico(rs.getString("CargoTecnico"));
                tec.setTelefono(rs.getString("Telefono"));
                listar.add(tec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return listar;
    }

    @Override
    public boolean editar(Tecnico tec) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean ejecuto = false;
        String sql = String.format("UPDATE TECNICO SET NombreTecnico='%s', CargoTecnico='%s', Telefono='%s' WHERE codigoTecnico=%s", tec.getNombreTecnico(), tec.getCargoTecnico(), tec.getTelefono(), tec.getCodigoTecnico());
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
    public Tecnico seleccionar(int id) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        Tecnico tec = null;
        String sql = String.format("SELECT * FROM TECNICO WHERE codigoTecnico=%s", id);
        try {
            ResultSet rs = this.conf.query(sql);
            while (rs.next()) {
                tec = new Tecnico();
                tec.setCodigoTecnico(rs.getInt("codigoTecnico"));
                tec.setNombreTecnico(rs.getString("NombreTecnico"));
                tec.setCargoTecnico(rs.getString("CargoTecnico"));
                tec.setTelefono(rs.getString("Telefono"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return tec;
    }

    @Override
    public boolean guardar(Tecnico tec) {
        System.out.println("Guardando...");
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean guardar = false;
        try{
            String sql = String.format("INSERT INTO TECNICO(codigoTecnico, NombreTecnico, CargoTecnico, Telefono) VALUES(%s, '%s', '%s', '%s')", tec.getCodigoTecnico(), tec.getNombreTecnico(), tec.getCargoTecnico(), tec.getTelefono());
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
            String sql = String.format("DELETE FROM TECNICO WHERE codigoTecnico=%s", id);
            eliminar = this.conf.execute(sql);
        }catch(Exception e){
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.conf.close();
        }
        return eliminar;
    }
    
}
