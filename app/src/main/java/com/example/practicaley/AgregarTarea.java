package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AgregarTarea extends AppCompatActivity{
    private Button botonAgregarLoad, botonAgregarArticulo, botonAgregarTarea, botonMandarTareas;
    private RecyclerView ListaTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_tarea);

        //Obtener lista de tareas
        botonAgregarLoad = findViewById(R.id.Ag_load);
        botonAgregarArticulo = findViewById(R.id.Ag_articulo);
        botonAgregarTarea = findViewById(R.id.Ag_tarea);


        botonAgregarTarea.setOnClickListener(view -> {
            startActivity(new Intent(AgregarTarea.this,RecibidorCodigo.class));
        });
    }


}
