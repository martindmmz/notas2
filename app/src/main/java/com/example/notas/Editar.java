package com.example.notas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class Editar extends AppCompatActivity {

    private EditText txt_nombre;
    private EditText txt_telefono;
    private EditText txt_correo;
    private EditText txt_fecha;
    private Button btn_guardar;
    Nota UnContacto;
    DAONotas dao;
    String[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        String recuperarDatos = getIntent().getStringExtra("recuperar");
        datos = recuperarDatos.split(",");

        txt_nombre = (EditText)findViewById(R.id.txt_titulo);
        txt_telefono = (EditText)findViewById(R.id.txt_descripcion);
        txt_correo = (EditText)findViewById(R.id.txt_contenido);

        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        dao = new DAONotas(this);

        txt_nombre.setText(datos[1]);
        txt_telefono.setText(datos[2]);
        txt_correo.setText(datos[3]);
        txt_fecha.setText(datos[4]);


        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txt_nombre.getText().toString();
                String correo = txt_correo.getText().toString();
                String telefono = txt_telefono.getText().toString();
                String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

                Nota editado = new Nota(Integer.parseInt(datos[0]),nombre,correo,telefono,fecha);
                dao.editar(editado);
                Intent principal = new Intent(Editar.this, MainActivity.class);
                startActivity(principal);

            }
        });





    }



}
