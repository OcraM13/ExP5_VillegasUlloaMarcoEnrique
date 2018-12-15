package Controlador;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class AnalizadorJson {

    InputStream is = null;
    JSONObject jsonObject = null;
    String json = null;
    OutputStream os;

    //metodos para altas, bajas y cambios


    public JSONObject peticionesHTTP(String url, String metodo, Map datos) {
        HttpURLConnection conexion = null;
        URL mUrl = null;

        //magia
        String cadenaJSON = null;
        try {
             cadenaJSON = "{\"nc\":\"" + URLEncoder.encode(String.valueOf(datos.get("nc")), "UTF-8")+
                    "\", \"n\":\"" + URLEncoder.encode(String.valueOf(datos.get("n")), "UTF-8")+
                    "\", \"pa\":\"" + URLEncoder.encode(String.valueOf(datos.get("pa")), "UTF-8")+
                    "\", \"sa\":\"" + URLEncoder.encode(String.valueOf(datos.get("sa")), "UTF-8")+
                    "\", \"e\":\"" + URLEncoder.encode(String.valueOf(datos.get("e")), "UTF-8")+
                    "\", \"s\":\"" + URLEncoder.encode(String.valueOf(datos.get("s")), "UTF-8")+
                    "\", \"c\":\"" + URLEncoder.encode(String.valueOf(datos.get("c")), "UTF-8")+ "\"}";

        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        try {
            mUrl = new URL(url);
            conexion = (HttpURLConnection) mUrl.openConnection();

            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);

            conexion.setFixedLengthStreamingMode(cadenaJSON.length());

            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            os = new BufferedOutputStream(conexion.getOutputStream());

            os.write(cadenaJSON.getBytes());
            os.flush();
            os.close();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder cadena = new StringBuilder();

            String linea;

            while ((linea = br.readLine()) != null){
                cadena.append(linea+"\n");
            }
            is.close();

            json = cadena.toString();

            Log.i("Mensaje 1 >>>>>", "Respuesta JSON: " + json);

            jsonObject = new JSONObject(json);
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonObject;
    }

    /*public JSONObject peticionHTTP(String url) {
        HttpURLConnection conexion = null;
        URL mUrl = null;

        try {
            mUrl = new URL(url);
            conexion = (HttpURLConnection) mUrl.openConnection();

            //activamos el envio de datos a traves de POST
            conexion.setDoOutput(true);

            //Establecer formato de codificación estándar para los datos enviados
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStream os = new BufferedOutputStream(conexion.getOutputStream());
            os.flush();
            os.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Recibir respuesta HTTP desde PHP

        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder cad = new StringBuilder();
            String fila;
            while ( (fila = br.readLine()) != null) {
                cad.append(fila + "\n");
            }

            is.close();
            json = cad.toString();

            Log.i("MSJ", "cadena JSON" + cad);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("MSJ", "Error en BufferedReader." + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MSJ", "Error en BufferedReader." + e.toString());
        }

        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("MSJ", "Error al convertir la cadena en cadena JSON");
        }

        return jsonObject;
    }//Metodo peticionHTTP*/

    public JSONObject peticionesHTTP(String url) {
        HttpURLConnection conexion = null;
        URL mUrl = null;

        try {
            mUrl = new URL(url);
            conexion = (HttpURLConnection) mUrl.openConnection();
            conexion.setDoOutput(true);
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            os = new BufferedOutputStream(conexion.getOutputStream());

            os.flush();
            os.close();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            is = new BufferedInputStream(conexion.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder cadena = new StringBuilder();

            String linea;

            while ((linea = br.readLine()) != null){
                cadena.append(linea+"\n");
            }
            is.close();

            json = cadena.toString();

            Log.i("Mensaje 1 >>>>>", "Respuesta JSON: " + json);

            jsonObject = new JSONObject(json);
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonObject;
    }
}
