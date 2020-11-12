package com.devforxkill.geekquote.dao;

import com.devforxkill.geekquote.model.Quote;

import java.util.ArrayList;

public interface QuoteDao {

    public ArrayList<Quote> getQuotes();
    public int addQuote(Quote q);
    public void updateQuote(Quote q);
    public Quote getByID(int id);
}
