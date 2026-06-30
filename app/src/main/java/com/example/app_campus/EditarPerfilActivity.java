package com.example.app_campus;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class EditarPerfilActivity extends AppCompatActivity {

    EditText edtNombre, edtTelefono, edtEmail;
    Spinner spCarrera, spComision, spTurno;
    Button btnGuardar;

    String[] carreras = {"Analista de Sistemas", "Diseño", "Marketing"};
    String[] comisiones = {"ACN4BV", "ACN3AV"};
    String[] turnos = {"Mañana", "Tarde", "Noche"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);


        ImageView btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        // Referencias
        edtNombre = findViewById(R.id.edtNombre);
        edtTelefono = findViewById(R.id.edtTelefono);
        edtEmail = findViewById(R.id.edtEmail);

        spCarrera = findViewById(R.id.spCarrera);
        spComision = findViewById(R.id.spComision);
        spTurno = findViewById(R.id.spTurno);

        btnGuardar = findViewById(R.id.btnGuardar);

        // Setup
        cargarSpinners();
        precargarDatos();

        btnGuardar.setOnClickListener(v -> guardarCambios());
    }

    //spinners
    private void cargarSpinners() {

        ArrayAdapter<String> adapterCarrera =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, carreras);
        spCarrera.setAdapter(adapterCarrera);

        ArrayAdapter<String> adapterComision =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, comisiones);
        spComision.setAdapter(adapterComision);

        ArrayAdapter<String> adapterTurno =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, turnos);
        spTurno.setAdapter(adapterTurno);
    }

    //datos precargados
    private void precargarDatos() {

        edtNombre.setText(PerfilRepository.nombre);
        edtTelefono.setText(PerfilRepository.telefono);
        edtEmail.setText(PerfilRepository.email);

        setSpinnerSelection(spCarrera, PerfilRepository.carrera);
        setSpinnerSelection(spComision, PerfilRepository.comision);
        setSpinnerSelection(spTurno, PerfilRepository.turno);
    }


    private void setSpinnerSelection(Spinner spinner, String value) {

        if (value == null) return;

        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        if (adapter == null) return;

        int position = adapter.getPosition(value);

        if (position >= 0) {
            spinner.setSelection(position);
        }
    }

    //guardar
    private void guardarCambios() {

        PerfilRepository.nombre = edtNombre.getText().toString();
        PerfilRepository.telefono = edtTelefono.getText().toString();
        PerfilRepository.email = edtEmail.getText().toString();

        if (spCarrera.getSelectedItem() != null)
            PerfilRepository.carrera = spCarrera.getSelectedItem().toString();

        if (spComision.getSelectedItem() != null)
            PerfilRepository.comision = spComision.getSelectedItem().toString();

        if (spTurno.getSelectedItem() != null)
            PerfilRepository.turno = spTurno.getSelectedItem().toString();

        finish();
    }
}