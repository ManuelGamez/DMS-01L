package com.edu.sv.ejercicioguia12;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private StudentsAdapter adaptador;

    Button Agregar;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adaptador = new StudentsAdapter(this);

        Agregar = findViewById(R.id.fab_agregar);

        lista = findViewById(R.id.lista);
        lista.setAdapter(adaptador);
        VerEstudiantes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        VerEstudiantes();
    }

    public void VerEstudiantes() {
        String URL = "content://com.edu.sv.ejercicioguia12/students";
        Uri students = Uri.parse(URL);
        Cursor c = getContentResolver().query(students, null, null, null, null);
        adaptador.swapCursor(c);
        adaptador.notifyDataSetChanged();
    }

    public void abrir (View view) {
        Intent i = new Intent(MainActivity.this, Agregar.class );
        startActivity(i);
    }
}