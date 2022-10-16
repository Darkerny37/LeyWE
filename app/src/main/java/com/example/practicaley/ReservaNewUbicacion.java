package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class ReservaNewUbicacion extends AppCompatActivity {
    private Button btnUbLis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva_newub);

        btnUbLis = findViewById(R.id.btnUbLis);

        btnUbLis.setOnClickListener(view -> startActivity(new Intent(ReservaNewUbicacion.this,ConfirmarUbicacion.class)));

    }

}