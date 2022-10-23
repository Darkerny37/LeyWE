package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ModificarMR extends AppCompatActivity {
    private Button mCancelar, mAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_mr);

        mCancelar = findViewById(R.id.mCancelar);
        mAceptar = findViewById(R.id.mAceptar);

        mCancelar.setOnClickListener(view -> startActivity(new Intent(ModificarMR.this,ModificarCodigo.class)));
        mAceptar.setOnClickListener(view -> startActivity(new Intent(ModificarMR.this, MenuAdmin.class)));
    }
}
