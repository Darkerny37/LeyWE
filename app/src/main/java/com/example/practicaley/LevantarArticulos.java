package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LevantarArticulos extends AppCompatActivity{
    private Button botonLevantarArticulo, botonLevantarInventario;
    private ListView ListaLev;
    private String Pasillo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levantar_articulos);
        botonLevantarArticulo = findViewById(R.id.botonLevantarArticulo);
        ListaLev = findViewById(R.id.ListaLev);
        botonLevantarInventario = findViewById(R.id.botonLevantarInventario);
        RecibirDatos();
        botonLevantarArticulo.setOnClickListener(view -> /*startActivity(new Intent(LevantarArticulos.this,LevNuevoArticulo.class))*/ Toast.makeText(this, Pasillo, Toast.LENGTH_LONG).show());
        botonLevantarInventario.setOnClickListener(view -> startActivity(new Intent(LevantarArticulos.this,MenuRecibidor.class)));

    }
    public void RecibirDatos(){
        Bundle extras = getIntent().getExtras();
        Pasillo = extras.getString("Pasillo");
    }

}
