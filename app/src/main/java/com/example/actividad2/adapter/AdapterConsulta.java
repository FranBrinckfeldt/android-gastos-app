package com.example.actividad2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.actividad2.ConsultaGastos;
import com.example.actividad2.R;
import com.example.actividad2.model.Gasto;

import java.util.ArrayList;

public class AdapterConsulta extends BaseAdapter {

    private Context context;
    private ArrayList<Gasto> gastos;

    public AdapterConsulta(Context context, ArrayList<Gasto> gastos) {
        this.context = context;
        this.gastos = gastos;
    }

    @Override
    public int getCount() {
        return gastos.size();
    }

    @Override
    public Gasto getItem(int i) {
        return gastos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return gastos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, R.layout.list_item_gasto, null);
        }
        TextView id = view.findViewById(R.id.id);
        TextView item = view.findViewById(R.id.item);
        TextView fecha = view.findViewById(R.id.fecha);
        TextView monto = view.findViewById(R.id.monto);
        TextView btn_editar = view.findViewById(R.id.btn_editar);

        item.setText(gastos.get(i).getItem());
        fecha.setText(gastos.get(i).getFecha_gasto());
        monto.setText(Double.toString(gastos.get(i).getMonto()));

        return view;
    }
}
