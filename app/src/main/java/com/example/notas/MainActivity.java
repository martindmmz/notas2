package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.SearchView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements  SearchView.OnQueryTextListener{
    public static final int REQUEST_CODE = 10;
    private Button btn_agregar;
    private ListView lv_contactos;
    private ArrayAdapter<Nota> adaptador;
    int usuarioseleccionado = -1;
    private Object mActionMode;
    private SearchView busqueda;
    private FloatingActionButton fab;
    Intent act;
    DAONotas dao;

    Toast j;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        busqueda =
                (SearchView) menu.findItem(R.id.buscador).getActionView();
        busqueda.setOnQueryTextListener(this);
        j = Toast.makeText(this, "click",Toast.LENGTH_SHORT);






        return true;
    }


    public void OnCLick() {

                lv_contactos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                lv_contactos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                usuarioseleccionado = position;
                mActionMode = MainActivity.this.startActionMode(amc);
                view.setSelected(true);

                return true;
            }
        });

                lv_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int posicion = position;
                        mostrar(posicion);

                    }
                });
    }


    private ActionMode.Callback amc = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.opciones,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getItemId()==R.id.Eliminaritem){
                eliminarItem();
                mode.finish();

            }
            else if(item.getItemId()==R.id.Editaritem){
                editarItem();

            }


            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);


        act = new Intent(getApplicationContext(), mostrar.class);

        dao = new DAONotas(this);
        baseDatos a = new baseDatos(this);
        fab = (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(act);

            }
        });

        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        Nota nota = new Nota(0,"Cocinar el pastel.","Cocinar el pastel para la fiesta","Recordar que el pastel debe ser cocinado",fecha);

        ContentValues valores = new ContentValues();

        valores.put("titulo",nota.getTitulo());
        valores.put("descripcion",nota.getDescripcion());
        valores.put("contenido",nota.getContenido());
        valores.put("fecha",nota.getFecha());





    // dao._sqLiteDatabase.insert(MiDB.TABLE_NAME_NOTAS,
         //       null, valores);


          lv_contactos = (ListView) findViewById(R.id.lv_contactos);
          Adaptador adapt = new Adaptador(this, dao.getAll());
          lv_contactos.setAdapter(adapt);
          registerForContextMenu(lv_contactos);
          OnCLick();
    }


    public void eliminarItem() {
        {

            DAONotas dao = new DAONotas(this);
            ArrayList<Nota> datos = new ArrayList<>();
            datos = dao.getAll();

            int id = datos.get(usuarioseleccionado).getId();


            dao.borrar(id);
            lv_contactos = (ListView) findViewById(R.id.lv_contactos);
            Adaptador adaptador = new Adaptador(this, dao.getAll());
            lv_contactos.setAdapter(adaptador);
            registerForContextMenu(lv_contactos);


        }



    }

    public void editarItem() {
        {
            DAONotas dao = new DAONotas(this);
            ArrayList<Nota> datos = new ArrayList<>();
            datos = dao.getAll();

            Nota seleccion = datos.get(usuarioseleccionado);
            String pasar = seleccion.getId()+","+seleccion.getTitulo()+","+seleccion.getDescripcion()+","+seleccion.getContenido()+","+seleccion.getFecha();
            Intent v = new Intent(getApplicationContext(), Editar.class);
            v.putExtra("recuperar",pasar);
            startActivity(v);
        }
    }



    public  void buscar(String s){

       try {

           adaptador = new ArrayAdapter<Nota>(this, android.R.layout.simple_list_item_1,  dao.buscar(s));
           lv_contactos.setAdapter(adaptador);

       }catch (Exception e){


       }
    }
    public  void mostrar(int posicion){

        try {

            DAONotas dao = new DAONotas(this);
            ArrayList<Nota> datos = new ArrayList<>();
            datos = dao.getAll();

            Nota seleccion = datos.get(posicion);
            String pasar = seleccion.getId()+","+seleccion.getTitulo()+","+seleccion.getDescripcion()+","+seleccion.getContenido()+","+seleccion.getFecha();
            Intent v = new Intent(getApplicationContext(), Mostrarnota.class);
            v.putExtra("minota",pasar);
            startActivity(v);



        }catch (Exception e){


        }
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        buscar(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        buscar(s);
        return true;

    }
}

