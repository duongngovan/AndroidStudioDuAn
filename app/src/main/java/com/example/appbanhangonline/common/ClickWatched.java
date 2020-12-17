package com.example.appbanhangonline.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.appbanhangonline.login.LoginFragment;
import com.example.appbanhangonline.model.Watcheds;
import com.example.appbanhangonline.service.GetDataRetrofitWatched;
import com.example.appbanhangonline.service.RetrofitContact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClickWatched {

    public void click(String idSanPham, Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences(LoginFragment.USER, Context.MODE_PRIVATE);
        Log.d("gggg",String.valueOf(sharedPreferences.getString(LoginFragment.ID,"")));
        String id = sharedPreferences.getString(LoginFragment.ID,"");
        GetDataRetrofitWatched service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitWatched.class );
        Call<Watcheds> call = service.add(id,idSanPham);
        call.enqueue(new Callback<Watcheds>() {
            @Override
            public void onResponse(Call<Watcheds> call, Response<Watcheds> response) {

            }

            @Override
            public void onFailure(Call<Watcheds> call, Throwable t) {

            }
        });

    }
}
