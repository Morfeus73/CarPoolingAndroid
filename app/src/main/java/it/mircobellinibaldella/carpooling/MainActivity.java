package it.mircobellinibaldella.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button loginButton,registratiButton, visualizzaMappaButton;
    EditText email, password;
    TextView titolo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        loginButton=findViewById(R.id.mainLogin);
        registratiButton=findViewById(R.id.mainRegistrati);
        visualizzaMappaButton=findViewById(R.id.mainMappa);
        email=findViewById(R.id.mainEmail);
        password=findViewById(R.id.mainPassword);
        titolo=findViewById(R.id.mainTitolo);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnessioneWebDB connessioneWebDB = new ConnessioneWebDB();
                JSONObject jsonObject = connessioneWebDB.Login(email.getText().toString(),password.getText().toString());
                try {
                    int risposta = jsonObject.getInt("risposta");
                    String testo = jsonObject.getString("testo");

                    switch (risposta){
                        case 0:
                            Toast toast = Toast.makeText(MainActivity.this,testo,Toast.LENGTH_LONG);
                            toast.show();
                            break;
                        case 1:
                            email.setEnabled(false);
                            password.setEnabled(false);
                            loginButton.setVisibility(View.GONE);
                            registratiButton.setVisibility(View.GONE);
                            titolo.setText(testo);
                            visualizzaMappaButton.setVisibility(View.VISIBLE);
                            break;

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        visualizzaMappaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Avvia l'activity della mappa
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });

    }
}
