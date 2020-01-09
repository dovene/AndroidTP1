package com.example.inscriptionconnexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseSQL extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Utilisateur.db";
    public static final String TABLE_NAME = "UTILISATEUR";
    public static final String COL_ID = "id";
    public static final String COL_NOM = "nom";
    public static final String COL_PRENOM = "prenom";
    public static final String COL_PSEUDO = "pseudo";
    public static final String COL_MDP = "mdp";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY," +
                    COL_NOM + " TEXT," +
                    COL_PRENOM + " TEXT," +
                    COL_PSEUDO + " TEXT," +
                    COL_MDP +  " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DatabaseSQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void ajouterUtilisateur(String nom, String prenom, String pseudo, String mpd){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

    // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COL_NOM, nom);
        values.put(COL_PRENOM, prenom);
        values.put(COL_PSEUDO, pseudo);
        values.put(COL_MDP, mpd);


        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public ArrayList<Utilisateur> recuperLesUtilisateurs(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        Utilisateur utilisateur;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                utilisateur = new Utilisateur();
                utilisateur.setNom(cursor.getString(1));
                utilisateur.setPrenom(cursor.getString(2));
                utilisateur.setPseudo(cursor.getString(3));
                utilisateur.setMdp(cursor.getString(4));
                utilisateurs.add(utilisateur);
            }
        }
        cursor.close();
        db.close();
        return utilisateurs;

    }
}