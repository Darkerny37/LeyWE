package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class EliminarER extends AppCompatActivity {
    private Button btnCanE, btnAcE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar_er);

        btnCanE = findViewById(R.id.btnCanE);
        btnAcE = findViewById(R.id.btnAcE);

        btnCanE.setOnClickListener(view -> startActivity(new Intent(EliminarER.this,EliminarCodigo.class)));
        btnAcE.setOnClickListener(view -> startActivity(new Intent(EliminarER.this,MenuAdmin.class)));

    }

}