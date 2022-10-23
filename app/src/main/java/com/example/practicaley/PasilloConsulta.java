package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class PasilloConsulta extends AppCompatActivity {
    private Button btnPR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pasillo_consulta);

        btnPR = findViewById(R.id.btnPR);

        btnPR.setOnClickListener(view -> startActivity(new Intent(PasilloConsulta.this,MenuAdmin.class)));

    }

}