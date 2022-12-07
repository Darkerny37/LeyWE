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

    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levantar_articulos);

        botonLevantarArticulo = findViewById(R.id.botonLevantarArticulo);
        ListaLev = findViewById(R.id.ListaLev);
        botonLevantarInventario = findViewById(R.id.botonLevantarInventario);

        id = getIntent().getExtras().getInt("idPasillo");
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();


        botonLevantarArticulo.setOnClickListener(view -> startActivity(new Intent(LevantarArticulos.this,LevNuevoArticulo.class)));
        botonLevantarInventario.setOnClickListener(view -> startActivity(new Intent(LevantarArticulos.this,MenuRecibidor.class)));
    }


}
