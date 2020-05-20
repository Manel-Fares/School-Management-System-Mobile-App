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
public class User {
    
    protected String id;
    protected String cinUser;
    protected String username;
    protected String nomUser;
    protected String prenomUser;
    protected String email;
    protected String adresseUser;
    protected String numTelUser;
    protected String sexeUser;
    protected String password;
    protected String roleUser;
    protected String picUser;
    protected String dateNaissanceUser;

private static User currentuser=new User();
    protected static String currentId="4", currentUsername, currentCin, currentPassword, currentNom, currentPrenom, currentEmail, currentPic;

    
    

    public User(String id, String cinUser, String username, String nomUser, String prenomUser, String email, String numTelUser, String sexeUser, String password, String roleUser) {
        this.id = id;
        this.cinUser = cinUser;
        this.username = username;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.email = email;    
        this.numTelUser = numTelUser;
        this.sexeUser = sexeUser;
        this.password = password;
        this.roleUser = roleUser;
       
    }

    public static void setCurrentId(String currentId) {
        User.currentId = currentId;
    }

    public static void setCurrentUsername(String currentUsername) {
        User.currentUsername = currentUsername;
    }

    public static void setCurrentPassword(String currentPassword) {
        User.currentPassword = currentPassword;
    }

 
    public static void setCurrentEmail(String currentEmail) {
        User.currentEmail = currentEmail;
    }

  
    public static String getCurrentId() {
        return currentId;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static String getCurrentPassword() {
        return currentPassword;
    }

    

    public static String getCurrentEmail() {
        return currentEmail;
    }

   

 

    public User(String id, String cinUser, String username, String nomUser, String email, String password, String roleUser) {
        this.id = id;
        this.cinUser = cinUser;
        this.username = username;
        this.nomUser = nomUser;
        this.email = email;
        this.password = password;
        this.roleUser = roleUser;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCinUser(String cinUser) {
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

    public void setNumTelUser(String numTelUser) {
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

    public static void setCurrentCin(String currentCin) {
        User.currentCin = currentCin;
    }

    public static void setCurrentNom(String currentNom) {
        User.currentNom = currentNom;
    }

    public static void setCurrentPrenom(String currentPrenom) {
        User.currentPrenom = currentPrenom;
    }

    public static void setCurrentPic(String currentPic) {
        User.currentPic = currentPic;
    }

    public String getDateNaissanceUser() {
        return dateNaissanceUser;
    }

    public static String getCurrentCin() {
        return currentCin;
    }

    public static String getCurrentNom() {
        return currentNom;
    }

    public static String getCurrentPrenom() {
        return currentPrenom;
    }

    public static String getCurrentPic() {
        return currentPic;
    }

  
  
    public static void setCurrentuser(User currentuser) {
        User.currentuser = currentuser;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public String getCinUser() {
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

    public String getNumTelUser() {
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



    public static User getCurrentuser() {
        return currentuser;
    }

    @Override
    public String toString() {
        return "  id " + id + "   username " + username + "   nomUser " + nomUser + "   password "+ password +
                "roleuser" + roleUser+ '\n';
    }

   

    
}
