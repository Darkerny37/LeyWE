package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Compara_Rep extends AppCompatActivity {
    private Button btnReg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compara_rep);

        btnReg3 = findViewById(R.id.btnReg3);

        btnReg3.setOnClickListener(view -> startActivity(new Intent(Compara_Rep.this,MenuAdmin.class)));

    }
}
