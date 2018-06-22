package ConexionFactoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class MysqlConexion extends ConfigConexion {

    public MysqlConexion(String params[]) {
        this.params = params;
        this.open();
    }
    
    @Override
    Connection open() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.1.9:3307/"+this.params[0], this.params[1], this.params[2]);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MysqlConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.conn;
    }
    
}
