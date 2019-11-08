package com.example.notas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class baseDatos
        extends SQLiteOpenHelper {


    public static String SCRIPT_DB = "create table Notas (" +
            "id integer primary key autoincrement," +
            "titulo text not null," +
            "descripcion text not null," +
            "contenido text not null," +
            "fecha String not null);"
            ;

    public static final String[] COLUMNS_NAME_NOTAS =
            {
             "id", "titulo", "descripcion", "contenido", "fecha"
            };

    public  static final String TABLE_NAME_NOTAS =
            "Notas";

    public baseDatos(@Nullable Context context) {

        super(context,
                "mibaseD",
                null,
                1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
