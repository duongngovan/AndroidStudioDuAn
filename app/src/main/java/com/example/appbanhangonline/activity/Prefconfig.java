package com.example.appbanhangonline.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.appbanhangonline.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Prefconfig {

    public static void writeListInPref(Context context, UserModel userModel){
        Gson gson = new Gson();

        String jsonString = gson.toJson(userModel);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_model",jsonString);
        editor.apply();
    }
    public static UserModel readList(Context context){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString  = sharedPreferences.getString("wish_list","");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<UserModel>>() {}.getType();
        UserModel list = gson.fromJson(jsonString,type);
        return list;
    }
}
