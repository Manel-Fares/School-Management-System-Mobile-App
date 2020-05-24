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
public class Likes {
    
    private int idLike;
    private Books book;
    private User user;

    public Likes() {
    }

    public Likes(Books book, User user) {
     
        this.book = book;
        this.user = user;
    }

    public int getIdLike() {
        return idLike;
    }

    public void setIdLike(int idLike) {
        this.idLike = idLike;
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
        return "Likes{" + "idLike=" + idLike + ", book={" + book.toString() + ", user={" + user.toString() + '}';
    }
    
    
    
}
