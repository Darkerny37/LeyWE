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

public class ControlCodigo extends AppCompatActivity {
    private EditText editCodigoCont;
    private Button escaneadorCont, continuarCont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_codigo);

        editCodigoCont = findViewById(R.id.editCodigoCont);
        escaneadorCont = findViewById(R.id.escaneadorCont);
        continuarCont = findViewById(R.id.continuarCont);

        escaneadorCont.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
            options.setPrompt("Escanea el codigo de barras");
            options.setCameraId(0);  // Use a specific camera of the device
            options.setBeepEnabled(true);
            options.setBarcodeImageEnabled(true);
            barcodeLauncher7.launch(options);
        });

        continuarCont.setOnClickListener(view -> startActivity(new Intent(ControlCodigo.this,MenuControlI.class)));


    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher7 = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(ControlCodigo.this, "Se cancelo la operación", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ControlCodigo.this, "Se escaneo: " + result.getContents(), Toast.LENGTH_LONG).show();
                    editCodigoCont.setText(result.getContents());
                }
            });
}