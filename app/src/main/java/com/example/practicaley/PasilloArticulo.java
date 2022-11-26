package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PasilloArticulo extends AppCompatActivity {
    private Button psReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pasillo_artic);


        psReg = findViewById(R.id.psReg);

        psReg.setOnClickListener(view -> startActivity(new Intent(PasilloArticulo.this, MenuAdmin.class)));
    }
}
