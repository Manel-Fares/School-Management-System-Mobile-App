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
public class Matiere {
    private String idMatiere;
    private String nomMatiere;
    private String coefficient;
    private Enseignant Responsable;

    public Matiere() {
    }

    public Matiere(String idMatiere, String nomMatiere, String coefficient, Enseignant Responsable) {
        this.idMatiere = idMatiere;
        this.nomMatiere = nomMatiere;
        this.coefficient = coefficient;
        this.Responsable = Responsable;
    }

    public Matiere(String idMatiere, String nomMatiere) {
        this.idMatiere = idMatiere;
        this.nomMatiere = nomMatiere;
    }

    public void setIdMatiere(String idMatiere) {
        this.idMatiere = idMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public void setResponsable(Enseignant Responsable) {
        this.Responsable = Responsable;
    }

    public String getIdMatiere() {
        return idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public Enseignant getResponsable() {
        return Responsable;
    }

    @Override
    public String toString() {
        return "Matiere{" + "idMatiere=" + idMatiere + ", nomMatiere=" + nomMatiere + ", coefficient=" + coefficient + '}';
    }

    

    
  
    
    
}
