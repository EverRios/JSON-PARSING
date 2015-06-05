package com.example.everardo.json_parsing;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity {
    // SE DECLARA VARIABLE
    private Context context;


    // DIRECCION DONDE OBTENDRA LOS DATOS DEl ARCHIVO.JSON
    private static String url = "http://eulisesrdz.260mb.net/eve/aabbcc.json";

    // Nodos keys Del JSON
    static final String KEY_ID_PERSONA = "id_persona";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_APELLIDOS = "apellidos";
    static final String KEY_TIPO_EXAMEN = "tipo_examen";
    static final String KEY_NIVEL = "nivel";
    static final String KEY_FECHA = "fecha";

    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ProgressTask(MainActivity.this).execute();
    }

    // METOS QUE MUESTRA LA BARRA DE PROGRESO
    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;

        private ListActivity activity;

        //Lista privada mensajes <Message>;
        public ProgressTask(ListActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);

        }
        /** progress dialog to show user that the backup is processing. */

        /**
         * application context.
         */
        private Context context;

        //MENSAJE QUE SE MOSTRARA EL BARRA DE PROGRESO
        protected void onPreExecute() {
            this.dialog.setMessage("Cargando...");
            this.dialog.show();
        }

        //MANDA LOS VALORES A ITEM
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ListAdapter adapter = new SimpleAdapter(context, jsonlist,
                    R.layout.list_item, new String[]{KEY_ID_PERSONA, KEY_NOMBRE, KEY_APELLIDOS, KEY_TIPO_EXAMEN, KEY_NIVEL, KEY_FECHA}, new int[]{
                    R.id.txt_l_idpersona, R.id.txt_l_nombre, R.id.txt_l_apellidos, R.id.txt_l_tipo_examen, R.id.txt_l_nivel, R.id.txt_l_fecha});

            setListAdapter(adapter);


            ///seleccionar elemento ListView item
            lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // obtener los valores de ListItem seleccionado
                    String id_persona = ((TextView) view.findViewById(R.id.txt_l_idpersona)).getText().toString();
                    String nombre = ((TextView) view.findViewById(R.id.txt_l_nombre)).getText().toString();
                    String apellidos  = ((TextView) view.findViewById(R.id.txt_l_apellidos)).getText().toString();
                    String tipo_examen = ((TextView) view.findViewById(R.id.txt_l_tipo_examen)).getText().toString();
                    String nivel = ((TextView) view.findViewById(R.id.txt_l_nivel)).getText().toString();
                    String fecha = ((TextView) view.findViewById(R.id.txt_l_fecha)).getText().toString();

                    // MANDA LOS VALORES A LA VISTA INDIVIDUAL
                    Intent in = new Intent(getApplicationContext(), vista_individual.class);
                    in.putExtra(KEY_ID_PERSONA, id_persona);
                    in.putExtra(KEY_NOMBRE, nombre);
                    in.putExtra(KEY_APELLIDOS, apellidos);
                    in.putExtra(KEY_TIPO_EXAMEN,  tipo_examen);
                    in.putExtra(KEY_NIVEL, nivel);
                    in.putExtra(KEY_FECHA, fecha);

                    startActivity(in);

                }
            });
        }
        protected Boolean doInBackground(final String... args) {

            JsonParse jParser = new JsonParse();

            // obtiene los valores del json mediante la url
            JSONArray json = jParser.getJSONFromUrl(url);

            for (int i = 0; i < json.length(); i++) {

                try {
                    JSONObject c = json.getJSONObject(i);

                    String id_persona = c.getString(KEY_ID_PERSONA);
                    String nombre = c.getString(KEY_NOMBRE);
                    String apellidos = c.getString(KEY_APELLIDOS);
                    String tipo_examen = c.getString(KEY_TIPO_EXAMEN);
                    String nivel = c.getString(KEY_NIVEL);
                    String fecha = c.getString(KEY_FECHA);


                    HashMap<String, String> map = new HashMap<String, String>();

                    // Se añaden cada nodo hijo al HashMap con su respectiva clave
                    map.put(KEY_ID_PERSONA, id_persona);
                    map.put(KEY_NOMBRE, nombre);
                    map.put(KEY_APELLIDOS, apellidos);
                    map.put(KEY_TIPO_EXAMEN, tipo_examen);
                    map.put(KEY_NIVEL, nivel);
                    map.put(KEY_FECHA, fecha);
                    jsonlist.add(map);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;
        }
    }
}
