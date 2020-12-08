package com.example.appbanhangonline.common;

import android.content.Context;
import android.widget.Toast;

import com.example.appbanhangonline.model.Watcheds;
import com.example.appbanhangonline.service.GetDataRetrofitWatched;
import com.example.appbanhangonline.service.RetrofitContact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClickWatched {
    public void click(String email, String name_product, int price, int soluong, int rating, String giamgia, String image, Context context){

        Watcheds watcheds= new Watcheds(email,name_product,price,soluong,rating,giamgia,image);
        GetDataRetrofitWatched service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitWatched.class);
        Call<Watcheds> call = service.add(watcheds);
        call.enqueue(new Callback<Watcheds>() {
            @Override
            public void onResponse(Call<Watcheds> call, Response<Watcheds> response) {
                if (response.isSuccessful()){
                    Watcheds user1 = response.body();
                    //mainIntent();
                    Toast.makeText(context,"Thanh cong",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,"Email ton tai",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Watcheds> call, Throwable t) {
                Toast.makeText(context,"That bai",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
