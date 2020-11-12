package com.devforxkill.geekquote.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.devforxkill.geekquote.model.Quote;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME ="quote.db";
    private static final int DB_VERSION = 2;

    public DBHelper(Context context){
        super(context, DB_NAME, null,  DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Quote.DML_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Quote.TABLE);
        onCreate(db);
    }
}
