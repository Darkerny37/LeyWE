package com.example.practicaley;

public class Inventario {
    private Articulos ArticulosI[];
    public Inventario(){
        ArticulosI = ObtenerArticulos();
    }
    public void AgregarArticulo(){
        /*
        * Agregar articulo a la base de datos
        * */
        ArticulosI = ObtenerArticulos();
    }
    private Articulos[] ObtenerArticulos(){
        /*
        * sacar los articulos de la base de datos
        * */
        return null;
    }
}
