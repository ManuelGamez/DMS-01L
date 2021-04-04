package com.edu.sv.ejemplodbrealfirebase;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.edu.sv.ejemplodbrealfirebase.datos.Persona;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PersonasActivity extends AppCompatActivity {
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refPersonas = database.getReference("personas");

    // Ordenamiento
    Query consultaOrdenada = refPersonas.orderByChild("nombre");

    List<Persona> personas;
    ListView listaPersonas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);

        inicializar();

    }

    private void inicializar() {
        FloatingActionButton fab_agregar= findViewById(R.id.fab_agregar);
        listaPersonas = findViewById(R.id.ListaPersonas);

        // Cuando el usuario haga clic en la lista (para editar registro)
        listaPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), AddPersonaActivity.class);

                intent.putExtra("accion","e"); // Editar
                intent.putExtra("key", personas.get(i).getKey());
                intent.putExtra("nombre",personas.get(i).getNombre());
                intent.putExtra("dui",personas.get(i).getDui());
                startActivity(intent);
            }
        });

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por mas de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaPersonas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                    // Preparando cuadro de dialogo para preguntar al usuario
                    // Si esta seguro de eliminar o no el registro
                    AlertDialog.Builder ad = new AlertDialog.Builder(PersonasActivity.this);
                    ad.setMessage("Está seguro de eliminar registro?")
                            .setTitle("Confirmación");

                    ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            PersonasActivity.refPersonas
                                    .child(personas.get(position).getKey()).removeValue();

                            Toast.makeText(PersonasActivity.this,
                                    "Registro borrado!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(PersonasActivity.this,
                                    "Operación de borrado cancelada!",Toast.LENGTH_SHORT).show();
                        }
                    });

                    ad.show();
                    return true;
            }
        });

        fab_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando el usuario quiere agregar un nuevo registro
                Intent i = new Intent(getBaseContext(), AddPersonaActivity.class);
                i.putExtra("accion","a"); // Agregar
                i.putExtra("key","");
                i.putExtra("nombre","");
                i.putExtra("dui","");
                startActivity(i);
            }
        });

        personas = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                personas.removeAll(personas);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Persona persona = dato.getValue(Persona.class);
                    persona.setKey(dato.getKey());
                    personas.add(persona);
                }

                AdaptadorPersona adapter = new AdaptadorPersona(PersonasActivity.this,
                       personas );
                listaPersonas.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}

