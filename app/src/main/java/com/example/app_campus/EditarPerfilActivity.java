package com.example.app_campus;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class EditarPerfilActivity extends AppCompatActivity {

    private EditText edtNombre, edtTelefono, edtEmail;
    private Spinner spCarrera, spComision, spTurno;
    private Button btnGuardar;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;

    private final String[] carreras = {"Analista de Sistemas", "Diseño", "Marketing"};
    private final String[] comisiones = {"ACN4BV", "ACN3AV"};
    private final String[] turnos = {"Mañana", "Tarde", "Noche"};

    private final String imagenPerfilDefault = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        edtNombre = findViewById(R.id.edtNombre);
        edtTelefono = findViewById(R.id.edtTelefono);
        edtEmail = findViewById(R.id.edtEmail);

        spCarrera = findViewById(R.id.spCarrera);
        spComision = findViewById(R.id.spComision);
        spTurno = findViewById(R.id.spTurno);

        btnGuardar = findViewById(R.id.btnGuardar);

        cargarSpinners();
        precargarDatos();

        btnGuardar.setOnClickListener(v -> guardarCambios());
    }

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

    private void guardarCambios() {
        String nombre = edtNombre.getText().toString().trim();
        String telefono = edtTelefono.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        String carrera = "";
        String comision = "";
        String turno = "";

        if (spCarrera.getSelectedItem() != null) {
            carrera = spCarrera.getSelectedItem().toString();
        }

        if (spComision.getSelectedItem() != null) {
            comision = spComision.getSelectedItem().toString();
        }

        if (spTurno.getSelectedItem() != null) {
            turno = spTurno.getSelectedItem().toString();
        }

        if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Completá todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            Toast.makeText(this, "Ingresá un email válido", Toast.LENGTH_SHORT).show();
            return;
        }

        PerfilRepository.nombre = nombre;
        PerfilRepository.telefono = telefono;
        PerfilRepository.email = email;
        PerfilRepository.carrera = carrera;
        PerfilRepository.comision = comision;
        PerfilRepository.turno = turno;

        guardarCambiosEnFirestore(nombre, telefono, email, carrera, comision, turno);
    }

    private void guardarCambiosEnFirestore(
            String nombre,
            String telefono,
            String email,
            String carrera,
            String comision,
            String turno
    ) {
        FirebaseUser usuarioActual = firebaseAuth.getCurrentUser();

        if (usuarioActual == null) {
            Toast.makeText(this, "No hay usuario logueado", Toast.LENGTH_SHORT).show();
            return;
        }

        String uid = usuarioActual.getUid();

        btnGuardar.setEnabled(false);
        btnGuardar.setText("Guardando...");

        Map<String, Object> datosPerfil = new HashMap<>();
        datosPerfil.put("nombre", nombre);
        datosPerfil.put("telefono", telefono);
        datosPerfil.put("email", email);
        datosPerfil.put("carrera", carrera);
        datosPerfil.put("comision", comision);
        datosPerfil.put("turno", turno);
        datosPerfil.put("imagenUrl", imagenPerfilDefault);

        db.collection("usuarios")
                .document(uid)
                .set(datosPerfil, SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "Perfil actualizado", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    btnGuardar.setEnabled(true);
                    btnGuardar.setText("Guardar cambios");
                    Toast.makeText(this, "No se pudo actualizar el perfil", Toast.LENGTH_SHORT).show();
                });
    }
}