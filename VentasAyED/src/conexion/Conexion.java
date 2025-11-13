package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 *
 */
public class Conexion {
    /*
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;"
            + "databaseName=bd_sistema_ventas;"
            + "encrypt=false;"
            + "trustServerCertificate=true;";
    private static final String USUARIO = "sa";
    private static final String CONTRASENA = "123123";
    */
    
    //private static final String URL = "jdbc:mysql://localhost:3306/bd_sistema_ventas";
    private static String URL = "jdbc:mysql://localhost:3306/bd_sistema_ventas?"
           + "useUnicode=true&"
           + "useJDBCCompliantTimezoneShift=true&"
           + "useLegacyDatetimeCode=false&"
           + "serverTimezone=UTC&"
           + "useSSL=false&"
           + "allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "admin"; // Cambia por tu contraseña
    
    //conexion local
    public static Connection conectar() {
        Connection cn = null;
        try {
            //Connection cn = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=", "sa", "123123");
            /*
            
            // Cargar el driver JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Establecer la conexión
            cn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión exitosa a SQL Server");
            */
            
            //Class.forName("com.mysql.cj.jdbc.Driver");
            
           
            // Establecer la conexión
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a MySQL");
            
            
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local " + e);
            e.printStackTrace();
            
        }
        return null;
    }
    
     public static void main(String[] args) {
        
        Connection conexion = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Obtener conexión
            conexion = conectar();
            
            if (conexion != null) {
                // Crear statement
                stmt = conexion.createStatement();
                
                // Ejecutar una consulta de prueba
                String query = "SELECT VERSION() as version, DATABASE() as db";
                rs = stmt.executeQuery(query);
                
                // Mostrar resultado
                if (rs.next()) {
                    System.out.println("Conexion exitosa");
                    System.out.println("🔹 Versión MySQL: " + rs.getString("version"));
                     System.out.println("🔹 Base de datos: " + rs.getString("db"));
                    //System.out.println("Versión de SQL Server: " + rs.getString("SQL Server Version"));
                }
                
                // Ejemplo de consulta a una tabla
                // Reemplaza "tu_tabla" con el nombre de una tabla real
                /*
                query = "SELECT * FROM tu_tabla";
                rs = stmt.executeQuery(query);
                
                while (rs.next()) {
                    // Procesar resultados
                    System.out.println("Columna1: " + rs.getString("columna1"));
                }
                */
            }
            
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta");
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
