package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class LevantarArticulos extends AppCompatActivity{
    private Button botonLevantarArticulo, botonLevantarInventario;
    private ListView ListaLev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levantar_articulos);
        botonLevantarArticulo = findViewById(R.id.botonLevantarArticulo);
        ListaLev = findViewById(R.id.ListaLev);
        botonLevantarInventario = findViewById(R.id.botonLevantarInventario);

        botonLevantarArticulo.setOnClickListener(view -> startActivity(new Intent(LevantarArticulos.this,LevNuevoArticulo.class)));
        botonLevantarInventario.setOnClickListener(view -> startActivity(new Intent(LevantarArticulos.this,MenuRecibidor.class)));
    }


}
