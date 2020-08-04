package com.example.actividad2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.actividad2.model.Gasto;
import com.example.actividad2.ui.DatePickerFragment;
import com.example.actividad2.utils.Validador;
import com.google.android.material.textfield.TextInputLayout;

public class EditarGasto extends AppCompatActivity {

    TextView tv_app_name, tv_edicion_gasto;
    TextInputLayout til_monto, til_descripcion,til_fecha_gasto;
    Spinner sp_item;
    Button btn_editar, btn_eliminar;
    Gasto gasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_gasto);

        til_monto = findViewById(R.id.til_monto);
        til_descripcion = findViewById(R.id.til_descripcion);
        sp_item = findViewById(R.id.sp_item);
        btn_editar = findViewById(R.id.btn_editar);
        btn_eliminar = findViewById(R.id.btn_eliminar);
        til_fecha_gasto = findViewById(R.id.til_fecha_gasto);

        ArrayAdapter<CharSequence> items = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        items.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_item.setAdapter(items);

        Bundle bundle = getIntent().getExtras();
        gasto = null;

        if (bundle != null) {
            gasto = (Gasto) bundle.getSerializable("gasto");
        } else {
            Intent intent = new Intent(getBaseContext(), ConsultaGastos.class);
            startActivity(intent);
            Toast.makeText(getBaseContext(), getString(R.string.mensaje_error_bundle), Toast.LENGTH_LONG).show();
        }

        Resources res = getResources();
        String[] itemsArray =  res.getStringArray(R.array.items);
        int selectedIndex = 0;

        int index = 0;
        for (String item : itemsArray) {
            if (item.equals(gasto.getItem())){
                Log.d("selectedItem", item+" "+ gasto.getItem());
                selectedIndex = index;
            }
            index++;
        }

        til_monto.getEditText().setText(Integer.toString(gasto.getMonto()));
        til_descripcion.getEditText().setText(gasto.getDescripcion());
        til_fecha_gasto.getEditText().setText(gasto.getFecha_gasto());
        sp_item.setSelection(selectedIndex);

        til_fecha_gasto.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(til_fecha_gasto);
            }
        });

        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validar()) {
                    Intent intent = new Intent(getBaseContext(), ConsultaGastos.class);
                    startActivity(intent);
                    String str_monto = til_monto.getEditText().getText().toString();
                    String str_descripcion = til_descripcion.getEditText().getText().toString();
                    String str_item = sp_item.getSelectedItem().toString();
                    String str_fecha = til_fecha_gasto.getEditText().getText().toString();
                    Toast.makeText(getBaseContext(), getString(R.string.mensaje_editar_gasto, str_monto, str_item, str_fecha, str_descripcion), Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ConsultaGastos.class);
                startActivity(intent);
                String str_monto = til_monto.getEditText().getText().toString();
                String str_descripcion = til_descripcion.getEditText().getText().toString();
                String str_item = sp_item.getSelectedItem().toString();
                String str_fecha = til_fecha_gasto.getEditText().getText().toString();
                Toast.makeText(getBaseContext(), getString(R.string.mensaje_eliminar_gasto, str_monto, str_item, str_fecha, str_descripcion), Toast.LENGTH_LONG).show();
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
        String monto = til_monto.getEditText().getText().toString();
        String fecha = til_fecha_gasto.getEditText().getText().toString();
        String descripcion = til_descripcion.getEditText().getText().toString();

        boolean montoValido;
        if (Validador.requerido(monto) && Validador.entero(monto) && Validador.mayorCero(monto)) {
            til_monto.setError(null);
            montoValido = true;
        } else {
            til_monto.setError("El monto ingresado no es válido");
            til_monto.getEditText().setText("");
            montoValido = false;
        }

        boolean fechaValida;
        if (Validador.fecha(fecha)) {
            til_fecha_gasto.setError(null);
            fechaValida = true;
        } else {
            til_fecha_gasto.setError("La fecha es inválida");
            til_fecha_gasto.getEditText().setText("");
            fechaValida = false;
        }

        boolean descripcionValida;
        if (Validador.requerido(descripcion)) {
            til_descripcion.setError(null);
            descripcionValida = true;
        } else {
            til_descripcion.setError("La descripción es inválida");
            til_descripcion.getEditText().setText("");
            descripcionValida = false;
        }

        return montoValido && fechaValida && descripcionValida;
    }

}