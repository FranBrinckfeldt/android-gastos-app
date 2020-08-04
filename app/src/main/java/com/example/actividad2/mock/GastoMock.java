package com.example.actividad2.mock;

import com.example.actividad2.model.Gasto;

import java.util.ArrayList;

public class GastoMock {

    public static ArrayList<Gasto> getAll() {
        ArrayList<Gasto> gastos = new ArrayList<>();
        gastos.add(new Gasto(1, 1, "Vestuario", "2020-03-24", 40000, "Ropa verano"));
        gastos.add(new Gasto(2, 1, "Supermercado", "2020-04-05", 50000, "Verduras"));
        gastos.add(new Gasto(3, 1, "Tiendas", "2020-04-23", 30000, "Juegos"));
        return gastos;
    }
}
