package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.Activity_Watched_Adapter;
import com.example.appbanhangonline.adapter.WatchedAdapter;
import com.example.appbanhangonline.adapter.WatchedAdapterss;
import com.example.appbanhangonline.login.LoginFragment;
import com.example.appbanhangonline.model.Watched;
import com.example.appbanhangonline.model.Watcheds;
import com.example.appbanhangonline.service.GetDataRetrofitWatched;
import com.example.appbanhangonline.service.RetrofitContact;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_Watched extends AppCompatActivity {

    private TextView txt_title;
    private ImageView img_back;
    private RecyclerView recycler_watched;

    private List<Watcheds> list_watched = new ArrayList<>();
    private WatchedAdapterss watchedAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__watched);
        init();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Sản phẩm đã xem");

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(LoginFragment.USER, Context.MODE_PRIVATE);
        Log.d("gggg",String.valueOf(sharedPreferences.getString(LoginFragment.ID,"")));
        String id = sharedPreferences.getString(LoginFragment.ID,"");
        recycler_watched.setHasFixedSize(true);
        recycler_watched.setLayoutManager(new LinearLayoutManager(getApplicationContext(),GridLayoutManager.VERTICAL,false));
        GetDataRetrofitWatched service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitWatched.class);
        Call<List<Watcheds>> call = service.getAll(id);
        call.enqueue(new Callback<List<Watcheds>>() {
            @Override
            public void onResponse(Call<List<Watcheds>> call, Response<List<Watcheds>> response) {
                if (response.isSuccessful()){
                    list_watched = response.body();
                    watchedAdapter =  new WatchedAdapterss(list_watched,getApplicationContext());
                    recycler_watched.setAdapter(watchedAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Watcheds>> call, Throwable t) {

            }
        });

    }
    private void init(){
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        recycler_watched = findViewById(R.id.recycler_watched);
    }
}