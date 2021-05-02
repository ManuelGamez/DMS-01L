package com.edu.sv.ejercicioguia12;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase envoltura para el gestor de Bases de datos
 */
class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context,
                          String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase database) {
        createTable(database); // Crear la tabla "students"
        loadDummyData(database); // Cargar 5 datos de prueba
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Actualizaciones
    }

    /**
     * Crear tabla en la base de datos
     *
     * @param database Instancia de la base de datos
     */
    private void createTable(SQLiteDatabase database) {
        String cmd = "CREATE TABLE " + StudentsContract.STUDENTS + " (" +
                StudentsContract.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StudentsContract.Columnas.NOMBRE + " TEXT, " +
                StudentsContract.Columnas.APELLIDO + " TEXT, " +
                StudentsContract.Columnas.CARNET + " TEXT);";
        database.execSQL(cmd);
    }

    /**
     * Carga datos de ejemplo en la tabla
     * @param database Instancia de la base de datos
     */
    private void loadDummyData(SQLiteDatabase database) {

        ContentValues values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo");
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia");
        values.put(StudentsContract.Columnas.CARNET, "GV050221");
        database.insert(StudentsContract.STUDENTS, null, values);

        values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo2");
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia");
        values.put(StudentsContract.Columnas.CARNET, "GV050222");
        database.insert(StudentsContract.STUDENTS, null, values);


        values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo3");
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia");
        values.put(StudentsContract.Columnas.CARNET, "GV050223");
        database.insert(StudentsContract.STUDENTS, null, values);


        values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo4");
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia");
        values.put(StudentsContract.Columnas.CARNET, "GV050224");
        database.insert(StudentsContract.STUDENTS, null, values);


        values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo5");
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia");
        values.put(StudentsContract.Columnas.CARNET, "GV050225");
        database.insert(StudentsContract.STUDENTS, null, values);

    }
}