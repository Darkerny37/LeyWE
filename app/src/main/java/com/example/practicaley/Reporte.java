package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class Reporte extends AppCompatActivity {
    private Button btnRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporte);

        btnRep = findViewById(R.id.btnRep);

        btnRep.setOnClickListener(view -> startActivity(new Intent(Reporte.this,MenuAdmin.class)));

    }

}