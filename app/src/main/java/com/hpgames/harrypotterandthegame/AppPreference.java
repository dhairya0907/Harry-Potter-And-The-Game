package com.hpgames.harrypotterandthegame;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private SharedPreferences mPreferences;
    private String PREF_NAME = "MyGroupChat";
    private String KEY_Gameename = "gamename";
    private SharedPreferences.Editor editor;
    public static String ugamename;

    public AppPreference(Context mContext){
        mPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = mPreferences.edit();
    }

    public void setGamename(String gamename){
        editor.putString(KEY_Gameename, gamename);
        editor.commit();
    }

    public String getGamename(){

        return  mPreferences.getString(KEY_Gameename, ugamename);
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }

}
