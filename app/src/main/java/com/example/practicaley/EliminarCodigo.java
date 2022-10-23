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

public class EliminarCodigo extends AppCompatActivity {
    private EditText editCodigoEr;
    private Button escaneadorEr, continuadorEr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar_codigo);

        editCodigoEr = findViewById(R.id.editCodigoEr);
        escaneadorEr = findViewById(R.id.escaneadorEr);
        continuadorEr = findViewById(R.id.continuarEr);

        escaneadorEr.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher9.launch(options);
        });

        continuadorEr.setOnClickListener(view -> startActivity(new Intent(EliminarCodigo.this,EliminarER.class)));


    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher9 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(EliminarCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EliminarCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoEr.setText(result.getContents());
                }
            });
}