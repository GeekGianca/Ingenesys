package accesoadatos;

import ConexionFactoria.ConfigConexion;
import ConexionFactoria.FactoriaMysql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ciudad;
import model.Cliente;

public class ClienteImplementado implements ClienteDao {

    ConfigConexion conf;

    @Override
    public List<Cliente> lista() {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        StringBuilder sql = new StringBuilder();
        sql.append("CALL INGENESYS.PROCESO_CLIENTE(0,'',0,'',0,'SELECT');");
        List<Cliente> listar = null;
        try {
            listar = new ArrayList<>();
            ResultSet rs = this.conf.query(sql.toString());
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigoCliente(rs.getInt("CodCliente"));
                cliente.setNombreCliente(rs.getString("NombreCliente"));
                cliente.setCodigoCiudad(rs.getInt("CIUDAD_CodigoCiudad"));
                cliente.setCodigoSede(rs.getInt("SEDE_CodSede"));
                listar.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return listar;
    }

    @Override
    public boolean editar(int CODCLIENTE, int CODCIUDAD, int CODSEDE) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean actualizo = false;
        String sql = String.format("CALL INGENESYS.PROCESO_CLIENTE(%s,'%s',%s,'%s',%s,'%s');", CODCIUDAD,"",CODCLIENTE,"",CODSEDE,"UPDATE");
        try {
            actualizo = this.conf.execute(sql);
//            while (rs.next()) {
//                cliente = new Cliente();
//                cliente.setCodigoCliente(rs.getInt("CodCliente"));
//                cliente.setNombreCliente(rs.getString("NombreCliente"));
//                cliente.setCodigoCiudad(rs.getInt("CIUDAD_CodigoCiudad"));
//                cliente.setCodigoSede(rs.getInt("SEDE_CodSede"));
//            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return actualizo;
    }

    @Override
    public Cliente seleccionar(int id) {
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        Cliente cliente = new Cliente();
        String sql = String.format("CALL INGENESYS.PROCESO_CLIENTE(%s,'%s',%s,'%s',%s,'%s');", 0,"",id,"",0,"SELECTED");
        try {
            ResultSet rs = this.conf.query(sql);
            while (rs.next()) {
                cliente.setCodigoCliente(rs.getInt("CodCliente"));
                cliente.setNombreCliente(rs.getString("NombreCliente"));
                cliente.setCodigoCiudad(rs.getInt("CIUDAD_CodigoCiudad"));
                cliente.setCodigoSede(rs.getInt("SEDE_CodSede"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conf.close();
        }
        return cliente;
    }

    @Override
    public boolean guardar(Cliente cliente, Ciudad ciudad) {
        System.out.println("Guardando...");
        this.conf = FactoriaMysql.getInstanceOpenConexion(FactoriaMysql.MYSQL);
        boolean guardar = false;
        boolean verificaCiudad = false;
        try{
            String sqlCiudad = String.format("SELECT * FROM CIUDAD WHERE CodigoCiudad=%s", ciudad.getCodigoCiudad());
            ResultSet rs = this.conf.query(sqlCiudad);
            System.out.println("Obteniendo...");
            while (rs.next()) {
                if (rs.getInt("CodigoCiudad") == ciudad.getCodigoCiudad() && !rs.getString("NombreCiudad").equals(ciudad.getNombreCiudad())) {
                    verificaCiudad = true;
                }
            }
            if (!verificaCiudad) {
                String sql = String.format("CALL INGENESYS.PROCESO_CLIENTE(%s,'%s',%s,'%s',%s,'%s');", ciudad.getCodigoCiudad(), ciudad.getNombreCiudad(), cliente.getCodigoCliente(), cliente.getNombreCliente(), cliente.getCodigoSede(), "INSERT");
                guardar = this.conf.execute(sql);
            }
        }catch(SQLException e){
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
            String sql = String.format("CALL INGENESYS.PROCESO_CLIENTE(%s,'%s',%s,'%s',%s,'%s');", 0,"",id,"",0,"DELETE");
            System.out.println(sql);
            eliminar = this.conf.execute(sql);
        }catch(Exception e){
            Logger.getLogger(ClienteImplementado.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            this.conf.close();
        }
        return eliminar;
    }

}
