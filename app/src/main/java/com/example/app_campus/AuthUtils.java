package com.example.app_campus;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AuthUtils {

    public static void cerrarSesion(Context context) {
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        Toast.makeText(context, "Sesión cerrada", Toast.LENGTH_SHORT).show();
    }
}