package mx.uv;

import static spark.Spark.*;

import mx.uv.bd.*;

import com.google.gson.*;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;
public class App 
{
   

    private static Gson gson = new Gson();
    private static Map<String, Usuario> usuarios = new HashMap<>();
    public static void main( String[] args )
    {
        System.out.println( "Proyecto Final de Sistemas Web" );

        staticFiles.location("/");


        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));

        get("/", (req, res) -> {
            res.redirect("inicio.html");
            return null;
        });
        


        //********************* METODOS DE LOS USUARIOS **********************

        //Meter usuario a la BD
        post("/usuario", (req, res) -> {
            String payload = req.body();
            String id = UUID.randomUUID().toString();
            Usuario u = gson.fromJson(payload, Usuario.class);
            u.setId(id);
            
            Operaciones dao = new Operaciones();
            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", dao.crearUsuario(u));
            objetoJson.addProperty("id", id);
            return objetoJson;
        });


        //Buscar usuario en la BD
        post("/usuarioB", (req, res) -> {
            String payload = req.body();
            Usuario u = gson.fromJson(payload, Usuario.class);
            String email = u.getEmail();
            String password = u.getPassword();

            Operaciones dao = new Operaciones();
            JsonObject objetoJson = new JsonObject();
            dao.buscarUsuario(email, password);
            return objetoJson;
        });

        
        //********************* METODOS DE LAS PREGUNTAS **********************
        //Buscar las preguntas en la BD
        get("/preguntas", (req, res) -> {
            before((rq, rs) -> rs.type("application/json"));
            Operaciones dao = new Operaciones();
            return gson.toJson(dao.lisPreguntas());
        });

        //Ingresar pregunta en la BD        
        post("/pregunta", (req, res) -> {
            String payload = req.body();
            String id = UUID.randomUUID().toString();
            Pregunta p = gson.fromJson(payload, Pregunta.class);
            p.setID(id);

            Operaciones dao = new Operaciones();
            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", dao.crearPregunta(p));
            objetoJson.addProperty("id", id);
            return objetoJson;
        });





        //********************* METODOS DE LAS RESPUESTAS **********************
        //METER una respuesta a la BD
        post("/respuesta", (req, res) -> {
            String payload = req.body();
            String id = UUID.randomUUID().toString();
            Respuesta r = gson.fromJson(payload, Respuesta.class);
            r.setId(id);
            
            Operaciones dao = new Operaciones();
            JsonObject objetoJson = new JsonObject();
            objetoJson.addProperty("status", dao.crearRespuesta(r));
            objetoJson.addProperty("id", id);
            return objetoJson;
        });





    }

}
