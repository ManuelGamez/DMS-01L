package com.edu.sv.ejercicioguia12;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;

public class StudentsContentProvider extends ContentProvider {

    /**
     * Nombre de la base de datos
     */
    private static final String DATABASE_NAME = "students.db";
    /**
     * Versión actual de la base de datos
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Instancia del administrado de BD
     */
    private DatabaseHelper databaseHelper;

    @Override
    public boolean onCreate() {
        // Inicializando gestor BD
        Log.i("BASE", "CRENADO BASE");
        databaseHelper = new DatabaseHelper(
                getContext(),
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {


        // Obtener base de datos
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        // Comparar Uri
        int match = StudentsContract.uriMatcher.match(uri);

        Cursor c;

        switch (match) {
            case StudentsContract.ALLROWS:
                // Consultando todos los registros
                c = db.query(StudentsContract.STUDENTS, projection,
                        selection, selectionArgs,
                        null, null, sortOrder);
                c.setNotificationUri(
                        getContext().getContentResolver(),
                        StudentsContract.CONTENT_URI);
                break;
            case StudentsContract.SINGLE_ROW:
                // Consultando un solo registro basado en el Id del Uri
                long id = ContentUris.parseId(uri);
                c = db.query(StudentsContract.STUDENTS, projection,
                        StudentsContract.Columnas._ID + " = " + id,
                        selectionArgs, null, null, sortOrder);
                c.setNotificationUri(
                        getContext().getContentResolver(),
                        StudentsContract.CONTENT_URI);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        return c;

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (StudentsContract.uriMatcher.match(uri)) {
            case StudentsContract.ALLROWS:
                return StudentsContract.MULTIPLE_MIME;
            case StudentsContract.SINGLE_ROW:
                return StudentsContract.SINGLE_MIME;
            default:
                throw new IllegalArgumentException("Tipo de students desconocida: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Validar la uri
        if (StudentsContract.uriMatcher.match(uri) != StudentsContract.ALLROWS) {
            throw new IllegalArgumentException("URI desconocida : " + uri);
        }
        ContentValues contentValues;
        if (values != null) {
            contentValues = new ContentValues(values);
        } else {
            contentValues = new ContentValues();
        }

        // Si es necesario, verifica los valores

        // Inserción de nueva fila
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long rowId = db.insert(StudentsContract.STUDENTS,
                null, contentValues);
        if (rowId > 0) {
            Uri uri_actividad =
                    ContentUris.withAppendedId(
                            StudentsContract.CONTENT_URI, rowId);
            getContext().getContentResolver().
                    notifyChange(uri_actividad, null);
            return uri_actividad;
        }
        throw new SQLException("Falla al insertar fila en : " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        int match = StudentsContract.uriMatcher.match(uri);
        int affected;

        switch (match) {
            case StudentsContract.ALLROWS:
                affected = db.delete(StudentsContract.STUDENTS,
                        selection,
                        selectionArgs);
                break;
            case StudentsContract.SINGLE_ROW:
                long id = ContentUris.parseId(uri);
                affected = db.delete(StudentsContract.STUDENTS,
                        StudentsContract.Columnas._ID + "=" + id
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);
                // Notificar cambio asociado a la uri
                getContext().getContentResolver().
                        notifyChange(uri, null);
                break;
            default:
                throw new IllegalArgumentException("Elemento actividad desconocido: " +
                        uri);
        }
        return affected;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int affected;
        switch (StudentsContract.uriMatcher.match(uri)) {
            case StudentsContract.ALLROWS:
                affected = db.update(StudentsContract.STUDENTS, values,
                        selection, selectionArgs);
                break;
            case StudentsContract.SINGLE_ROW:
                String id = uri.getPathSegments().get(1);
                affected = db.update(StudentsContract.STUDENTS, values,
                        StudentsContract.Columnas._ID + "=" + id
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }
}