package it.mircobellinibaldella.carpooling;

import android.icu.util.LocaleData;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ConnessioneWebDB {

    static String server="http://mircocarpooling.altervista.org/android/";

    public static JSONArray Login(String email, String password){
        JSONArray resultsArray=null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(server+"login.php");

        List<NameValuePair> valoripost = new ArrayList<NameValuePair>(2);
        valoripost.add(new BasicNameValuePair("email", email));
        valoripost.add(new BasicNameValuePair("password", password));
        Log.d("aaa","ok");
        try {
            httppost.setEntity(new UrlEncodedFormEntity(valoripost));
            Log.d("aaa","1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            Log.d("aaa","2");
            HttpResponse response = httpclient.execute(httppost);
            Log.d("aaa","3");
            String json = EntityUtils.toString(response.getEntity());
            Log.d("aaa",json);
            resultsArray = new JSONArray(json);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultsArray;
    }
}
