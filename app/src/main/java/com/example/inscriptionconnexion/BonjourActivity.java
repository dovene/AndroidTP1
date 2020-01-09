package com.example.inscriptionconnexion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class BonjourActivity extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private EditText pseudo;
    private EditText motdepasse;
    private Button boutonvalider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonjour);

        //On instancie les éléments de l'interface
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        pseudo = findViewById(R.id.pseudo);
        motdepasse = findViewById(R.id.motdepasse);
        boutonvalider = findViewById(R.id.retour);


        String str_nom = "";
        String str_prenom = "";
        String str_psd = "";
        String str_mdp = "";
        //On instancie l'objet correspondant au bouton
        //Création de l'intent
        Intent intent = getIntent();
        if (intent.hasExtra("nom")) { // vérifie qu'une valeur est associée à la clé “edittext”
            str_nom = intent.getStringExtra("nom"); // on récupère la valeur associée à la clé
        }
        if (intent.hasExtra("prenom")) { // vérifie qu'une valeur est associée à la clé “edittext”
            str_prenom = intent.getStringExtra("prenom"); // on récupère la valeur associée à la clé
        }
        if (intent.hasExtra("pseudo")) { // vérifie qu'une valeur est associée à la clé “edittext”
            str_psd = intent.getStringExtra("pseudo"); // on récupère la valeur associée à la clé
        }
        if (intent.hasExtra("mdp")) { // vérifie qu'une valeur est associée à la clé “edittext”
            str_mdp = intent.getStringExtra("mdp"); // on récupère la valeur associée à la clé
        }
        TextView textView = (TextView) findViewById(R.id.textView);
        //Ajouter du text dans un textView
        textView.setText("Bonjour " + str_nom + " " + str_prenom + ".");

        boutonvalider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BonjourActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}


