package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Agregar extends AppCompatActivity {

    private EditText txt_nombre;
    private EditText txt_telefono;
    private EditText txt_correo;
    private EditText txt_fecha;
    private Button btn_guardar;

    DAONotas dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        txt_nombre = (EditText)findViewById(R.id.txt_titulo);
        txt_telefono = (EditText)findViewById(R.id.txt_descripcion);
        txt_correo = (EditText)findViewById(R.id.txt_contenido);

        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        dao = new DAONotas(this);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txt_nombre.getText().toString();
                String correo = txt_correo.getText().toString();
                String telefono = txt_telefono.getText().toString();
                String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

                dao.insert(new Nota(0, nombre, correo, telefono, fecha));
                Intent principal = new Intent(Agregar.this, MainActivity.class);
                startActivity(principal);

            }
        });

    }
}
