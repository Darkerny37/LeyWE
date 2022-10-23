package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class MenuControlI extends AppCompatActivity {
    private Button btnAgregar, btnModificar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_controli);
        btnAgregar=findViewById(R.id.btnAgregar);
        btnModificar=findViewById(R.id.btnModificar);
        btnEliminar=findViewById(R.id.btnEliminar);

        btnAgregar.setOnClickListener(view -> startActivity(new Intent(MenuControlI.this,AgregarCodigo.class)));
        btnModificar.setOnClickListener(view -> startActivity(new Intent(MenuControlI.this,ModificarCodigo.class)));
        btnEliminar.setOnClickListener(view -> startActivity(new Intent(MenuControlI.this,EliminarCodigo.class)));

    }

}