package com.example.notas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAONotas {
    SQLiteDatabase _sqLiteDatabase;
    Context ctx;

    public DAONotas(Context ctx) {
        this.ctx = ctx;
        _sqLiteDatabase =
                new baseDatos(ctx).getWritableDatabase();
    }

    public long insert(Nota nota) {


        ContentValues contentValues
                = new ContentValues();

        contentValues.put(baseDatos.COLUMNS_NAME_NOTAS[1],
                nota.getTitulo());
        contentValues.put(baseDatos.COLUMNS_NAME_NOTAS[2],
                nota.getDescripcion());
        contentValues.put(baseDatos.COLUMNS_NAME_NOTAS[3],
                nota.getContenido());
        contentValues.put(baseDatos.COLUMNS_NAME_NOTAS[4],
                nota.getFecha());


        return _sqLiteDatabase.insert(baseDatos.TABLE_NAME_NOTAS,
                null, contentValues);

    }

    public ArrayList<Nota> getAll() {
        ArrayList<Nota> lst = null;

        Cursor c = _sqLiteDatabase.query(baseDatos.TABLE_NAME_NOTAS,
                baseDatos.COLUMNS_NAME_NOTAS,
                null,
                null,
                null,
                null,
                null,
                null);

        if (c.moveToFirst()) {
            lst = new ArrayList<Nota>();
            do {
                Nota contacto =
                        new Nota(c.getInt(0), c.getString(1),
                                c.getString(2), c.getString(3), c.getString(4));
                lst.add(contacto);

            } while (c.moveToNext());
        }
        return lst;

    }

    public Cursor getAllCursor() {
        Cursor c = _sqLiteDatabase.query(baseDatos.TABLE_NAME_NOTAS,
                baseDatos.COLUMNS_NAME_NOTAS,
                null,
                null,
                null,
                null,
                null,
                null);
        return c;
    }

    public ArrayList<Nota> buscar(String name){

        ArrayList<Nota> lst = null;


            Cursor c = _sqLiteDatabase.query("Notas", baseDatos.COLUMNS_NAME_NOTAS, "titulo" + " LIKE '%" + name + "%'",
                    null,
                    null,
                    null,
                    null);

            if (c.moveToFirst()) {
                lst = new ArrayList<Nota>();
                do {
                    Nota contacto =
                            new Nota(c.getInt(0), c.getString(1),
                                    c.getString(2), c.getString(3), c.getString(4));
                    lst.add(contacto);

                } while (c.moveToNext());
            }

            return lst;


    }


    public void borrar(int id) {

        String consulta = "id=" + id;
        _sqLiteDatabase.delete("Notas", consulta, null);



    }


    public void  editar(Nota nota){

        int id = nota.getId();
        ContentValues valores = new ContentValues();
        String update = "0,";

        //"_id", "usuario", "email", "tel", "fecNacimiento"
        valores.put("titulo",nota.getTitulo());
        valores.put("descripcion",nota.getDescripcion());
        valores.put("contenido",nota.getContenido());
        valores.put("fecha",nota.getFecha());


        _sqLiteDatabase.update(baseDatos.TABLE_NAME_NOTAS, valores,"id="+id,null);


    }



}
