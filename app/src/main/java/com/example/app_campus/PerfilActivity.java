package com.example.app_campus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    private TextView txtNombre, txtEmail, txtCarrera, txtComision, txtTurno, txtTelefono;
    private Button btnEditar;
    private ImageView imgPerfil;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;

    private LinearLayout btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnCerrarSesion.setOnClickListener(v -> cerrarSesion());

        ImageView btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> {
            finish();
        });

        initViews();
        initListeners();

        cargarDatos();
        setupBottomNav();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarDatos();
    }

    // init views
    private void initViews() {
        imgPerfil = findViewById(R.id.imgPerfil);
        txtNombre = findViewById(R.id.txtNombre);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCarrera = findViewById(R.id.txtCarrera);
        txtComision = findViewById(R.id.txtComision);
        txtTurno = findViewById(R.id.txtTurno);
        btnEditar = findViewById(R.id.btnEditar);
    }

    // listeners
    private void initListeners() {
        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditarPerfilActivity.class);
            startActivity(intent);
        });
    }

    private void cargarDatos() {
        cargarDatosLocales();
        cargarDatosDesdeFirestore();
    }

    private void cargarDatosLocales() {
        txtNombre.setText(PerfilRepository.nombre);
        txtEmail.setText(PerfilRepository.email);
        txtTelefono.setText(PerfilRepository.telefono);
        txtCarrera.setText(PerfilRepository.carrera);
        txtComision.setText(PerfilRepository.comision);
        txtTurno.setText(PerfilRepository.turno);

        Glide.with(this)
                .load("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg")
                .placeholder(R.drawable.bg_chip_gray)
                .error(R.drawable.bg_chip_red)
                .circleCrop()
                .into(imgPerfil);
    }

    private void cargarDatosDesdeFirestore() {
        FirebaseUser usuarioActual = firebaseAuth.getCurrentUser();

        if (usuarioActual == null) {
            Toast.makeText(this, "No hay usuario logueado", Toast.LENGTH_SHORT).show();
            return;
        }

        String uid = usuarioActual.getUid();

        db.collection("usuarios")
                .document(uid)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        mostrarDatosFirestore(documentSnapshot);
                        Toast.makeText(this, "Datos cargados desde Firestore", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "No se encontró el perfil del usuario", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al cargar datos de Firestore", Toast.LENGTH_SHORT).show();
                });
    }

    private void mostrarDatosFirestore(DocumentSnapshot documentSnapshot) {
        String nombre = documentSnapshot.getString("nombre");
        String email = documentSnapshot.getString("email");
        String telefono = documentSnapshot.getString("telefono");
        String carrera = documentSnapshot.getString("carrera");
        String comision = documentSnapshot.getString("comision");
        String turno = documentSnapshot.getString("turno");
        String imagenUrl = documentSnapshot.getString("imagenUrl");

        if (nombre != null) {
            txtNombre.setText(nombre);
            PerfilRepository.nombre = nombre;
        }

        if (email != null) {
            txtEmail.setText(email);
            PerfilRepository.email = email;
        }

        if (telefono != null) {
            txtTelefono.setText(telefono);
            PerfilRepository.telefono = telefono;
        }

        if (carrera != null) {
            txtCarrera.setText(carrera);
            PerfilRepository.carrera = carrera;
        }

        if (comision != null) {
            txtComision.setText(comision);
            PerfilRepository.comision = comision;
        }

        if (turno != null) {
            txtTurno.setText(turno);
            PerfilRepository.turno = turno;
        }

        if (imagenUrl != null && !imagenUrl.isEmpty()) {
            Glide.with(this)
                    .load(imagenUrl)
                    .placeholder(R.drawable.bg_chip_gray)
                    .error(R.drawable.bg_chip_red)
                    .circleCrop()
                    .into(imgPerfil);
        }
    }

    // nav
    private void setupBottomNav() {
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.nav_perfil);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_inicio) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_materias) {
                startActivity(new Intent(this, MateriasActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_grupos) {
                startActivity(new Intent(this, GruposActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_calendario) {
                startActivity(new Intent(this, CalendarioActivity.class));
                finish();
                return true;
            }

            if (id == R.id.nav_chat) {
                startActivity(new Intent(this, NovedadesActivity.class));
                finish();
                return true;
            }

            return id == R.id.nav_perfil;
        });
    }

    private void cerrarSesion() {
        firebaseAuth.signOut();

        Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
    }
}