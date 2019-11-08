package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Mostrarnota extends AppCompatActivity {

    TextView titulo;
    TextView descripcion;
    TextView contenido;
    String[] datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarnota_layout);
        String recuperarDatos = getIntent().getStringExtra("minota");

        titulo = (TextView)findViewById(R.id.txtmostrar_titulo);
        descripcion = (TextView)findViewById(R.id.txtmostrar_descripcion);
        contenido = (TextView)findViewById(R.id.txtmostrar_contenido);


        datos = recuperarDatos.split(",");

        titulo.setText(datos[1]);
        descripcion.setText(datos[2]);
        contenido.setText(datos[3]);





    }
}
