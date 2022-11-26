package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class PasilloLoad extends AppCompatActivity {
    private Button btnPLD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pasillo_load);

        btnPLD = findViewById(R.id.btnPLD);

        btnPLD.setOnClickListener(view -> startActivity(new Intent(PasilloLoad.this,PasilloArticulo.class)));

    }

}