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
public class Rating {

    private int idR;
    private double rating;
    private Club c;
    private User et;
    
    public Rating() {
    }
    
    public Rating(int idR, double rating, int c, int et) {
        this.idR = idR;
        this.rating = rating;
        this.c.setIdClub(c);
        this.et.setId(String.valueOf(et));
    }

    public Rating(double rating, Club c, User et) {
        this.rating = rating;
        this.c = c;
        this.et = et;
    }
    
    public int getIdR() {
        return idR;
    }
    
    public Club getC() {
        return c;
    }
    
    public User getEt() {
        return et;
    }
    
    public void setIdR(int idR) {
        this.idR = idR;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public void setC(Club c) {
        this.c = c;
    }
    
    public void setEt(User et) {
        this.et = et;
    }

    public Rating(int idR,double rating, Club c, User et) {
        this.idR = idR;
        this.rating = rating;
        this.c = c;
        this.et = et;
    }

    @Override
    public String toString() {
        return "Rating{" + "idR=" + idR + ", rating=" + rating + ", c=" + c + ", et=" + et + '}';
    }
    
}
