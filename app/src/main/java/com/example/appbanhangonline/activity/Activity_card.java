package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.CardAdapter;
import com.example.appbanhangonline.model.CardItemModel;
import com.example.appbanhangonline.model.Cart;
import com.example.appbanhangonline.service.GetDataRetrofitCard;
import com.example.appbanhangonline.service.RetrofitContact;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_card extends AppCompatActivity {

    private RecyclerView recyclerView_card;
    private  List<CardItemModel> list;
    private CardAdapter cardAdapter;
    public static int LIST_CARD;
    private Intent intent;

    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();
    private List<Cart> list_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Giỏ hàng");
        list = new ArrayList<>();
//        sharedPreferences = getSharedPreferences(ProductDetails.MYCARD, Context.MODE_PRIVATE);
//        String product_name = sharedPreferences.getString(ProductDetails.NAME,"");
//        String product_giamgia = sharedPreferences.getString(ProductDetails.GIAM_GIA,"");
//        int product_price = Integer.parseInt(sharedPreferences.getString(ProductDetails.PRICE,""));
//        int product_soluong = Integer.parseInt(sharedPreferences.getString(ProductDetails.AMOUNT,""));
//        String json = sharedPreferences.getString("task_list",null);
//        Type type = new TypeToken<List<CardItemModel>>(){}.getType();


        recyclerView_card = findViewById(R.id.recycview_card);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_card.setLayoutManager(linearLayoutManager);

        intent = this.getIntent();

         String address = intent.getStringExtra("address");
         String name = intent.getStringExtra("name");
         String phone = intent.getStringExtra("phone");
         list_cart = new ArrayList<>();

         ///



        GetDataRetrofitCard serice = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitCard.class);
        Call<List<Cart>> call = serice.getAll();
        Log.d("a2","tau vao roi");
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()){
                    Log.d("a1", "onResponse: "+response.body());
                    list.add(new CardItemModel(0,name, phone,address));
                    list_cart = response.body();
                    for(int i = 0; i < list_cart.size();i++){
//
                        list.add(new CardItemModel(1,list_cart.get(i).getImageProduct(),list_cart.get(i).getNameProduct(),list_cart.get(i).getGiamGia(),list_cart.get(i).getPriceProduct(),list_cart.get(i).getAmountProduct()));
                    }

                    list.add(new CardItemModel(2,1,"199","213","123"));
                    cardAdapter = new CardAdapter(list);
                    recyclerView_card.setAdapter(cardAdapter);
                    cardAdapter.notifyDataSetChanged();
                    Log.d("list", String.valueOf(list_cart.size()));
                }else {
                    Toast.makeText(getApplicationContext(),"faild",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"fall",Toast.LENGTH_SHORT).show();
            }
        });


        ///





//       list.add(new CardItemModel(1,0,R.drawable.ao1,"123",123,"123",123));
//       list.add(new CardItemModel(1,1,R.drawable.ao1,product_name,product_price,product_giamgia,product_soluong));



       // LIST_CARD = list.size() - 2;


    }

}