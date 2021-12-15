package mx.uv.bd;

public class Usuario {
    
    private String id;
    private String nombre;
    private String email;
    private String password;

    public Usuario(String id, String nombre, String email, String password) {
        this.setId(id);
        this.setNombre(nombre);
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
