package com.example.practicaley;

public class Usuario {
    private String NombreUsuario;
    private String Contraseña;
    private int Tipo;
    public Usuario(){
        this("","",0);
    }
    public Usuario(String nombreUsuario, String contraseña, int tipo){
        NombreUsuario = nombreUsuario;
        Contraseña = contraseña;
        Tipo = tipo;
    }
    public String getNombreUsuario(){
        return NombreUsuario;
    }
    public String getContraseña(){
        return Contraseña;
    }
    public int getTipo(){
        return Tipo;
    }
    public String[] getUsuario(){
        return new String[]{NombreUsuario, Contraseña, Tipo+""};
    }
}
