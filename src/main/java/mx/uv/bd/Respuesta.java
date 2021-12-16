package mx.uv.bd;

public class Respuesta {

    private String id;
    private String respuesta;
    private String idpregunta;


    public Respuesta(String id, String respuesta, String idpregunta){
        this.setId(id);
        this.setRespuesta(respuesta);
        this.setIDpregunta(idpregunta);
    }

    public String getId(){
        return id;
    }

    public void setId( String id){
        this.id = id;
    }

    public String getRespuesta(){
        return respuesta;
    }

    public void setRespuesta(String respuesta){
        this.respuesta =  respuesta;
    }

    public String getIDpregunta(){
        return idpregunta;
    }
    public void setIDpregunta(String idpregunta){
        this.idpregunta = idpregunta;
    }
    
}
