package com.example.app_campus;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MateriasActivity extends AppCompatActivity {

    private RecyclerView listaMaterias;
    private MateriaAdapter adapter;
    private List<Materia> lista;

    String[] materias = {
            "Matemática",
            "Aplicaciones Móviles",
            "Java II",
            "Arquitectura de Computadoras",
            "Sistemas Operativos"
    };

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        listaMaterias = findViewById(R.id.recyclerMaterias);
        ImageView btnAgregar = findViewById(R.id.btnAdd);

        lista = new ArrayList<>();

        // Adapter
        adapter = new MateriaAdapter(this, lista, (materia, position) ->
                Toast.makeText(this, materia.getNombre(), Toast.LENGTH_SHORT).show()
        );

        listaMaterias.setLayoutManager(new LinearLayoutManager(this));
        listaMaterias.setAdapter(adapter);

        agregarMateriaInicial();
        agregarMateriaInicial();

        btnAgregar.setOnClickListener(v -> agregarMateria());

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_materias);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_inicio) {
                return true;

            } else if (id == R.id.nav_materias) {
                return true;

            } else if (id == R.id.nav_grupos) {
                return true;

            } else if (id == R.id.nav_calendario) {
                return true;

            } else if (id == R.id.nav_chat) {
                return true;

            } else if (id == R.id.nav_perfil) {
                return true;
            }

            return false;
        });
    }

    private void agregarMateriaInicial() {
        if (index < materias.length) {
            lista.add(new Materia(materias[index], "Pendiente de horario"));
            index++;
        }
    }

    private void agregarMateria() {

        if (index >= materias.length) {
            Toast.makeText(this, "Ya agregaste todas las materias", Toast.LENGTH_SHORT).show();
            return;
        }

        lista.add(new Materia(materias[index], "Pendiente de horario"));
        adapter.notifyItemInserted(lista.size() - 1);

        Toast.makeText(this, "Materia agregada", Toast.LENGTH_SHORT).show();

        index++;
    }
}