package com.example.actividad2.model;

import java.io.Serializable;

public class Gasto implements Serializable {

    private int id;
    private int uid;
    private String item;
    private String fecha_gasto;
    private int monto;
    private String descripcion;

    public Gasto(int id, int uid, String item, String fecha_gasto, int monto, String descripcion) {
        this.id = id;
        this.uid = uid;
        this.item = item;
        this.fecha_gasto = fecha_gasto;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getFecha_gasto() {
        return fecha_gasto;
    }

    public void setFecha_gasto(String fecha_gasto) {
        this.fecha_gasto = fecha_gasto;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Gasto{" +
                "id=" + id +
                ", uid=" + uid +
                ", item='" + item + '\'' +
                ", fecha_gasto='" + fecha_gasto + '\'' +
                ", monto=" + monto +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
