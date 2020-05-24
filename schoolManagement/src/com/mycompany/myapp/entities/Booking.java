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
public class Booking {

    private int idBooking;

    private User user;
    private Books book;

    public Booking() {
    }

    public Booking(User user, Books book) {
        this.user = user;
        this.book = book;
    }

    public Booking(int idBooking, User user, Books book) {
        this.idBooking = idBooking;
        this.user = user;
        this.book = book;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Booking{" + "idBooking=" + idBooking + ", user=" + user.toString() + ", book=" + book.toString() + '}';
    }

}
