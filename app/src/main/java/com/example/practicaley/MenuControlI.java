package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuControlI extends AppCompatActivity {
    private Button btnAgregar, btnModificar, btnEliminar;

    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_controli);

        btnAgregar=findViewById(R.id.btnAgregar);
        btnModificar=findViewById(R.id.btnModificar);
        btnEliminar=findViewById(R.id.btnEliminar);

        id = getIntent().getExtras().getInt("idPasillo");

        //Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();

        btnAgregar.setOnClickListener(view -> startActivity(new Intent(MenuControlI.this,AgregarCodigo.class)));
        btnModificar.setOnClickListener(view -> startActivity(new Intent(MenuControlI.this,ModificarCodigo.class)));
        btnEliminar.setOnClickListener(view -> startActivity(new Intent(MenuControlI.this,EliminarCodigo.class)));

    }

}