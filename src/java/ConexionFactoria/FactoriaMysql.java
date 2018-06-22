package ConexionFactoria;


public class FactoriaMysql {
    public static final int MYSQL = 1;
    public static String confiMYSQL[] = {
        "INGENESYS", "root", "geekprogramador23"
    };
    
    public static ConfigConexion getInstanceOpenConexion(int tipoBd){
        switch (tipoBd) {
            case FactoriaMysql.MYSQL:
                return new MysqlConexion(confiMYSQL);
            default:
                System.out.println("Conexion abierta");
        }
        return null;
    }
}
