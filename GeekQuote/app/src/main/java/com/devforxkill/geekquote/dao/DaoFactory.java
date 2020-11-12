package com.devforxkill.geekquote.dao;

import android.content.Context;

public class DaoFactory {   // point d'accès à la base générique

    private static QuoteDao quoteDao;

    private DaoFactory(){} // interdiction d'en créer une instance

    public static QuoteDao getQuoteDao(Context context){
        if(quoteDao == null){
            quoteDao = new QuoteDaoSqlite(context);
        }
        return quoteDao;    // quoteDao est un singleton
    }
}
