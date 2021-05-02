package com.edu.sv.ejercicioguia12;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

public class StudentsContract {

    /**
     * Representación de la tabla a consultar
     */
    public static final String STUDENTS = "students";

    /**
     * Código para URIs de multiples registros
     */
    public static final int ALLROWS = 1;
    /**
     * Código para URIS de un solo registro
     */
    public static final int SINGLE_ROW = 2;

    /**
     * Autoridad del Content Provider
     */
    public final static String AUTHORITY = "com.edu.sv.ejercicioguia12";
    /**
     * URI de contenido principal
     */
    public final static Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + STUDENTS);

    public final static String SINGLE_MIME =
            "vnd.android.cursor.item/vnd." + AUTHORITY + STUDENTS;

    public final static String MULTIPLE_MIME =
            "vnd.android.cursor.dir/vnd." + AUTHORITY + STUDENTS;

    /**
     * Comparador de URIs de contenido
     */
    public static final UriMatcher uriMatcher;

    // Asignación de URIs
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, STUDENTS, ALLROWS);
        uriMatcher.addURI(AUTHORITY, STUDENTS + "/#", SINGLE_ROW);
    }

    /**
     * Estructura de la tabla
     */
    public static class Columnas implements BaseColumns {

        private Columnas() {
            // Sin instancias
        }

        public final static String NOMBRE = "nombre";
        public final static String APELLIDO = "apellido";
        public final static String CARNET = "carnet";

    }

}