package com.edu.sv.ejercicio.datos;

public class Persona {
    private String dui;
    private String nombre;
    private String fecha;
    private String genero;
    private String peso;
    private String altura;
    String key;

    public Persona() {
    }

    public Persona(String dui, String nombre, String fecha, String genero, String peso, String altura) {
        this.dui = dui;
        this.nombre = nombre;
        this.fecha = fecha;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }
}

