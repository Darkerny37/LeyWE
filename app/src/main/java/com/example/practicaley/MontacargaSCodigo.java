package com.example.practicaley;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MontacargaSCodigo extends AppCompatActivity {
    private EditText editCodigoDos;
    private Button escaneadorDos, continuadorDos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.montacarga_scodigo);

        editCodigoDos = findViewById(R.id.editCodigoUno);
        escaneadorDos = findViewById(R.id.escaneadorDos);
        continuadorDos = findViewById(R.id.continuarDos);

        escaneadorDos.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher2.launch(options);
        });

        continuadorDos.setOnClickListener(view -> startActivity(new Intent(MontacargaSCodigo.this,SurtirDom.class)));


    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher2 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(MontacargaSCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MontacargaSCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoDos.setText(result.getContents());
                }
            });
}