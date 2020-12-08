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
import com.example.appbanhangonline.model.Wishlist;
import com.example.appbanhangonline.service.GetDataRetrofitWishList;
import com.example.appbanhangonline.service.RetrofitContact;
import com.google.gson.Gson;

import java.util.ArrayList;
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

        GetDataRetrofitWishList serice = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitWishList.class);
        Call<List<Wishlist>> call = serice.getAll();
        Log.d("a2","tau vao roi");
        call.enqueue(new Callback<List<Wishlist>>() {
            @Override
            public void onResponse(Call<List<Wishlist>> call, Response<List<Wishlist>> response) {
                if (response.isSuccessful()){
                    Log.d("a1", "onResponse: "+response.body());
                    list_wishlist = response.body();
                    wishlistAdapter = new WishlistAdapter(list_wishlist);
                    ry_wishlist.setAdapter(wishlistAdapter);
                    wishlistAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(),"faild",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Wishlist>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"fall",Toast.LENGTH_SHORT).show();
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