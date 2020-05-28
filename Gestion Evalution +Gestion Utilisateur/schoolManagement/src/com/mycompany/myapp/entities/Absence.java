/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;




/**
 *
 * @author Pytrooooo
 */
public class Absence {
    private int id;
    private User user;
    private Matiere matiere;
    private String date;
    private String timeDeb,timeFin;

    public Absence() {
    }

     public Absence(String date, String timedeb,String timefin) {
        this.date = date;
        this.timeDeb = timedeb;
        this.timeFin = timefin;
    }
    public Absence(int id, User user, Matiere matiere, String date, String timedeb,String timefin) {
        this.id = id;
        this.user = user;
        this.matiere = matiere;
        this.date = date;
        this.timeDeb = timedeb;
        this.timeFin = timefin;
    }
    public Absence( Matiere matiere, String date, String timedeb,String timefin) {
        this.matiere = matiere;
        this.date = date;
        this.timeDeb = timedeb;
        this.timeFin = timefin;
    }
    
    public Absence( User user, Matiere matiere, String date,String timedeb,String timefin) {      
        this.user = user;
        this.matiere = matiere;
        this.date = date;
        this.timeDeb = timedeb;
        this.timeFin = timefin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeDeb() {
        return timeDeb;
    }

    public void setTimeDeb(String timeDeb) {
        this.timeDeb = timeDeb;
    }

    public String getTimeFin() {
        return timeFin;
    }

    public void setTimeFin(String timeFin) {
        this.timeFin = timeFin;
    }

   


    @Override
    public String toString() {
        return "Absence{" + "id=" + id + ", user=" + user + ", matiere=" + matiere + ", date=" + date + ", timeDeb=" + timeDeb + ", timeFin=" + timeFin + '}';
    }

   
    
    
    
}
