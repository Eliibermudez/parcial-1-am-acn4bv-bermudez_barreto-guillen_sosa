package com.example.app_campus;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationHelper {

    public static void configurar(
            Context context,
            BottomNavigationView bottomNavigation
    ) {

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();
            if(id == R.id.nav_inicio){
                context.startActivity(
                        new Intent(context, HomeActivity.class)
                );

                return true;
            }


            if(id == R.id.nav_materias){
                context.startActivity(
                        new Intent(context, MateriasActivity.class)
                );
                return true;
            }

            if(id == R.id.nav_grupos){

                context.startActivity(
                        new Intent(context, GruposActivity.class)
                );

                return true;
            }

            if(id == R.id.nav_novedades){

                context.startActivity(
                        new Intent(context, NovedadesActivity.class)
                );

                return true;
            }


            if(id == R.id.nav_perfil){

                context.startActivity(
                        new Intent(context, PerfilActivity.class)
                );
                return true;
            }
            if(id == R.id.nav_mas){

                PopupMenu popup = new PopupMenu(
                        context,
                        bottomNavigation,
                        Gravity.END
                );


                popup.getMenuInflater()
                        .inflate(
                                R.menu.menu_mas,
                                popup.getMenu()
                        );

                popup.setOnMenuItemClickListener(menuItem -> {
                    if(menuItem.getItemId()==R.id.nav_novedades){

                        context.startActivity(
                                new Intent(
                                        context,
                                        NovedadesActivity.class
                                )
                        );

                        return true;
                    }
                    if(menuItem.getItemId()==R.id.nav_calendario){

                        context.startActivity(
                                new Intent(
                                        context,
                                        CalendarioActivity.class
                                )
                        );

                        return true;
                    }


                    if(menuItem.getItemId()==R.id.nav_contacto){

                        context.startActivity(
                                new Intent(
                                        context,
                                        ContactoActivity.class
                                )
                        );
                        return true;
                    }
                    return false;

                });
                popup.show();

                return true;
            }
            return false;

        });
    }
}