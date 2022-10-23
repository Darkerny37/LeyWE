package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ContarAR extends AppCompatActivity {
    private Button caCancelar, caAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contar_ar);

        caCancelar = findViewById(R.id.caCancelar);
        caAceptar = findViewById(R.id.caAceptar);

        caCancelar.setOnClickListener(view -> startActivity(new Intent(ContarAR.this,LevNuevoArticulo.class)));
        caAceptar.setOnClickListener(view -> startActivity(new Intent(ContarAR.this, LevantarArticulos.class)));
    }
}
