package com.bbva.petagram;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

public class fragment_recview extends Fragment implements  IRecyclerViewFragmentView{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //ArrayList<ListaPets> lista = new ArrayList<ListaPets>();
    private RecyclerView lista_pets;
    private IRecycleViewFragmentPresenter presenter;

    public fragment_recview() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static fragment_recview newInstance(String param1, String param2) {
        fragment_recview fragment = new fragment_recview();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recview, container, false);

        //likes = (RecyclerView) v.findViewById(R.i)
        lista_pets = (RecyclerView) v.findViewById(R.id.rvpets);
        presenter = new RecyclerViewFragment(this,getContext());
        /*
        lista.add(new ListaPets("Gato",R.drawable.gato1,5));
        lista.add(new ListaPets("Perro",R.drawable.perro2,3));
        lista.add(new ListaPets("Perrito",R.drawable.perro3,1));
        */
        return  v;
    }


    @Override
    public void generarLinearLayoutV() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        lista_pets.setLayoutManager(llm);

    }

    @Override
    public PetsAdaptador creaAdaptador(ArrayList<ListaPets> listaPets) {
        PetsAdaptador adaptador = new PetsAdaptador(listaPets, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(PetsAdaptador adaptador) {
        lista_pets.setAdapter(adaptador);

    }
}