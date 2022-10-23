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

public class AgregarCodigo extends AppCompatActivity {
    private EditText editCodigoAg;
    private Button escaneadorAg, continuadorAg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_codigo);

        editCodigoAg = findViewById(R.id.editCodigoAg);
        escaneadorAg = findViewById(R.id.escaneadorAg);
        continuadorAg = findViewById(R.id.continuarAg);

        escaneadorAg.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher8.launch(options);
        });

        continuadorAg.setOnClickListener(view -> startActivity(new Intent(AgregarCodigo.this,AgregarAR.class)));


    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher8 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(AgregarCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AgregarCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoAg.setText(result.getContents());
                }
            });
}