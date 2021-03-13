package com.bbva.petagram;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class top5Pets extends AppCompatActivity {

    ArrayList<ListaPets> toplist = new ArrayList<ListaPets>();
    private RecyclerView lista_pets;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        this.setActionBar(miActionBar);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        //Se usa por compatibilidad de widget.Toolbar
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle param = getIntent().getExtras();

        lista_pets = (RecyclerView) findViewById(R.id.rvtoppets);

        LinearLayoutManager ltop = new LinearLayoutManager(this);
        ltop.setOrientation(LinearLayoutManager.VERTICAL);

        lista_pets.setLayoutManager(ltop);

        toplist.add(new ListaPets(0,"Kiwi",R.drawable.gato1,10));
        toplist.add(new ListaPets(1,"Perro",R.drawable.perro2,5));
        toplist.add(new ListaPets(2,"Perrito",R.drawable.perro3,4));
        toplist.add(new ListaPets(3,"Roky",R.drawable.gato2,4));
        toplist.add(new ListaPets(4,"Per",R.drawable.perro1,6));


        inicializaAdaptador();


    }

    public void inicializaAdaptador() {
        PetsAdaptador adaptador = new PetsAdaptador(toplist, top5Pets.this);
        lista_pets.setAdapter(adaptador);
    }
}


