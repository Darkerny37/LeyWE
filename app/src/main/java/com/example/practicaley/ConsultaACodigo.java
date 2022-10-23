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

public class ConsultaACodigo extends AppCompatActivity {
    private EditText editCodigoArCon1;
    private Button escaneadorArCon1, continuadorArCon1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta_a_cod);

        editCodigoArCon1 = findViewById(R.id.editCodigoArCon1);
        escaneadorArCon1 = findViewById(R.id.escaneadorArCon1);
        continuadorArCon1 = findViewById(R.id.continuarArCon1);

        escaneadorArCon1.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher5.launch(options);
        });

        continuadorArCon1.setOnClickListener(view -> startActivity(new Intent(ConsultaACodigo.this,ArticuloConsultado.class)));


    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher5 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(ConsultaACodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ConsultaACodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoArCon1.setText(result.getContents());
                }
            });
}