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

public class ModificarCodigo extends AppCompatActivity {
    private EditText editCodigoMd;
    private Button escaneadorMd, continuadorMd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_codigo);

        editCodigoMd = findViewById(R.id.editCodigoMd);
        escaneadorMd = findViewById(R.id.escaneadorMd);
        continuadorMd = findViewById(R.id.continuarMd);

        escaneadorMd.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher8.launch(options);
        });

        continuadorMd.setOnClickListener(view -> startActivity(new Intent(ModificarCodigo.this,ModificarMR.class)));


    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher8 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(ModificarCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ModificarCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoMd.setText(result.getContents());
                }
            });
}