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
public class Etudiant extends User {
    
     
    private String classeEtd;
    private String specialiteEtd;

    public Etudiant(String classeEtd, String specialiteEtd, String id, int cinUser, String username, String nomUser, String email, String password, String roleUser) {
        super(id, cinUser, username, nomUser, email, password, roleUser);
        this.classeEtd = classeEtd;
        this.specialiteEtd = specialiteEtd;
    }

    public void setClasseEtd(String classeEtd) {
        this.classeEtd = classeEtd;
    }

    public void setSpecialiteEtd(String specialiteEtd) {
        this.specialiteEtd = specialiteEtd;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCinUser(int cinUser) {
        this.cinUser = cinUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresseUser(String adresseUser) {
        this.adresseUser = adresseUser;
    }

    public void setNumTelUser(int numTelUser) {
        this.numTelUser = numTelUser;
    }

    public void setSexeUser(String sexeUser) {
        this.sexeUser = sexeUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

    public void setPicUser(String picUser) {
        this.picUser = picUser;
    }

    public void setDateNaissanceUser(String dateNaissanceUser) {
        this.dateNaissanceUser = dateNaissanceUser;
    }

    public static void setCurrentId(String currentId) {
        User.currentId = currentId;
    }

    public static void setCurrentUsername(String currentUsername) {
        User.currentUsername = currentUsername;
    }

    public static void setCurrentCin(int currentCin) {
        User.currentCin = currentCin;
    }

    public static void setCurrentPassword(String currentPassword) {
        User.currentPassword = currentPassword;
    }

    public static void setCurrentNom(String currentNom) {
        User.currentNom = currentNom;
    }

    public static void setCurrentPrenom(String currentPrenom) {
        User.currentPrenom = currentPrenom;
    }

    public static void setCurrentEmail(String currentEmail) {
        User.currentEmail = currentEmail;
    }

    public static void setCurrentPic(String currentPic) {
        User.currentPic = currentPic;
    }

    public String getClasseEtd() {
        return classeEtd;
    }

    public String getSpecialiteEtd() {
        return specialiteEtd;
    }

    public String getId() {
        return id;
    }

    public int getCinUser() {
        return cinUser;
    }

    public String getUsername() {
        return username;
    }

    public String getNomUser() {
        return nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresseUser() {
        return adresseUser;
    }

    public int getNumTelUser() {
        return numTelUser;
    }

    public String getSexeUser() {
        return sexeUser;
    }

    public String getPassword() {
        return password;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public String getPicUser() {
        return picUser;
    }

    public String getDateNaissanceUser() {
        return dateNaissanceUser;
    }

    public static String getCurrentId() {
        return currentId;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static int getCurrentCin() {
        return currentCin;
    }

    public static String getCurrentPassword() {
        return currentPassword;
    }

    public static String getCurrentNom() {
        return currentNom;
    }

    public static String getCurrentPrenom() {
        return currentPrenom;
    }

    public static String getCurrentEmail() {
        return currentEmail;
    }

    public static String getCurrentPic() {
        return currentPic;
    }
    

  
   
    
}
