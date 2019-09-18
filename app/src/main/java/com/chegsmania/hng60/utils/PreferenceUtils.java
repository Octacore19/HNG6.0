package com.chegsmania.hng60.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class PreferenceUtils {

    private static PreferenceUtils mPreferences;
    private static SharedPreferences preferences;

    private PreferenceUtils(){}

    public static void initSharedPreferences(Context context){
        if (mPreferences == null){
            mPreferences = new PreferenceUtils();
            preferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        }
    }

    public static void registerUser(String email, String password, String firstname, String lastname, String phone){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.putString("First Name", firstname);
        editor.putString("Last Name", lastname);
        editor.putString("Phone Number", phone);
        editor.apply();
    }

    public static List<String> getUser(){
        String email = preferences.getString("Email", " ");
        String password = preferences.getString("Password", " ");
        String firstName = preferences.getString("First Name", " ");
        String lastName = preferences.getString("Last Name", " ");
        String phone = preferences.getString("Phone Number", " ");
        List<String> user = new ArrayList<>();
        user.add(email);
        user.add(password);
        user.add(firstName);
        user.add(lastName);
        user.add(phone);
        return user;
    }
}
