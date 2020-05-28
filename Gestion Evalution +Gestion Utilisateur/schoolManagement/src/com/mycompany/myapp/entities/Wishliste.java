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
public class Wishliste {
    
    private int idwish;
    private Books book;
    private User user;

    public Wishliste() {
    }

    public Wishliste(Books book, User user) {
        this.book = book;
        this.user = user;
    }

    public Wishliste(int idwish, Books book, User user) {
        this.idwish = idwish;
        this.book = book;
        this.user = user;
    }

    public int getIdwish() {
        return idwish;
    }

    public void setIdwish(int idwish) {
        this.idwish = idwish;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Wishliste{" + "idwish=" + idwish + ", book=" + book.toString() + ", user=" + user.toString() + '}';
    }
    
    
}
