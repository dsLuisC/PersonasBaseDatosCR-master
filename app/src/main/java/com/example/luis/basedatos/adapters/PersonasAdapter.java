package com.example.luis.basedatos.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luis.basedatos.R;
import com.example.luis.basedatos.models.Persona;

import java.util.List;

/**
 * Created by luis on 13/10/17.
 */

public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.PersonasViewHolder> {
    public List<Persona> personas;
    public Context context;

    public PersonasAdapter(List<Persona> personas, Context context) {
        this.personas = personas;
        this.context = context;
    }

    @Override
    public PersonasAdapter.PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_personas, parent, false);
        PersonasViewHolder personasViewHolder = new PersonasViewHolder(cardview);
        return personasViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonasAdapter.PersonasViewHolder holder, int position) {
        holder.nombres.setText(personas.get(position).getName());
        holder.apellidos.setText(personas.get(position).getLastname());
        holder.edad.setText(String.valueOf(personas.get(position).getAge()));
        holder.telefono.setText(personas.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    public class PersonasViewHolder extends RecyclerView.ViewHolder {
        TextView nombres;
        TextView apellidos;
        TextView edad;
        TextView telefono;
        CardView personasCard;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            nombres = itemView.findViewById(R.id.cvNombres);
            apellidos = itemView.findViewById(R.id.cvApellidos);
            edad = itemView.findViewById(R.id.cvEdad);
            telefono = itemView.findViewById(R.id.cvTelefono);
            personasCard = itemView.findViewById(R.id.personasCard);
        }
    }
}
