package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ArticuloConsultado extends AppCompatActivity {
    private Button acAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articulo_consultado);


        acAceptar = findViewById(R.id.acAceptar);

        acAceptar.setOnClickListener(view -> startActivity(new Intent(ArticuloConsultado.this, MenuAdmin.class)));
    }
}
