package com.example.app_campus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import java.util.List;

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.MateriaViewHolder> {

    private List<Materia> listaMaterias;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Materia materia, int position);
    }

    public MateriaAdapter(Context context, List<Materia> listaMaterias, OnItemClickListener listener) {
        this.context = context;
        this.listaMaterias = listaMaterias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_materia, parent, false);
        return new MateriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaViewHolder holder, int position) {
        Materia materia = listaMaterias.get(position);

        holder.txtNombre.setText(materia.nombre);
        holder.txtProfesor.setText(materia.docente);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(materia, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaMaterias.size();
    }

    // ViewHolder
    public static class MateriaViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        TextView txtProfesor;

        public MateriaViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtProfesor = itemView.findViewById(R.id.txtProfesor);
        }

    }
}
