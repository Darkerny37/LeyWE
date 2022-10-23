package com.example.practicaley;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuRecibidor extends AppCompatActivity {
    private Button botonTareas, botonLI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recibidor_menu);

        botonTareas=findViewById(R.id.botonTareas);
        botonLI=findViewById(R.id.botonLI);

        botonTareas.setOnClickListener(view -> startActivity(new Intent(MenuRecibidor.this,AgregarTarea.class)));
        botonLI.setOnClickListener(view -> startActivity(new Intent(MenuRecibidor.this,RecibidorCPasillo.class)));
    }

}