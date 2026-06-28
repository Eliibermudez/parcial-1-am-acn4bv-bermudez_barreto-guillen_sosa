package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CrearGrupoActivity extends AppCompatActivity {

    public static final String EXTRA_TITULO_GRUPO = "extra_titulo_grupo";
    public static final String EXTRA_DETALLE_GRUPO = "extra_detalle_grupo";
    public static final String EXTRA_DESCRIPCION_GRUPO = "extra_descripcion_grupo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);

        EditText inputMateriaGrupo = findViewById(R.id.inputMateriaGrupo);
        EditText inputDescripcionGrupo = findViewById(R.id.inputDescripcionGrupo);
        EditText inputIntegrantesGrupo = findViewById(R.id.inputIntegrantesGrupo);
        Button btnPublicarGrupo = findViewById(R.id.btnPublicarGrupo);
        TextView btnVolverCrearGrupo = findViewById(R.id.btnVolverCrearGrupo);
        btnVolverCrearGrupo.setOnClickListener(v -> finish());

        btnPublicarGrupo.setOnClickListener(v -> {
            String materia = inputMateriaGrupo.getText().toString().trim();
            String descripcion = inputDescripcionGrupo.getText().toString().trim();
            String integrantes = inputIntegrantesGrupo.getText().toString().trim();

            if (materia.isEmpty() || descripcion.isEmpty() || integrantes.isEmpty()) {
                Toast.makeText(this, "Completá todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            String detalle = "Faltan " + integrantes + " integrantes";

            Intent resultado = new Intent();
            resultado.putExtra(EXTRA_TITULO_GRUPO, materia);
            resultado.putExtra(EXTRA_DETALLE_GRUPO, detalle);
            resultado.putExtra(EXTRA_DESCRIPCION_GRUPO, descripcion);

            setResult(RESULT_OK, resultado);

            Toast.makeText(this, getString(R.string.grupo_publicado), Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}