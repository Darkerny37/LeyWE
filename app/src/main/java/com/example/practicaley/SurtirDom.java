package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SurtirDom extends AppCompatActivity {
    private Button sdCancelar, sdAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surtir_dom);

        sdCancelar = findViewById(R.id.sdCancelar);
        sdAceptar = findViewById(R.id.sdAceptar);

        sdCancelar.setOnClickListener(view -> startActivity(new Intent(SurtirDom.this,MontacargaSCodigo.class)));
        sdAceptar.setOnClickListener(view -> startActivity(new Intent(SurtirDom.this, MenuMontacarga.class)));
    }
}
