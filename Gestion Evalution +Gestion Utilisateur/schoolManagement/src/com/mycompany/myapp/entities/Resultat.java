/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Aymen
 */
public class Resultat {
    private User etudiant = new User();
    private String dateResultat;
    private float resultat;
    
 public Resultat() {
    }

    public Resultat(String dateResultat, float resultat) {
        this.dateResultat = dateResultat;
        this.resultat = resultat;
    }

   
    
    public User getEtudiant() {
        return etudiant;
    }

    public String getDateResultat() {
        return dateResultat;
    }

    public float getResultat() {
        return resultat;
    }

    public void setEtudiant(User etudiant) {
        this.etudiant = etudiant;
    }

    public void setDateResultat(String dateResultat) {
        this.dateResultat = dateResultat;
    }

    public void setResultat(float resultat) {
        this.resultat = resultat;
    }
    
    
}
