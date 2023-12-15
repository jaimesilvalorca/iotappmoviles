package com.example.aplicacionmaestroclientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MostrarActivity extends AppCompatActivity {
    private Button btnVolver;
    private TextView tvRut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        referencias();
        eventos();

        buscarDatos();

    }

    private void buscarDatos(){
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            Cliente cli;

            cli = (Cliente) bundle.getSerializable("cliente");
            tvRut.setText(cli.getRut());
            Log.d("TAG_", "Credito -> " + cli.getCredito());
        }

    }

    private void eventos() {
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }

    private void referencias() {
        btnVolver = findViewById(R.id.btnVolver);
        tvRut = findViewById(R.id.tvRut);
    }
}