package it.mircobellinibaldella.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    Button loginButton,registratiButton;
    EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        loginButton=findViewById(R.id.mainLogin);
        registratiButton=findViewById(R.id.mainRegistrati);
        email=findViewById(R.id.mainEmail);
        password=findViewById(R.id.mainPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONArray jsonArray = ConnessioneWebDB.Login(email.getText().toString(),password.getText().toString());
                try {
                    int risposta = jsonArray.getJSONObject(0).getInt("risposta");
                    String testo = jsonArray.getJSONObject(0).getString("testo");

                    switch (risposta){
                        case 0:
                            Log.d("aburbe","Errore");
                            break;
                        case 1:
                            email.setEnabled(false);
                            password.setEnabled(false);
                            loginButton.setVisibility(View.GONE);
                            registratiButton.setVisibility(View.GONE);
                            break;

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        registratiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Avvia l'activity della mappa
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });

    }
}
