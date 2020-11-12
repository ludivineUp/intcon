package com.devforxkill.geekquote.model;

import com.devforxkill.geekquote.mapping.QuoteMapping;

import java.io.Serializable;
import java.time.LocalDate;

// Javabean c'est une classe qui a les attributs privées, au moins constructeur et les getter/setter
// Serializable permet de stocker/transformer un objet sous forme de bytecode (donc en string unique) avec un identifiant unique
// Tous les objets de java sont sérializable de base (Strgin, Arraylist, LocalDate...)
public class Quote implements Serializable, QuoteMapping {

    private int id;
    private String strQuote;
    private int rating;
    private LocalDate date;

    public Quote(int id, String strQuote, int rating, LocalDate date) {
        this.id = id;
        this.strQuote = strQuote;
        this.rating = rating;
        this.date = date;
    }

    public String getStrQuote() {
        return strQuote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStrQuote(String strQuote) {
        this.strQuote = strQuote;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return strQuote;
    }
}
