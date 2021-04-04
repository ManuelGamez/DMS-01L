package com.edu.sv.ejemplodbrealfirebase.datos;

public class Persona {
    private String dui;
    private String nombre;
    String key;

    public Persona() {
    }

    public Persona(String dui, String nombre) {
        this.dui = dui;
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }
}
