package com.bbva.petagram;

import android.content.Context;
import android.widget.ImageButton;

import java.util.ArrayList;

public class RecyclerViewFragment implements  IRecycleViewFragmentPresenter{

    private  IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorPets constructorPets;
    private ArrayList<ListaPets> listaPets;

    public  RecyclerViewFragment(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){

        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerPetsBD();

    }


    @Override
    public void obtenerPetsBD() {
        constructorPets = new ConstructorPets(context);
        listaPets = constructorPets.obtenerDatos();
        mostrarPetsRV();

    }

    @Override
    public void mostrarPetsRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.creaAdaptador(listaPets));
        iRecyclerViewFragmentView.generarLinearLayoutV();

    }
}
