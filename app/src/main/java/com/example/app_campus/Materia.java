package com.example.app_campus;

public class Materia {

    public String cuatrimestre;
    public String comision;
    public String nombre;
    public String horario;
    public String docente;

    public Materia(String cuatrimestre, String comision,
                   String nombre, String horario, String docente) {

        this.cuatrimestre = cuatrimestre;
        this.comision = comision;
        this.nombre = nombre;
        this.horario = horario;
        this.docente = docente;
    }
}