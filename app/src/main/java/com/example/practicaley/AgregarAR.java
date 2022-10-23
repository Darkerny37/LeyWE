package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarAR extends AppCompatActivity {
    private Button aCancelar, aAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_ar);

        aCancelar = findViewById(R.id.aCancelar);
        aAceptar = findViewById(R.id.aAceptar);

        aCancelar.setOnClickListener(view -> startActivity(new Intent(AgregarAR.this,AgregarCodigo.class)));
        aAceptar.setOnClickListener(view -> startActivity(new Intent(AgregarAR.this, MenuAdmin.class)));
    }
}
