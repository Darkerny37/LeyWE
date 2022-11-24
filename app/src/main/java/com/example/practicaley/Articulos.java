package com.example.practicaley;

public class Articulos {
    private int IdArticulo;
    private String NombreArticulo;
    private double Precio;
    public Articulos(){
        this(0, "", 0.0);
    }
    public Articulos(int idArticulo, String nombreArticulo, double precio){
        IdArticulo = idArticulo;
        NombreArticulo = nombreArticulo;
        Precio = precio;
    }
}
