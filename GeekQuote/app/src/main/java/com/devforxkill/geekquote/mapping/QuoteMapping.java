package com.devforxkill.geekquote.mapping;

import android.media.Rating;

public interface QuoteMapping extends BaseMapping {

    public static final String STR_QUOTE = "str_quote";
    public static final String RATING= "rating";
    public static final String DATE = "creation_date";

    /*
        DML : équivalent du script de la création de table
     */

    public static final String TABLE = "quotes";
    public static final String DML_CREATE  =
            "CREATE TABLE "+ TABLE + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  +
                    STR_QUOTE + " TEXT NOT NULL, " +
                    RATING + " TEXT NOT NULL, " +
                    DATE + " TEXT NOT NULL"+");";

}
