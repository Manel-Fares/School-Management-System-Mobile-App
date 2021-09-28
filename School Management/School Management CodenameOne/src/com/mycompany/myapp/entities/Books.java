/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Neifos
 */
public class Books {
    
    private int idbook;

   
    private  String titrebook;

   
    private int nbrLike;
  
    private String descriptionbook;

  
    private String etatbook;

   
    private String picbook;
   

 
    private String categoriebook;
    
    
    public Books() {
    }

    public Books(int idbook, String titrebook, int nbrLike, String descriptionbook, String etatbook, String picbook, String categoriebook) {
        this.idbook = idbook;
        this.titrebook = titrebook;
        this.nbrLike = nbrLike;
        this.descriptionbook = descriptionbook;
        this.etatbook = etatbook;
        this.picbook = picbook;
        this.categoriebook = categoriebook;
    }

    public Books(String titrebook, int nbrLike, String descriptionbook, String etatbook, String picbook, String categoriebook) {
        this.titrebook = titrebook;
        this.nbrLike = nbrLike;
        this.descriptionbook = descriptionbook;
        this.etatbook = etatbook;
        this.picbook = picbook;
        this.categoriebook = categoriebook;
    }

    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idbook) {
        this.idbook = idbook;
    }

    public String getTitrebook() {
        return titrebook;
    }

    public void setTitrebook(String titrebook) {
        this.titrebook = titrebook;
    }

    public int getNbrLike() {
        return nbrLike;
    }

    public void setNbrLike(int nbrLike) {
        this.nbrLike = nbrLike;
    }

    public String getDescriptionbook() {
        return descriptionbook;
    }

    public void setDescriptionbook(String descriptionbook) {
        this.descriptionbook = descriptionbook;
    }

    public String getEtatbook() {
        return etatbook;
    }

    public void setEtatbook(String etatbook) {
        this.etatbook = etatbook;
    }

    public String getPicbook() {
        return picbook;
    }

    public void setPicbook(String picbook) {
        this.picbook = picbook;
    }

    public String getCategoriebook() {
        return categoriebook;
    }

    public void setCategoriebook(String categoriebook) {
        this.categoriebook = categoriebook;
    }

    @Override
    public String toString() {
        return "Books{" + "idbook=" + idbook + ", titrebook=" + titrebook + ", nbrLike=" + nbrLike + ", descriptionbook=" + descriptionbook + ", etatbook=" + etatbook + ", picbook=" + picbook + ", categoriebook=" + categoriebook + '}';
    }

    
}
