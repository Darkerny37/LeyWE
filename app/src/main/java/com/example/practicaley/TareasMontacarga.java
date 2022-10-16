package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class TareasMontacarga extends AppCompatActivity {
    private Button btnRegLis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montacarga_tareas);

        btnRegLis = findViewById(R.id.btnRegLis);

        btnRegLis.setOnClickListener(view -> startActivity(new Intent(TareasMontacarga.this,ReservaNewUbicacion.class)));

    }

}