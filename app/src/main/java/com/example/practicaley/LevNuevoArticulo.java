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

public class LevNuevoArticulo extends AppCompatActivity {
    private EditText editCodigoArPas1;
    private Button escaneadorArPas1, continuadorArPas1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lev_nuevoar);

        editCodigoArPas1 = findViewById(R.id.editCodigoArPas1);
        escaneadorArPas1 = findViewById(R.id.escaneadorArPas1);
        continuadorArPas1 = findViewById(R.id.continuarArPas1);

        escaneadorArPas1.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher4.launch(options);
        });

        continuadorArPas1.setOnClickListener(view -> startActivity(new Intent(LevNuevoArticulo.this,ContarAR.class)));


    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher4 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(LevNuevoArticulo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LevNuevoArticulo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoArPas1.setText(result.getContents());
                }
            });
}