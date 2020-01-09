package com.example.inscriptionconnexion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ConnexionActivity extends AppCompatActivity {
    private EditText psd2;
    private EditText mdp2;
    private Button valider;

    private String str_nom = "";
    private String str_prenom = "";
    private String str_psd = "";
    private String str_mdp = "";

    private ArrayList<Utilisateur> utilisateurs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);


        psd2 = findViewById(R.id.psd2);
        mdp2 = findViewById(R.id.mdp2);
        valider = findViewById(R.id.valider);



        //On instancie les éléments de l'interface
        valider = (Button) findViewById(R.id.valider);
        psd2 = (EditText) findViewById(R.id.psd2);
        mdp2 = (EditText) findViewById(R.id.mdp2);


        // Recuperer les valeurs passés en paramètres
        recupererInformations();



//Au clique sur le bouton
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnexionActivity.this, BonjourActivity.class);// On créé un Intent permettant de changer d'activité. On passe de MainActivity à BeginGame
                //On recupere les champs remplis par l'utilisateur
                String psd_str = psd2.getText().toString();
                String mdp_str = mdp2.getText().toString();
                //Si l'un des champs est vide, on affiche un Toast à l'utilisateur
                if(psd_str.equals("") || mdp_str.equals("")){
                    Toast.makeText(getApplicationContext(), "Veuillez remplir l'intégralité des champs avant de valider", Toast.LENGTH_LONG).show();
                    return;
                }

                //On verifie si les valeurs des champs sont équivalentes aux informations récupérés dans l'intent
                if(psd_str.equals(str_psd) && mdp_str.equals(str_mdp)){

                    // Mettre les informations récupéres depuis l'extra dans l'intent avant de lancer l'activité bonjour
                    intent.putExtra("nom", str_nom);
                    intent.putExtra("prenom", str_prenom);
                    intent.putExtra("pseudo", str_psd);
                    intent.putExtra("mdp", str_mdp);

                    // Puis on lance l'intent !
                    startActivity(intent);
                } else if (estDansLaBase()!=null)
                // Question 8 - Gestion de la base de données pour récupérer l'utilisateur correspondant
                {
                    // Mettre les informations récupéres depuis la base de données dans l'intent avant de lancer l'activité bonjour
                    intent.putExtra("nom", estDansLaBase().getNom());
                    intent.putExtra("prenom", estDansLaBase().getPrenom());
                    intent.putExtra("pseudo", estDansLaBase().getPseudo());
                    intent.putExtra("mdp", estDansLaBase().getMdp());

                    // Puis on lance l'intent !
                    startActivity(intent);

                }  else if (estDansLaListeDesUtilisateurs()!=null)
                    // Question 9 - Utilisation d'une liste passé en extra pour récupérer l'utilisateur correspondant ou bien depuis les preferences
                {
                    // Mettre les informations récupéres depuis la base de données dans l'intent avant de lancer l'activité bonjour
                    intent.putExtra("nom", estDansLaListeDesUtilisateurs().getNom());
                    intent.putExtra("prenom", estDansLaListeDesUtilisateurs().getPrenom());
                    intent.putExtra("pseudo", estDansLaListeDesUtilisateurs().getPseudo());
                    intent.putExtra("mdp", estDansLaListeDesUtilisateurs().getMdp());

                    // Puis on lance l'intent !
                    startActivity(intent);

                }else{
                    //Si l'un des champs est vide, on affiche un Toast à l'utilisateur
                    Toast.makeText(getApplicationContext(), "Mauvaises informations ! Changez les informations pour vous connecter", Toast.LENGTH_LONG).show();
                }
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
        if (intent.hasExtra("mdp")) {
            str_mdp = intent.getStringExtra("mdp"); // on récupère la valeur associée à la clé

        }
        // Question 9 - gestion de la liste des utilisateurs inscrits
        if (intent.hasExtra("liste_utilisateurs")){
            utilisateurs = (ArrayList<Utilisateur>) intent.getSerializableExtra("liste_utilisateurs");
        }
    }

    private Utilisateur estDansLaBase(){

        Utilisateur resultat = null;
        DatabaseSQL db = new DatabaseSQL(this);
        ArrayList<Utilisateur> utilisateurs = db.recuperLesUtilisateurs();

        for (Utilisateur utilisateur: utilisateurs){
            if (utilisateur.getMdp().equals(mdp2.getText().toString()) &&  utilisateur.getPseudo().equals(psd2.getText().toString()) ){
                resultat = utilisateur;
            }
        }

        return resultat;

    }

    private Utilisateur estDansLaListeDesUtilisateurs(){

        Utilisateur resultat = null;
        ArrayList<Utilisateur> utilisateurArrayList = new ArrayList<>();
        Utilisateur.ListeUtilisateurs listeUtilisateurs =  PreferencesApplication.getInstance(this).retrieveObject("liste_utilisateurs", Utilisateur.ListeUtilisateurs.class);

        // Si la liste des utilisateurs exitse dans les preferences on la récupère sinon on utilise la liste passée dans l'extra
        if (listeUtilisateurs!=null){
            utilisateurArrayList = listeUtilisateurs.getUtilisateurs();
        } else {
            utilisateurArrayList = this.utilisateurs;
        }

        for (Utilisateur utilisateur: utilisateurArrayList){
            if (utilisateur.getMdp().equals(mdp2.getText().toString()) &&  utilisateur.getPseudo().equals(psd2.getText().toString()) ){
                resultat = utilisateur;
            }
        }

        return resultat;

    }

}
