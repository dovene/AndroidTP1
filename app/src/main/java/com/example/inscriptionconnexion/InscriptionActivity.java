package com.example.inscriptionconnexion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class InscriptionActivity extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private EditText pseudo;
    private EditText motdepasse;
    private Button boutonvalider;
    private ArrayList<Utilisateur> utilisateurs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        //On instancie les éléments de l'interface
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        pseudo = findViewById(R.id.pseudo);
        motdepasse = findViewById(R.id.motdepasse);
        boutonvalider = findViewById(R.id.boutonvalider);



//Au clique sur le bouton
        boutonvalider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);// On créé un Intent permettant de changer d'activité. On passe de MainActivity à BeginGame
                //On recupere les champs remplis par l'utilisateur
                String nom_str = nom.getText().toString();
                String prn_str = prenom.getText().toString();
                String psd_str = pseudo.getText().toString();
                String mdp_str = motdepasse.getText().toString();
//On verifie si le champs n'est pas vide, puis on ajoute des extras à notre Intent
                if(!psd_str.equals("") && !mdp_str.equals("") && !prn_str.equals("") && !nom_str.equals("")){
                    // Mettre les informations dans l'intent via les exttra avant de retourner à l'activité principale

                    intent.putExtra("nom", nom_str);
                    intent.putExtra("prenom", prn_str);
                    intent.putExtra("pseudo", psd_str);
                    intent.putExtra("mdp", mdp_str);

                    // Question 8 - Enregistrer dans la base de données SQL Lite

                    DatabaseSQL db = new DatabaseSQL(InscriptionActivity.this);
                    db.ajouterUtilisateur(nom_str,prn_str,psd_str,mdp_str);

                    //Question 9 - Enregistrer le nouvel utilisateur dans la liste reçue en paramètre  avant de le renvoyer à l'activité principale
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setNom(nom_str);
                    utilisateur.setPrenom((prn_str));
                    utilisateur.setPseudo(psd_str);
                    utilisateur.setMdp(mdp_str);

                    if (getIntent().hasExtra("liste_utilisateurs")){
                        utilisateurs = (ArrayList<Utilisateur>) getIntent().getSerializableExtra("liste_utilisateurs");
                    }
                    utilisateurs.add(utilisateur);
                    intent.putExtra("liste_utilisateurs",utilisateurs);

                    // Utilisation des preferences en option pour la question 9
                    Utilisateur.ListeUtilisateurs listeUtilisateurs = new Utilisateur.ListeUtilisateurs();
                    listeUtilisateurs.setUtilisateurs(utilisateurs);
                    PreferencesApplication.getInstance(InscriptionActivity.this).saveObject("liste_utilisateurs", listeUtilisateurs);



                    // Puis on lance l'intent !
                    startActivity(intent);
                }else{
                    //Si l'un des champs est vide, on affiche un Toast à l'utilisateur
                    Toast.makeText(getApplicationContext(), "Veuillez remplir l'intégralité des champs avant de valider", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
