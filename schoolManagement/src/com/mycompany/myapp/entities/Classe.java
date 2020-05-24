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
public class Classe {
    
    private int Id,Nbr_Etudiant;
    private String Name,Niveau,Spec,Description;

    public Classe() {
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNbr_Etudiant(int Nbr_Etudiant) {
        this.Nbr_Etudiant = Nbr_Etudiant;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setNiveau(String Niveau) {
        this.Niveau = Niveau;
    }

    public void setSpec(String Spec) {
        this.Spec = Spec;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    
    public int getId() {
        return Id;
    }

    public int getNbr_Etudiant() {
        return Nbr_Etudiant;
    }

    public String getName() {
        return Name;
    }

    public String getNiveau() {
        return Niveau;
    }

    public String getSpec() {
        return Spec;
    }

    public String getDescription() {
        return Description;
    }
    
    
    
}
