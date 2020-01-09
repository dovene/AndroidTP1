package com.example.inscriptionconnexion;

import java.io.Serializable;
import java.util.ArrayList;

public class Utilisateur implements Serializable {

    private String nom;
    private String prenom;
    private String pseudo;
    private String mdp;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public static class ListeUtilisateurs{
        private ArrayList<Utilisateur> utilisateurs;

        public ArrayList<Utilisateur> getUtilisateurs() {
            return utilisateurs;
        }

        public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
            this.utilisateurs = utilisateurs;
        }
    }
}
