package com.example.actividad2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.actividad2.ui.DatePickerFragment;
import com.example.actividad2.utils.Validador;
import com.google.android.material.textfield.TextInputLayout;

public class ConsultaGastos extends AppCompatActivity {

    TextView tv_app_name, tv_consulta_gasto;
    TextInputLayout til_fecha_inicio_gasto,til_fecha_termino_gasto;
    Spinner sp_item;
    Button btn_consultar_gasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_gastos);

        tv_app_name = findViewById(R.id.tv_app_name);
        tv_consulta_gasto = findViewById(R.id.tv_consulta_gasto);
        sp_item = findViewById(R.id.sp_item);
        til_fecha_inicio_gasto = findViewById(R.id.til_fecha_inicio_gasto);
        til_fecha_termino_gasto = findViewById(R.id.til_fecha_termino_gasto);
        btn_consultar_gasto = findViewById(R.id.btn_consultar_gasto);

        ArrayAdapter<CharSequence> items = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        items.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_item.setAdapter(items);

        til_fecha_inicio_gasto.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(til_fecha_inicio_gasto);
            }
        });

        til_fecha_termino_gasto.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(til_fecha_termino_gasto);
            }
        });

        btn_consultar_gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validar()) {
                    Intent intent = new Intent(getBaseContext(), ResultadoConsulta.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void showDatePickerDialog (final TextInputLayout til) {
        DatePickerFragment datePicker = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = String.format("%d-%02d-%02d", year, month+1, day);
                til.getEditText().setText(selectedDate);
            }
        });
        datePicker.show(getSupportFragmentManager(), "datePicker");
    }

    private boolean validar () {
        String fechaInicio = til_fecha_inicio_gasto.getEditText().getText().toString();
        String fechaTermino = til_fecha_termino_gasto.getEditText().getText().toString();

        boolean fechaInicioValida;
        if (Validador.fecha(fechaInicio)) {
            til_fecha_inicio_gasto.setError(null);
            fechaInicioValida = true;
        } else {
            til_fecha_inicio_gasto.setError("La fecha es inválida");
            til_fecha_inicio_gasto.getEditText().setText("");
            fechaInicioValida = false;
        }

        boolean fechaTerminoValida;
        if (Validador.fecha(fechaTermino)) {
            til_fecha_termino_gasto.setError(null);
            fechaTerminoValida = true;
        } else {
            til_fecha_termino_gasto.setError("La fecha es inválida");
            til_fecha_termino_gasto.getEditText().setText("");
            fechaTerminoValida = false;
        }
        return fechaInicioValida && fechaTerminoValida;
    }

}