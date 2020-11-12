package com.devforxkill.geekquote.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.devforxkill.geekquote.model.Quote;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class QuoteDaoSqlite extends BaseDaoSqlite  implements  QuoteDao{

    public QuoteDaoSqlite(Context context){
        super(context);
    }
    @Override
    public ArrayList<Quote> getQuotes() {
        // créer une nouvelle liste de quotes que l'on va ensuite remplir à partir des infos de la base
        ArrayList<Quote> list = new ArrayList<Quote>();
        // colonnes que l'on souhaite récupérer dans la table quotes (= SELECT x, y, z ...)
        String[] cols = {Quote.ID, Quote.STR_QUOTE, Quote.RATING, Quote.DATE};
//        String[] cols = {Quote.ID};
        // executer la requete (= SELECT * FROM quotes)
        Cursor cursor = getDB().query(Quote.TABLE, cols, null, null, null, null, null);
        // aller au début de la liste récupérée
        cursor.moveToFirst();
        // parcourir la liste tant que l'on est pas arrivé à la fin de la boucle
        while(!cursor.isAfterLast()) {
            // récupérer une instance de date à partir d'une chaine de caractère
            LocalDate date = LocalDate.parse(cursor.getString(3), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            // créer un nouvel objet Quote à partir des infos récupérées de la base
//            Quote quote = new Quote((int)cursor.getLong(0),"", 0, LocalDate.now());
            Quote quote = new Quote((int)cursor.getLong(0), cursor.getString(1), cursor.getInt(2), date);
            // et l'ajouter dans la liste de quotes
            list.add(quote);
            // avancer le curseur au prochain élément du la liste récupérée
            cursor.moveToNext();
        }
        // fermer la connexion à la base
        cursor.close();     // obligatoire
        getDB().close();    // performance : on est dans une application qui ne vas pas taper dans le base de manière intensivement
        // puis retourner la liste de quotes
        return list;
    }

    @Override
    public int addQuote(Quote q) {
        // mapper pour insérer dans la table
        ContentValues values = mapFromQuote(q);
        // inserer ces valeurs dans la table
        int id = (int) getDB().insert(Quote.TABLE, null, values);
        // puis fermer la connexion
        getDB().close();
        return id;
    }

    @Override
    public void updateQuote(Quote q) {
        // mapper pour modifier dans la table
        ContentValues values = mapFromQuote(q);
        // ajouter un paramètre dans la requête
        String[] params = { ""+q.getId() };
        // executer la requête d'update
        getDB().update(Quote.TABLE, values, Quote.ID + " = ?", params);
        // puis fermer la connexion
        getDB().close();

    }

    @Override
    public Quote getByID(int id) {
        Quote res = null;
        String[] cols = {Quote.ID, Quote.STR_QUOTE, Quote.RATING, Quote.DATE};
        String[] params = { ""+id };
        Cursor cursor = getDB().query(Quote.TABLE, cols, "id=?", params, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {  // meilleur qu'un try catch
            LocalDate date = LocalDate.parse(cursor.getString(3), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            res = new Quote((int)cursor.getLong(0), cursor.getString(1), cursor.getInt(2), date);
            cursor.moveToNext();
        }

        cursor.close();
        getDB().close();

        return res;
    }

    protected ContentValues mapFromQuote(Quote quote) {
        // sélectionner les valeurs à ajouter dans la table
        ContentValues values = new ContentValues();
        values.put(Quote.STR_QUOTE, quote.getStrQuote());
        values.put(Quote.RATING, quote.getRating());
        values.put(Quote.DATE, quote.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return values;
    }
}
