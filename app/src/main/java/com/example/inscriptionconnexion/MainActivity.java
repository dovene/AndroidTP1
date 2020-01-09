package com.example.inscriptionconnexion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button connexionactivity;
    private Button inscriptionactivity;
    private String str_nom = "";
    private String str_prenom = "";
    private String str_psd = "";
    private String str_mdp = "";
    private ArrayList<Utilisateur> utilisateurs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Question 8 - Initialisation de la base de données
        DatabaseSQL db = new DatabaseSQL(this);
        db.onCreate(db.getWritableDatabase());

        connexionactivity = findViewById(R.id.connexionactivity);
        inscriptionactivity = findViewById(R.id.inscriptionactivity);


       recupererInformations();


        inscriptionactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inscriptionactivity();
            }
        });

        connexionactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connexionactivity();
            }
        });





    }

    private void recupererInformations(){
        // recupération des valeurs dans l'intent
        Intent intent = getIntent();
        if (intent.hasExtra("nom")) {
            str_nom = intent.getStringExtra("nom"); // on récupère la valeur associée à la clé
        }
        if (intent.hasExtra("prenom")) {
            str_prenom = intent.getStringExtra("prenom"); // on récupère la valeur associée à la clé
        }
        if (intent.hasExtra("pseudo")) {
            str_psd = intent.getStringExtra("pseudo"); // on récupère la valeur associée à la clé
        }
        if (intent.hasExtra("mdp")) {
            str_mdp = intent.getStringExtra("mdp"); // on récupère la valeur associée à la clé

        }

        // Question 9 - gestion de la liste des utilisateurs inscrits
        if (intent.hasExtra("liste_utilisateurs")){
            utilisateurs = (ArrayList<Utilisateur>) intent.getSerializableExtra("liste_utilisateurs");
        }
    }

    private void inscriptionactivity(){
        Intent intent = new Intent(this, InscriptionActivity.class);

        // Question 9
        intent.putExtra("liste_utilisateurs",utilisateurs);

        startActivity(intent);
    }


    private void connexionactivity(){
        Intent intent = new Intent(this, ConnexionActivity.class);
        if(!str_nom.equals("") && !str_mdp.equals("") && !str_prenom.equals("") && !str_psd.equals("")){
            intent.putExtra("nom", str_nom);
            intent.putExtra("prenom", str_prenom);
            intent.putExtra("pseudo", str_psd);
            intent.putExtra("mdp", str_mdp);


            // Question 9
            intent.putExtra("liste_utilisateurs",utilisateurs);

        }
        startActivity(intent);
    }



}
