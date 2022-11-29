package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Model.articuloModel;

public class ArticuloConsultado extends AppCompatActivity {
    private Button acAceptar;
    public articuloModel articulo = new articuloModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articulo_consultado);

        //Labels
        TextView idArt = (TextView)findViewById(R.id.txtidArticulo);
        TextView nombreArt = (TextView) findViewById(R.id.txtnombreArticulo);
        TextView cantArt = (TextView) findViewById(R.id.txtCantidad);
        TextView infoArt = (TextView) findViewById(R.id.txtInfo);

        idArt.setText(String.valueOf(getIntent().getExtras().getInt("idArticulo")));
        nombreArt.setText(getIntent().getExtras().getString("nombreArticulo"));
        cantArt.setText(String.valueOf(getIntent().getExtras().getInt("cantidad")));
        infoArt.setText(getIntent().getExtras().getString("numeroSerie"));


        acAceptar = findViewById(R.id.acAceptar);

        acAceptar.setOnClickListener(view -> startActivity(new Intent(ArticuloConsultado.this, MenuAdmin.class)));
    }
}
