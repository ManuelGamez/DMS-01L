package com.edu.sv.ejercicioguia12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Agregar extends AppCompatActivity {

    EditText carnet, nombre, apellido;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        carnet = findViewById(R.id.carnet);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        add = (Button) findViewById(R.id.add);

        add = findViewById(R.id.add);
        add.setOnClickListener(v -> {
            agregar();
        });
    }

    protected void agregar() {
        ContentValues agregar = new ContentValues();

        agregar.put(StudentsContract.Columnas.CARNET, carnet.getText().toString());
        agregar.put(StudentsContract.Columnas.NOMBRE, nombre.getText().toString());
        agregar.put(StudentsContract.Columnas.APELLIDO, apellido.getText().toString());

        Uri uri = getContentResolver().insert(StudentsContract.CONTENT_URI, agregar);

        Toast.makeText(getBaseContext(), "Estudiante agregado!", Toast.LENGTH_SHORT).show();
    }
}