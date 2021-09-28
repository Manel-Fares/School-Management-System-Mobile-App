/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author asus
 */
public class Participation {
    private int idparticipation;
    private User u;
    private Evenement e;

    public Participation(User u, Evenement e) {
        this.u = u;
        this.e = e;
    }

    public Participation(int idparticipation, User u, Evenement e) {
        this.idparticipation = idparticipation;
        this.u = u;
        this.e = e;
    }

    public int getIdparticipation() {
        return idparticipation;
    }

    public void setIdparticipation(int idparticipation) {
        this.idparticipation = idparticipation;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Evenement getE() {
        return e;
    }

    public void setE(Evenement e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "Participation{" + "idparticipation=" + idparticipation + ", u=" + u + ", e=" + e + '}';
    }
    
    
}
