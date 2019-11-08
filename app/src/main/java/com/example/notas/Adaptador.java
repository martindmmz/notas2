package com.example.notas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private ArrayList<Nota> notas= new ArrayList<>();
    private Context context;

    public Adaptador(Context contexto, ArrayList<Nota> entrada){

        this.context = contexto;
        this.notas = entrada;
    }


    @Override
    public int getCount() {

        return notas.size();
    }

    @Override
    public Object getItem(int position) {

        return notas.get(position);

    }


    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Nota objeto = (Nota) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.listarelementos, null);
        ImageView imagen = (ImageView)convertView.findViewById(R.id.imagen_lv);
        TextView titulo = (TextView)convertView.findViewById(R.id.titulo_lv);
        TextView descripcion = (TextView)convertView.findViewById(R.id.descripcion_lv);

        imagen.setImageResource(R.drawable.nota);
        titulo.setText(objeto.getTitulo());
        descripcion.setText(objeto.getDescripcion()+"\n"+objeto.getFecha());

        return convertView;
    }

}
