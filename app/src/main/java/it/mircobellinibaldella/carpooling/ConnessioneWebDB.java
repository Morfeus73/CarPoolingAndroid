package it.mircobellinibaldella.carpooling;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnessioneWebDB extends AsyncTask<URL, Void, JSONObject> {

    static String server="http://mircocarpooling.altervista.org/android/";

    @Override
    protected JSONObject doInBackground(URL... param) {
        String stringaRes="";
        JSONObject result=null;
        try {
            HttpURLConnection urlConn = (HttpURLConnection) param[0].openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                stringaRes+=line;
            }
            reader.close();
        } catch (IOException e) {
            Log.d("ConnessioneWebDB","Eccezione\n"+e.getMessage());
        }
        try {
            result = new JSONObject(stringaRes);
        } catch (JSONException e) {
            Log.d("ConnessioneWebDB","Errore nella creazione del JSON in doInBackground()\n"+e.getMessage());
        }
        return result;
    }

    public JSONObject Login(String email, String password){
        try {
            return doInBackground(new URL(server+"/login.php?email="+email+"&password="+password));
        } catch (MalformedURLException e) {
            Log.d("ConnessioneWebDB","URL Scritto male in Login(String email, String password)");
        }
        return null;
    }
}
