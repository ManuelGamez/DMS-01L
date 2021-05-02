package com.edu.sv.ejercicioguia12;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class StudentsAdapter extends CursorAdapter {

    public StudentsAdapter(Context context) {
        super(context, null, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView carnet = (TextView) view.findViewById(R.id.carnet_text);
        carnet.setText(cursor.getString(
                cursor.getColumnIndex(StudentsContract.Columnas.CARNET)));

        TextView nombre = (TextView) view.findViewById(R.id.nombre_text);
        nombre.setText(cursor.getString(
                cursor.getColumnIndex(StudentsContract.Columnas.NOMBRE)));

        TextView apellido = (TextView) view.findViewById(R.id.apellido_text);
        apellido.setText(cursor.getString(
                cursor.getColumnIndex(StudentsContract.Columnas.APELLIDO)));

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.item, parent, false);
    }
}