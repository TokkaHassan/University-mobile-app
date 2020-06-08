package com.example.graduationapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "SocialPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String Name = "nameKey";
    public static final String E_mail = "emailKey";
    public static final String ID = "studentID";
    public static final String tableId ="TableID";
    public static final String Role ="Role";


    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name, String email , String id){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(Name, name);

        // Storing email in pref
        editor.putString(E_mail, email);
        editor.putString(ID,id);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Identification.class);

            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }


    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    /**
     * Get stored session data
     * */

    public String getName(){
        return pref.getString(Name,"");
    }

    public  String getEmail() {
        return pref.getString(E_mail,"");
    }

    public  String getID() {
        return pref.getString(ID,"");
    }
    public  String getTableID() {
        return pref.getString(tableId,"");
    }

    public  String getRole() {
        return pref.getString(Role,"");
    }

    public SharedPreferences getPref() {
        return pref;
    }



    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        editor.putBoolean(IS_LOGIN, false);
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Identification.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        // Add new Flag to start new Activity
       // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
