package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class MenuMontacarga extends AppCompatActivity {
    private Button btnReserva, btnSurti, btnConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montacarga_menu);
        btnReserva=findViewById(R.id.btnReserva);
        btnConsulta=findViewById(R.id.btnConsulta);
        btnSurti=findViewById(R.id.btnSurti);

      btnReserva.setOnClickListener(view -> startActivity(new Intent(MenuMontacarga.this,TareasMontacarga.class)));
      btnSurti.setOnClickListener(view -> startActivity(new Intent(MenuMontacarga.this,MontacargaSCodigo.class)));
      //btnConsulta.setOnClickListener(view -> startActivity(new Intent(MenuMontacarga.this,.class)));
    }

}