package mx.uv.BD;


import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Operaciones {

    private Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement stm;
    ResultSet rs;

    public String crearUsuario(Usuario u) {
        PreparedStatement stm = null;
        Connection con = null;
        String msj = "";

        con = conexion.getConnection();
        try {
            String sql = "INSERT INTO usuarios (id, nombre, email, password) VALUES (?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, u.getId());
            stm.setString(2, u.getNombre());
            stm.setString(3, u.getEmail());
            stm.setString(4, u.getPassword());

            if (stm.executeUpdate() > 0)
                msj = "El usuario fue agregado";
            else
                msj = "El usuario no se agrego";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return msj;
    }

    /**Buscar un usuario para poder iniciar sesión */

    public void buscarUsuario( String email, String password){
          Statement stm = null;
            ResultSet rs = null;
            Connection con = null;

            con = conexion.getConnection();
            try{
                String sql = "SELECT * FROM usuarios WHERE email='"+email+ "' AND password='"+ password +"'";
                stm = con.createStatement();
                rs = stm.executeQuery(sql);
                if(rs.next()){
                    System.out.println("Bienvenido usuario");
                    if(java.awt.Desktop.isDesktopSupported()){
                        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                        if(desktop.isSupported(java.awt.Desktop.Action.BROWSE)){
                            try{
                                
                                java.net.URI uri = new java.net.URI("http://127.0.0.1:5500/ProyectoFinal/src/main/resources/inicio.html");
                                desktop.browse(uri);
                            } catch (URISyntaxException | IOException ex) {}
                        }
                    }

                }else{
                    System.out.println("No se pudo ingresar");
                }
            }catch (Exception e){
                e.printStackTrace();
            } finally{
                if (stm != null) {
                    try {
                        stm.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }  
            

    }



    /****************************************Listar las preguntas********************************************/
    public List<Pregunta> lisPreguntas(){
        Statement stm = null;
        ResultSet rs = null;
        Connection con = null;
        List<Pregunta> resultado = new ArrayList<>();

        con = conexion.getConnection();
        try {
            String sql = "SELECT * FROM preguntas";
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Pregunta u = new Pregunta(rs.getString("id"), rs.getString("pregunta"), rs.getString("video"));
                resultado.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
        
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { sqlEx.printStackTrace(); } // ignore
                rs = null;
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException sqlEx) { sqlEx.printStackTrace(); } // ignore
                stm = null;
            }
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    /**---------------------------------------Crear pregunta------------------------- */

    public String crearPregunta(Pregunta p) {
        PreparedStatement stm = null;
        Connection con = null;
        String msj = "";

        con = conexion.getConnection();
        try {
            String sql = "INSERT INTO preguntas (id, pregunta, video) VALUES (?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, p.getId());
            stm.setString(2, p.getPregunta());
            stm.setString(3, p.getVideo());

            if (stm.executeUpdate() > 0)
                msj = "La pregunta fue agregada";
            else
                msj = "La pregunta no se agrego";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return msj;
    }

    /************************************Crear respuesta*******************************************/
    public String crearRespuesta(Respuesta r) {
        PreparedStatement stm = null;
        Connection con = null;
        String msj = "";

        con = conexion.getConnection();
        try {
            String sql = "INSERT INTO respuestas (id, respuesta, idpregunta) VALUES (?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, r.getId());
            stm.setString(2, r.getRespuesta());
            stm.setString(4, r.getIDpregunta());

            if (stm.executeUpdate() > 0)
                msj = "La pregunta fue agregada";
            else
                msj = "La pregunta no se agrego";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return msj;
    }
    
}
