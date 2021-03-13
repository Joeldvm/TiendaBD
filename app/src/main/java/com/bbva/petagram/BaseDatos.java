package com.bbva.petagram;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(@Nullable Context context) {

        super(context,ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascotas = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                                            ConstantesBaseDatos.TABLE_MASCOTAS_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE   + " TEXT, " +
                                            ConstantesBaseDatos.TABLE_MASCOTAS_FOTO     + " INTEGER " +
                                            ")";

        String queryCrearTablaLikeMascotas = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKE_MASCOTAS + "(" +
                                                ConstantesBaseDatos.TABLE_LIKE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                ConstantesBaseDatos.TABLE_LIKE_MASCOTAS_ID_MASCOTA + " INTEGER, " +
                                                ConstantesBaseDatos.TABLE_LIKE_MASCOTAS_NUMERO_LIKES + " INTEGER, " +
                                                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKE_MASCOTAS_ID_MASCOTA + ")" +
                                                "REFERENCES " + ConstantesBaseDatos.TABLE_LIKE_MASCOTAS + "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")" +
                                                ")";

        db.execSQL(queryCrearTablaMascotas);
        db.execSQL(queryCrearTablaLikeMascotas);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS);
        onCreate(db);
    }

    public ArrayList<ListaPets> obtenerMascotas(){
        ArrayList<ListaPets> listaPets = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            ListaPets leemascota = new ListaPets();
            leemascota.setId(registros.getInt(0));
            leemascota.setNombreP(registros.getString(1));
            leemascota.setFotoP(registros.getInt(2));

            String queryLikes = "SELECT COUNT (" + ConstantesBaseDatos.TABLE_LIKE_MASCOTAS_NUMERO_LIKES+") as likes" +
                                " FROM " + ConstantesBaseDatos.TABLE_LIKE_MASCOTAS +
                                " WHERE " + ConstantesBaseDatos.TABLE_LIKE_MASCOTAS_ID_MASCOTA + "=" + leemascota.getid();

            Cursor registrosLike = db.rawQuery(queryLikes, null);
            if(registrosLike.moveToNext()){
                leemascota.setLikeP(registrosLike.getInt(0));
            }else {
                leemascota.setLikeP(0);
            }

            listaPets.add(leemascota);

        }
        db.close();

        return  listaPets;
    }

    public void insertarMascota(ContentValues contentValues){

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

    public  void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKE_MASCOTAS, null, contentValues);
        db.close();

    }

    public int obtenerLikesMascotas(ListaPets listaPets){
        int likes = 0;

        String query = "SELECT COUNT( " + ConstantesBaseDatos.TABLE_LIKE_MASCOTAS_NUMERO_LIKES +")" +
                        " FROM " + ConstantesBaseDatos.TABLE_LIKE_MASCOTAS +
                        " WHERE " + ConstantesBaseDatos.TABLE_LIKE_MASCOTAS_ID_MASCOTA + "=" + listaPets.getid();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();

        return likes;
    }


}
