package com.example.inscriptionconnexion;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

public class PreferencesApplication {
    private static final String PREFERENCES_NAME = "TP_NOTE";
    private SharedPreferences sharedPreferences;
    private static PreferencesApplication preferencesManagerInstance;

    private PreferencesApplication(Context context){
        sharedPreferences=context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
    }

    public static PreferencesApplication getInstance(Context context){
        if(preferencesManagerInstance ==null){
            preferencesManagerInstance = new PreferencesApplication(context);
        }
        return preferencesManagerInstance;
    }

    public void saveObject(String key, Object object){
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("key is empty or null");
        }
        sharedPreferences.edit().putString(key, new Gson().toJson(object)).apply();
    }


    public <T> T retrieveObject(String key, Class<T> tClass) {
        String json = sharedPreferences.getString(key, null);
        if (json == null) {
            return null;
        } else {
            try {
                return new Gson().fromJson(json, tClass);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object stored with key " + key + " is instanceof other class");
            }
        }
    }

}
