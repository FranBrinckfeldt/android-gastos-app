package com.example.actividad2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.actividad2.adapter.AdapterConsulta;
import com.example.actividad2.mock.GastoMock;
import com.example.actividad2.model.Gasto;
import com.google.android.material.textfield.TextInputLayout;

public class ResultadoConsulta extends AppCompatActivity {

    TextView tv_app_name, tv_registros;
    ListView lv_gastos;
    AdapterConsulta adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_consulta);

        lv_gastos = findViewById(R.id.lv_gastos);

        adapter = new AdapterConsulta(getBaseContext(), GastoMock.getAll());

        lv_gastos.setAdapter(adapter);

        lv_gastos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Gasto gasto = adapter.getItem(i);
                Intent intent = new Intent(getBaseContext(), EditarGasto.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("gasto", gasto);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}