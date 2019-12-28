package com.restaurant.arrifqiaziz.data;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import com.restaurant.arrifqiaziz.activity.auth.LoginActivity;
import com.restaurant.arrifqiaziz.model.login.User;


/**
 * Created by Comp on 7/29/2017.
 */

public class Session {
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "pjr-trans";

    private static final String IS_LOGIN = "IsLoged";

    public static final String KEY_USER = "user";

    public Session(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);

        editor.putString(KEY_USER, json);
        editor.putBoolean(IS_LOGIN, true);

        editor.commit();
    }

    public User getUser() {
        Gson gson = new Gson();
        String json = pref.getString(KEY_USER, "");
        User obj = gson.fromJson(json, User.class);
        return obj;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        editor.putBoolean(IS_LOGIN, false);

        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
