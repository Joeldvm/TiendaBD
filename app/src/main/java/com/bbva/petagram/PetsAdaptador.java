package com.bbva.petagram;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PetsAdaptador extends  RecyclerView.Adapter<PetsAdaptador.ListaViewHolder>{

    ArrayList<ListaPets> lista;
    Activity activity;

    public  PetsAdaptador (ArrayList<ListaPets> lista, Activity activity){

        this.lista = lista;
        this.activity = activity;

    }

    @NonNull
    @Override
    public ListaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listapets, parent, false);

        return new ListaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViewHolder listaViewHolder, int position) {
        ListaPets listaanimal =lista.get(position);
        listaViewHolder.imagPets.setImageResource(listaanimal.getFotoP());
        listaViewHolder.nombrePets.setText(listaanimal.getNombreP());
        listaViewHolder.likePets.setText(String.valueOf(listaanimal.getLikeP()) + " Likes");



        listaViewHolder.btnlike.setOnClickListener((v) ->  {
                Toast.makeText( activity ,"Diste Like a " + listaanimal.getid() + " " + listaanimal.getNombreP(),
                        Toast.LENGTH_SHORT).show();

                ConstructorPets constructorPets = new ConstructorPets(activity);
                constructorPets.darLikeMascota(listaanimal);
                listaViewHolder.likePets.setText(String.valueOf(constructorPets.obtenerLikesMascotas(listaanimal)));


        });


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ListaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagPets;
        private TextView nombrePets;
        private TextView likePets;
        private ImageButton btnlike;

        public ListaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagPets = (ImageView) itemView.findViewById(R.id.foto);
            nombrePets = (TextView) itemView.findViewById(R.id.txtpet);
            likePets = (TextView) itemView.findViewById(R.id.plikes);
            btnlike = (ImageButton) itemView.findViewById(R.id.btnlike);
        }
    }

}
