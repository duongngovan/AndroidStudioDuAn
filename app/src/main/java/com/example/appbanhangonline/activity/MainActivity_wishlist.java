package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.WishlistAdapter;
import com.example.appbanhangonline.login.LoginFragment;
import com.example.appbanhangonline.model.Wishlist;
import com.example.appbanhangonline.service.GetDataRetrofitWishList;
import com.example.appbanhangonline.service.RetrofitContact;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_wishlist extends AppCompatActivity {

    private TextView txt_title;
    private ImageView img_back;
    private RecyclerView ry_wishlist;
    private WishlistAdapter wishlistAdapter;
    private List<Wishlist> list_wishlist = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wishlist);
        init();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Yêu Thích");
        sharedPreferences = getSharedPreferences("wish", Context.MODE_PRIVATE);

        ry_wishlist.setHasFixedSize(true);
        ry_wishlist.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

//        String json = sharedPreferences.getString("wish_list",null);
//        Type type = new TypeToken<List<Watched>>(){}.getType();
//        list_watched = Prefconfig.readList(getApplicationContext());

        sharedPreferences = getSharedPreferences(LoginFragment.USER, Context.MODE_PRIVATE);
        Log.d("gggg",String.valueOf(sharedPreferences.getString(LoginFragment.ID,"")));
        String id = sharedPreferences.getString(LoginFragment.ID,"");
        GetDataRetrofitWishList service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitWishList.class);
        Call<List<Wishlist>> call = service.getAll(id);
        call.enqueue(new Callback<List<Wishlist>>() {
            @Override
            public void onResponse(Call<List<Wishlist>> call, Response<List<Wishlist>> response) {
                if (response.isSuccessful()){
                    list_wishlist = response.body();
                    wishlistAdapter = new WishlistAdapter(list_wishlist);
                    ry_wishlist.setAdapter(wishlistAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Wishlist>> call, Throwable t) {

            }
        });



        //


    }
    private void init(){
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        ry_wishlist = findViewById(R.id.recycler_wishlist);
    }
}