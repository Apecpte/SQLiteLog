package com.androidag.sqlitelogin.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
//Complete
public class PrefUtil {

    // Put data or store Data into sharedPreference
    public static void putbooleanPref(String key, boolean value, Context context){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    // clear Data From sharedPreference
    public static void clearBoolean(String key, boolean value, Context context){
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        editor.apply();
    }
    // get Data From sharedPreference
    public static boolean getbooleanPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, false);
    }

}
