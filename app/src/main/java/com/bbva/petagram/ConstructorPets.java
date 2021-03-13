package com.bbva.petagram;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

public class ConstructorPets {

    private Context context;
    private static final int LIKE = 1;

    public ConstructorPets(Context context){

        this.context = context;

    }

    public ArrayList<ListaPets> obtenerDatos(){
        /* Con DAtos en Duro
        ArrayList<ListaPets> lista = new ArrayList<>();
        lista.add(new ListaPets("Gato",R.drawable.gato1,5));
        lista.add(new ListaPets("Perro",R.drawable.perro2,5));
        lista.add(new ListaPets("Perrito",R.drawable.perro3,4));
        return lista;
         */
        //Con Datos en Base de Datos
        BaseDatos db = new BaseDatos(context);
        insertar3datos(db);
        return  db.obtenerMascotas();
    }

    public void insertar3datos(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Gato1");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.gato1);
        //contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING,5);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Perro1");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro1);
        //contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING,2);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE,"Gato2");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.gato2);
        //contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAITING,4);

        db.insertarMascota(contentValues);

    }

    public void darLikeMascota(ListaPets listaPets){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKE_MASCOTAS_ID_MASCOTA, listaPets.getid());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKE_MASCOTAS_NUMERO_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);

    }

    public int obtenerLikesMascotas(ListaPets listaPets){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascotas(listaPets);
    }

}
