package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
public class MenuAdmin extends AppCompatActivity {
    private Button btnConsultaA, btnConsultaP, btnControlI, btnReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_admin);
        btnConsultaA=findViewById(R.id.btnConsultaA);
        btnConsultaP=findViewById(R.id.btnConsultaP);
        btnControlI=findViewById(R.id.btnControlI);
        btnReporte=findViewById(R.id.btnReporte);

        btnConsultaA.setOnClickListener(view -> startActivity(new Intent(MenuAdmin.this,ConsultaACodigo.class)));
        btnConsultaP.setOnClickListener(view -> startActivity(new Intent(MenuAdmin.this,ConsultaPCodigo.class)));
        btnReporte.setOnClickListener(view -> startActivity(new Intent(MenuAdmin.this,Reporte.class)));
        btnControlI.setOnClickListener(view -> startActivity(new Intent(MenuAdmin.this,ControlCodigo.class)));

    }

}