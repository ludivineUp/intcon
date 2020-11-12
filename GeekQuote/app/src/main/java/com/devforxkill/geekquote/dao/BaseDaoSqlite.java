package com.devforxkill.geekquote.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.devforxkill.geekquote.helper.DBHelper;

// ce qui est générique à la base de données
// equivalent PDO de php
public class BaseDaoSqlite {

    protected DBHelper dbHelper;
    protected SQLiteDatabase db;

    public BaseDaoSqlite(Context context){
        dbHelper = new DBHelper(context);
    }

    protected SQLiteDatabase getDB(){
        if(db == null || !db.isOpen()){ // isOpen condition = Parano liée au crash possible d'android
            db = dbHelper.getWritableDatabase();
        }   // db est une sorte de Singleton
        return db;
    }
}
