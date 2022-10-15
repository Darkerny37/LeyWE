package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NuevoLoad extends AppCompatActivity {
   private Button nlCancelar, nlAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_load);

        nlCancelar = findViewById(R.id.nlCancelar);
        nlAceptar = findViewById(R.id.nlAceptar);

        nlCancelar.setOnClickListener(view -> startActivity(new Intent(NuevoLoad.this,RecibidorCodigo.class)));
        nlAceptar.setOnClickListener(view -> startActivity(new Intent(NuevoLoad.this, AgregarTarea.class)));
    }
}
