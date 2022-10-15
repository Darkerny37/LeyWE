package com.example.practicaley;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuRecibidor extends AppCompatActivity {
    private Button botonTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recibidor_menu);

        botonTareas=findViewById(R.id.botonTareas);

        botonTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuRecibidor.this,AgregarTarea.class));
            }
        });
    }

}