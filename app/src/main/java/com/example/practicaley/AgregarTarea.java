package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarTarea extends AppCompatActivity{
    private Button botonAgregarTareas;
    private ListView ListaTarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_tarea);
        botonAgregarTareas = findViewById(R.id.botonAgregarTareas);
        ListaTarea = findViewById(R.id.ListaTarea);

        botonAgregarTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AgregarTarea.this,RecibidorCodigo.class));
            }
        });
    }


}
