package com.example.luis.basedatos.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.basedatos.R;
import com.example.luis.basedatos.models.Persona;
import com.example.luis.basedatos.utility.DeletePerson;
import com.example.luis.basedatos.views.MainActivity;

import java.util.List;

/**
 * Created by luis on 13/10/17.
 */

public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.PersonasViewHolder> {
    public List<Persona> listaPersonas;
    public Context context;

    public PersonasAdapter(List<Persona> listaPersonas, Context context) {
        this.listaPersonas = listaPersonas;
        this.context = context;
    }

    @Override
    public PersonasAdapter.PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_personas, parent, false);
        PersonasViewHolder personasViewHolder = new PersonasViewHolder(cardview);
        return personasViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonasAdapter.PersonasViewHolder holder, final int position) {
        holder.nombres.setText(listaPersonas.get(position).getName());
        holder.apellidos.setText(listaPersonas.get(position).getLastname());
        holder.edad.setText(String.valueOf(listaPersonas.get(position).getAge()));
        holder.telefono.setText(listaPersonas.get(position).getPhone());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                /*Intent i = new Intent(context, DeleteActivity.class);
                i.putExtra("id", listaPersonas.get(position).getId());
                i.putExtra("name", listaPersonas.get(position).getName());
                i.putExtra("lastname", listaPersonas.get(position).getLastname());
                i.putExtra("age", listaPersonas.get(position).getAge());
                i.putExtra("phone", listaPersonas.get(position).getPhone());*/
                //view.getContext().startActivity(i);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                DeletePerson aux = new DeletePerson();
                                aux.delete(listaPersonas.get(position).getId(), view);
                                //Toast.makeText(MainActivity.this, "YES", Toast.LENGTH_SHORT).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                //Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Esta seguro de eliminar?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public class PersonasViewHolder extends RecyclerView.ViewHolder {
        TextView nombres;
        TextView apellidos;
        TextView edad;
        TextView telefono;
        CardView personasCard;
        Button btnDelete;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            nombres = itemView.findViewById(R.id.cvNombres);
            apellidos = itemView.findViewById(R.id.cvApellidos);
            edad = itemView.findViewById(R.id.cvEdad);
            telefono = itemView.findViewById(R.id.cvTelefono);
            personasCard = itemView.findViewById(R.id.personasCard);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
