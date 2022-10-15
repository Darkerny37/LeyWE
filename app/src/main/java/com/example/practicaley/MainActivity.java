package com.example.practicaley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

  private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.botonTareas);

        button.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,MenuRecibidor.class)));
    }

}