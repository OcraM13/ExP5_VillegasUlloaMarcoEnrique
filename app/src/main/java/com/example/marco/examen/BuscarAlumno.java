package com.example.marco.examen;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.marco.examen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Controlador.AnalizadorJson;

public class BuscarAlumno extends Activity {

    ListView listaAlumnos;
    ArrayAdapter<String> adapter;
    String filtro="";

    ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_alumno);

        listaAlumnos = findViewById(R.id.listRes);

        new mostrarAlumnos().execute();

        adapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listaAlumnos.setAdapter(adapter);
    }

    class mostrarAlumnos extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {

            AnalizadorJson AnalizadorJSON = new AnalizadorJson();

            String url = "http://192.168.1.76/Web/JSON/consulta.php";
            JSONObject jsonObject = AnalizadorJSON.peticionesHTTP(url);
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("alumnos");

                String cadena = "";
                for (int i = 0; i<jsonArray.length(); i++){

                    //if(filtro.equals(jsonArray.getJSONObject(i).getString("c")) || filtro.equals("")){

                        cadena = jsonArray.getJSONObject(i).getString("nc") + " | "+
                                jsonArray.getJSONObject(i).getString("n") + " | "+
                                jsonArray.getJSONObject(i).getString("pa") + " | "+
                                jsonArray.getJSONObject(i).getString("sa") + " | "+
                                jsonArray.getJSONObject(i).getString("e") + " | "+
                                jsonArray.getJSONObject(i).getString("s") + " | "+
                                jsonArray.getJSONObject(i).getString("c");

                        arrayList.add(cadena);
                    }
               // }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbISC:
                if (checked)
                    filtro="Ingenieria Sistemas Computacionales";
                    break;
            case R.id.rbIM:
                if (checked)
                    filtro="Ingenieria Mecatonica";
                    break;
            case R.id.rbIIA:
                if (checked)
                    filtro="Ingenieria Industrias Alimentarias";
                    break;
            case R.id.rbLA:
                if (checked)
                    filtro="Licensiatura Administracion";
                    break;
            case R.id.rbLC:
                if (checked)
                    filtro="Linceniatura Contaduria";
                    break;
            default: filtro="";
        }
    }

}
