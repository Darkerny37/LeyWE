package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class ConfirmarUbicacion extends AppCompatActivity {
    private Button btnCan, btnAc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_ub);

        btnCan = findViewById(R.id.btnCan);
        btnAc = findViewById(R.id.btnAc);

        btnCan.setOnClickListener(view -> startActivity(new Intent(ConfirmarUbicacion.this,ReservaNewUbicacion.class)));
        btnAc.setOnClickListener(view -> startActivity(new Intent(ConfirmarUbicacion.this,TareasMontacarga.class)));

    }

}