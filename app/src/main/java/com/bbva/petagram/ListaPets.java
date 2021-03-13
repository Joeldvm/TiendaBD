package com.bbva.petagram;

public class ListaPets {

    private  int id;
    private String nombreP;
    private int fotoP;
    private int likeP;

    public ListaPets(){

    }

    public ListaPets(int id, String nombreP, int fotoP, int likeP){
        this.id = id;
        this.nombreP = nombreP;
        this.fotoP = fotoP;
        this.likeP = likeP;
    }

    public int getid() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public int getFotoP() {
        return fotoP;
    }

    public void setFotoP(int fotoP) {
        this.fotoP = fotoP;
    }

    public int getLikeP() {return likeP;    }

    public void setLikeP(int likeP) {  this.likeP = likeP;     }
}
