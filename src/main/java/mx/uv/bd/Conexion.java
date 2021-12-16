package mx.uv.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String url = "jdbc:mysql://db4free.net:3306/basedata_sw";
    private String driverName = "com.mysql.jdbc.Driver";
    private String user = "uvroot";
    private String password = "12345678";
    private Connection con = null;

    public Connection getConnection(){
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, user, password);
            System.out.println("¡Conexion Lista!");
        } catch (SQLException e) {
            System.out.println("¡Conexion Fallida!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("¡Ups, Driver no encontrado!");
            e.printStackTrace();
        }
        return con;
    }
}
