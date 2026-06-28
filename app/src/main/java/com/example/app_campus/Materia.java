package com.example.app_campus;
public class Materia {

    private String nombre;
    private String profesor;

    public Materia(String nombre, String profesor) {
        this.nombre = nombre;
        this.profesor = profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProfesor() {
        return profesor;
    }
}