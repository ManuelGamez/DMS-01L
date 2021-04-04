package com.edu.sv.ejercicio;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.edu.sv.ejercicio.datos.Persona;

public class AddPersonaActivity extends AppCompatActivity {
    EditText edtDUI, edtNombre, edtFecha, edtGenero, edtPeso, edtAltura;
    String key="",nombre="",dui="",fecha="",genero="",peso="",altura="",accion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);
        inicializar();

    }

    private void inicializar() {
        edtNombre = findViewById(R.id.edtNombre);
        edtDUI = findViewById(R.id.edtDUI);
        edtFecha = findViewById(R.id.edtFecha);
        edtGenero = findViewById(R.id.edtGenero);
        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        dui = datos.getString("dui");
        nombre=datos.getString("nombre");
        fecha = datos.getString("fecha");
        genero = datos.getString("genero");
        peso = datos.getString("peso");
        altura = datos.getString("altura");
        accion=datos.getString("accion");
        edtDUI.setText(dui);
        edtNombre.setText(nombre);
        edtFecha.setText(fecha);
        edtGenero.setText(genero);
        edtPeso.setText(peso);
        edtAltura.setText(altura);
    }

    public void guardar(View v){
        String nombre = edtNombre.getText().toString();
        String dui = edtDUI.getText().toString();
        String fecha = edtFecha.getText().toString();
        String genero = edtGenero.getText().toString();
        String peso = edtPeso.getText().toString();
        String altura = edtAltura.getText().toString();
        // Se forma objeto persona
        Persona persona = new Persona(dui,nombre,fecha,genero,peso,altura);

        if (accion.equals("a")) { //Agregar usando push()
            PersonasActivity.refPersonas.push().setValue(persona);
        }
        else // Editar usando setValue
        {
            PersonasActivity.refPersonas.child(key).setValue(persona);
        }
        finish();
    }
    public void cancelar(View v){
        finish();
    }


}