package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarNota extends AppCompatActivity {

    private EditText txt_titulo;
    private EditText txt_descripcion;
    private EditText txt_contenido;
    private EditText txt_fecha;
    private Button btn_guardar;
    Nota UnContacto;
    DAONotas dao = new DAONotas(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        txt_titulo = (EditText)findViewById(R.id.txt_titulo);
        txt_descripcion = (EditText)findViewById(R.id.txt_descripcion);
        txt_contenido = (EditText)findViewById(R.id.txt_contenido);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);


        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titulo = txt_titulo.getText().toString();
                String descripcion = txt_descripcion.getText().toString();
                String contenido = txt_contenido.getText().toString();
                String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                dao.insert(new Nota(0, titulo, descripcion, contenido, fecha));
                Intent principal = new Intent(AgregarNota.this, MainActivity.class);



            }
        });





}




}
