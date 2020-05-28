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
public class Note {
    private User etudiant = new User();
    private User enseignant = new User();
    private Matiere matiere = new Matiere();
    private String dateNote;
    private double noteCC;
    private double noteDS;
    private double noteExam;
    private double moyenne;

    public Note() {
    }


    public Note(String dateNote, double noteCC, double noteDS, double noteExam, double moyenne) {
        this.dateNote = dateNote;
        this.noteCC = noteCC;
        this.noteDS = noteDS;
        this.noteExam = noteExam;
        this.moyenne = moyenne;
    }

    public void setEtudiant(User etudiant) {
        this.etudiant = etudiant;
    }

    public void setEnseignant(User enseignant) {
        this.enseignant = enseignant;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public void setDateNote(String dateNote) {
        this.dateNote = dateNote;
    }

    public void setNoteCC(double noteCC) {
        this.noteCC = noteCC;
    }

    public void setNoteDS(double noteDS) {
        this.noteDS = noteDS;
    }

    public void setNoteExam(double noteExam) {
        this.noteExam = noteExam;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }


    public User getEtudiant() {
        return etudiant;
    }

    public User getEnseignant() {
        return enseignant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public String getDateNote() {
        return dateNote;
    }

    public double getNoteCC() {
        return noteCC;
    }

    public double getNoteDS() {
        return noteDS;
    }

    public double getNoteExam() {
        return noteExam;
    }

    public double getMoyenne() {
        return moyenne;
    }
        @Override
    public String toString() {
        return "Note{" + "etudiant=" + etudiant + ", enseignant=" + enseignant + ", matiere=" + matiere + ", dateNote=" + dateNote + ", noteCC=" + noteCC + ", noteDS=" + noteDS + ", noteExam=" + noteExam + ", moyenne=" + moyenne + '}';
    }

}
