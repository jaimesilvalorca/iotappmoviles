package com.example.auto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnGuardar,btnEliminar,btnAtras,btnSiguiente;
    private EditText etPatente,etMarca,etModelo,etCilindrada;

    private ArrayList<Auto> listaAuto = new ArrayList<>();
    private int posicionActual = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referencias();
        eventos();

    }

    public void eventos() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patente, marca, modelo, cilindrada;

                patente = etPatente.getText().toString();
                marca = etMarca.getText().toString();
                modelo = etModelo.getText().toString();
                cilindrada = etCilindrada.getText().toString();

                Auto auto = new Auto(patente, marca, modelo, cilindrada);

                if (posicionActual >= 0 && posicionActual < listaAuto.size()) {
                    listaAuto.set(posicionActual, auto);
                } else {
                    listaAuto.add(auto);
                }

                limpiarEditText();
                
                actualizarEstadoBotonGuardar();
            }
        });



        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posicionActual >= 0 && posicionActual < listaAuto.size()) {
                    mostrarDialogoEliminar();
                } else {
                    Toast.makeText(MainActivity.this, "Ningún auto seleccionado para eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!listaAuto.isEmpty()) {
                    posicionActual = (posicionActual + 1) % listaAuto.size();
                    mostrarDatosEnEditText();
                } else {
                    Toast.makeText(MainActivity.this, "La lista está vacía", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!listaAuto.isEmpty()) {
                    posicionActual = (posicionActual + 1) % listaAuto.size();
                    mostrarDatosEnEditText();
                } else {
                    Toast.makeText(MainActivity.this, "La lista está vacía", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!listaAuto.isEmpty()) {
                    posicionActual = (posicionActual - 1 + listaAuto.size()) % listaAuto.size();
                    mostrarDatosEnEditText();
                } else {
                    Toast.makeText(MainActivity.this, "La lista está vacía", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void mostrarDialogoEliminar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Auto");
        builder.setMessage("¿Estás seguro de que deseas eliminar este auto?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listaAuto.remove(posicionActual);
                limpiarEditText();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.create().show();
    }

    private void actualizarEstadoBotonGuardar() {
        btnGuardar.setEnabled(listaAuto.size() < 4);
    }

    private void limpiarEditText() {
        etPatente.getText().clear();
        etMarca.getText().clear();
        etModelo.getText().clear();
        etCilindrada.getText().clear();
    }

    private void mostrarDatosEnEditText() {
        if (posicionActual >= 0 && posicionActual < listaAuto.size()) {
            Auto autoSeleccionado = listaAuto.get(posicionActual);
            etPatente.setText(autoSeleccionado.getPatente());
            etMarca.setText(autoSeleccionado.getMarca());
            etModelo.setText(autoSeleccionado.getModelo());
            etCilindrada.setText(autoSeleccionado.getCilindrada());
        }
    }

    public void referencias(){
        etPatente = findViewById(R.id.editTextPatente);
        etMarca = findViewById(R.id.editTextMarca);
        etModelo = findViewById(R.id.editTextModelo);
        etCilindrada = findViewById(R.id.editTextCilindrada);
        btnSiguiente =findViewById(R.id.buttonNext);
        btnAtras = findViewById(R.id.buttonPrevious);
        btnGuardar = findViewById(R.id.buttonSave);
        btnEliminar = findViewById(R.id.buttonDelete);


    }
}

