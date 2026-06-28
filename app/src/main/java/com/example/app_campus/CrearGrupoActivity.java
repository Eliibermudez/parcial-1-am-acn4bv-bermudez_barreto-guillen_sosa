package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CrearGrupoActivity extends AppCompatActivity {

    public static final String EXTRA_TITULO_GRUPO = "extra_titulo_grupo";
    public static final String EXTRA_DETALLE_GRUPO = "extra_detalle_grupo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);

        EditText inputTituloGrupo = findViewById(R.id.inputTituloGrupo);
        EditText inputMateriaGrupo = findViewById(R.id.inputMateriaGrupo);
        EditText inputDescripcionGrupo = findViewById(R.id.inputDescripcionGrupo);
        EditText inputIntegrantesGrupo = findViewById(R.id.inputIntegrantesGrupo);
        Button btnPublicarGrupo = findViewById(R.id.btnPublicarGrupo);

        btnPublicarGrupo.setOnClickListener(v -> {
            String titulo = inputTituloGrupo.getText().toString().trim();
            String materia = inputMateriaGrupo.getText().toString().trim();
            String descripcion = inputDescripcionGrupo.getText().toString().trim();
            String integrantes = inputIntegrantesGrupo.getText().toString().trim();

            if (titulo.isEmpty() || materia.isEmpty() || descripcion.isEmpty() || integrantes.isEmpty()) {
                Toast.makeText(this, "Completá todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            String detalle = materia + " - Faltan " + integrantes + " integrantes";

            Intent resultado = new Intent();
            resultado.putExtra(EXTRA_TITULO_GRUPO, titulo);
            resultado.putExtra(EXTRA_DETALLE_GRUPO, detalle);

            setResult(RESULT_OK, resultado);

            Toast.makeText(this, getString(R.string.grupo_publicado), Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}