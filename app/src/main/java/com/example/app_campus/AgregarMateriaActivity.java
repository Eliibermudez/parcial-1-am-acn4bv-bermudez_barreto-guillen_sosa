package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarMateriaActivity extends AppCompatActivity {

    private Spinner spCuatrimestre, spComision, spMateria, spHorario, spDocente;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_materia);


        spCuatrimestre = findViewById(R.id.spCuatrimestre);
        spComision = findViewById(R.id.spComision);
        spMateria = findViewById(R.id.spMateria);
        spHorario = findViewById(R.id.spHorario);
        spDocente = findViewById(R.id.spDocente);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {

            String cuatrimestre = spCuatrimestre.getSelectedItem().toString();
            String comision = spComision.getSelectedItem().toString();
            String materia = spMateria.getSelectedItem().toString();
            String horario = spHorario.getSelectedItem().toString();
            String docente = spDocente.getSelectedItem().toString();

            Intent result = new Intent();
            result.putExtra("cuatrimestre", cuatrimestre);
            result.putExtra("comision", comision);
            result.putExtra("materia", materia);
            result.putExtra("horario", horario);
            result.putExtra("docente", docente);

            setResult(RESULT_OK, result);
            finish();
        });
    }
}