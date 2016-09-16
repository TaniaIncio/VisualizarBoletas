package com.tincio.visualizarboletas.data.model;

import java.util.Date;

/**
 * Created by juan on 19/08/2016.
 */
public class Boleta {

    private int id;
    private String date;
    private String nombre;
    private boolean leido;

    public Boleta(int id,String date,String nombre, boolean leido){
        this.id = id;
        this.nombre = nombre;
        this.date = date;
        this.leido = leido;
    }
    public Boleta(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }
}
