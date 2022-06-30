package com.roshka.bootcamp.bd;
import jakarta.servlet.ServletRequest;

import java.io.Serializable;

public class Consulta4 implements Serializable {

    private String nombre;
    private double cantidad;

    public Consulta4() {
        this.nombre = "";
        this.cantidad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }


}
