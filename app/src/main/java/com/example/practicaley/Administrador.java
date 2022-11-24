package com.example.practicaley;

public class Administrador extends Usuario{
    public Administrador(){
        super("","",1);
    }
    public Administrador(String NombreAdm, String Contraseña){
        super(NombreAdm, Contraseña, 1);
    }
    public void ControlarInventario(){

    }
    public void GenerarReporte(){

    }
}
