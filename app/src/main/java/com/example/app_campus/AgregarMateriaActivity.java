package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarMateriaActivity extends AppCompatActivity {

    private Spinner spCuatrimestre, spComision, spMateria, spHorario, spDocente;
    private Button btnGuardar;

    private final String[] cuatrimestres = {"Seleccionar...", "4to Cuatrimestre"};

    private final String[] comisiones = { "Seleccionar...",
            "Comisión 1",
            "Comisión 2"
    };

    private final String[] materias = { "Seleccionar...",
            "Programación I",
            "Programación II",
            "Estructura de Datos"
    };

    private final String[] horarios = { "Seleccionar...",
            "08:00 - 10:00",
            "10:00 - 12:00",
            "12:00 - 14:00",
            "14:00 - 16:00",
            "16:00 - 18:00",
            "18:00 - 20:00"
    };

    private final String[] docentes = { "Seleccionar...",
            "Juan Pérez",
            "María Gómez",
            "Carlos López",
            "Ana Fernández",
            "Luis Martínez",
            "Sofía Rodríguez"
    };

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

        setupSpinners();

        btnGuardar.setOnClickListener(v -> {

            String cuatrimestre = spCuatrimestre.getSelectedItem().toString();
            String comision = spComision.getSelectedItem().toString();
            String materia = spMateria.getSelectedItem().toString();
            String horario = spHorario.getSelectedItem().toString();
            String docente = spDocente.getSelectedItem().toString();

            // validacion

            if (spMateria.getSelectedItemPosition() == 0 ||
                    spHorario.getSelectedItemPosition() == 0 ||
                    spDocente.getSelectedItemPosition() == 0) {

                Toast.makeText(this, "Seleccioná todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Materia nueva = new Materia(
                    cuatrimestre,
                    comision,
                    materia,
                    horario,
                    docente
            );

            MateriasRepository.listaMaterias.add(nueva);

            String detalle = materia + " | " + horario;

            Intent resultado = new Intent();
            resultado.putExtra("cuatrimestre", cuatrimestre);
            resultado.putExtra("comision", comision);
            resultado.putExtra("materia", materia);
            resultado.putExtra("horario", horario);
            resultado.putExtra("docente", docente);
            resultado.putExtra("detalle", detalle);

            setResult(RESULT_OK, resultado);

            Toast.makeText(this, "Materia agregada correctamente", Toast.LENGTH_SHORT).show();

            finish();
        });
    }

    private void setupSpinners() {

        ArrayAdapter<String> adapterCuatri =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cuatrimestres);
        adapterCuatri.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCuatrimestre.setAdapter(adapterCuatri);

        ArrayAdapter<String> adapterComision =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, comisiones);
        adapterComision.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spComision.setAdapter(adapterComision);

        ArrayAdapter<String> adapterMateria =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materias);
        adapterMateria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMateria.setAdapter(adapterMateria);

        ArrayAdapter<String> adapterHorario =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horarios);
        adapterHorario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHorario.setAdapter(adapterHorario);

        ArrayAdapter<String> adapterDocente =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, docentes);
        adapterDocente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDocente.setAdapter(adapterDocente);
    }
}