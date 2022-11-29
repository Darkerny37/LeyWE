package Model;


public class userModel {

    public int idUsuario;
    public String usuario;
    public String passUsuario;
    public int rol;
    public int load;

    public userModel(){
        //Constructor vacio
    }

    public userModel(int idUsuario, String usuario, String passUsuario, int rol, int load){
        //Super manda a llamar el constructor del padre si es que hay.
        super();
        this.idUsuario = idUsuario;
        this.usuario =usuario;
        this.passUsuario = passUsuario;
        this.rol = rol;
        this.load = load;
    }
    //setters
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public void setLoad(int load){ this.load = load;}
    //getters
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public int getRol() {
        return rol;
    }

    public  int getLoad(){return load;}
}
