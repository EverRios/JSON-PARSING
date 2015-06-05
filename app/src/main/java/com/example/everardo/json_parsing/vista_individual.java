package com.example.everardo.json_parsing;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class vista_individual extends ActionBarActivity {

    // SE DECLARAN VARIABLES
    static final String KEY_ID_PERSONA = "id_persona";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_APELLIDOS = "apellidos";
    static final String KEY_TIPO_EXAMEN = "tipo_examen";
    static final String KEY_NIVEL = "nivel";
    static final String KEY_FECHA = "fecha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_individual);

        Intent in =getIntent();

        // Obtener valores XML de la intención previa
        String id_persona = in.getStringExtra(KEY_ID_PERSONA);
        String nombre = in.getStringExtra(KEY_NOMBRE);
        String apellidos = in.getStringExtra(KEY_APELLIDOS);
        String tipo_examen = in.getStringExtra(KEY_TIPO_EXAMEN);
        String nivel = in.getStringExtra(KEY_NIVEL);
        String fecha = in.getStringExtra(KEY_FECHA);

        // lanza todos los valores en la pantalla
        TextView lblid = (TextView) findViewById(R.id.txt_idpersona);
        TextView lblnombre = (TextView) findViewById(R.id.txt_nombre);
        TextView lblapellidos = (TextView) findViewById(R.id.txt_apellidos);
        TextView lbltipo = (TextView) findViewById(R.id.txt_tipo_examen);
        TextView lblnivel = (TextView) findViewById(R.id.txt_nivel);
        TextView lblfecha = (TextView) findViewById(R.id.txt_fecha);

        lblid.setText(id_persona);
        lblnombre.setText(nombre);
        lblapellidos.setText(apellidos);
        lbltipo.setText(tipo_examen);
        lblnivel.setText(nivel);
        lblfecha.setText(fecha);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vista_individual, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
