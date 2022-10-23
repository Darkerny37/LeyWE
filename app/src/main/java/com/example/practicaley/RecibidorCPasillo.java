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

public class RecibidorCPasillo extends AppCompatActivity {
    private EditText editCodigoPas1;
    private Button escaneadorPas1, continuarPas1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recibidor_pasilloc);

        editCodigoPas1 = findViewById(R.id.editCodigoPas1);
        escaneadorPas1 = findViewById(R.id.escaneadorPas1);
        continuarPas1 = findViewById(R.id.continuarPas1);

        escaneadorPas1.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher3.launch(options);
        });

        continuarPas1.setOnClickListener(view -> startActivity(new Intent(RecibidorCPasillo.this,LevantarArticulos.class)));


    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher3 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(RecibidorCPasillo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RecibidorCPasillo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoPas1.setText(result.getContents());
                }
            });
}