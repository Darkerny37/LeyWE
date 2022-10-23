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

public class ConsultaPCodigo extends AppCompatActivity {
    private EditText editCodigoArCon2;
    private Button escaneadorArCon2, continuadorArCon2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_p_cod);

        editCodigoArCon2 = findViewById(R.id.editCodigoArCon2);
        escaneadorArCon2 = findViewById(R.id.escaneadorArCon2);
        continuadorArCon2 = findViewById(R.id.continuarArCon2);

        escaneadorArCon2.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher6.launch(options);
        });

        continuadorArCon2.setOnClickListener(view -> startActivity(new Intent(ConsultaPCodigo.this,PasilloConsulta.class)));


    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher6 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(ConsultaPCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ConsultaPCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoArCon2.setText(result.getContents());
                }
            });
}
