package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.CardAdapter;
import com.example.appbanhangonline.login.LoginFragment;
import com.example.appbanhangonline.model.CardItemModel;
import com.example.appbanhangonline.model.Cart;
import com.example.appbanhangonline.model.Items;
import com.example.appbanhangonline.model.TestCard;
import com.example.appbanhangonline.model.TestCardModel;
import com.example.appbanhangonline.model.Testproduct;
import com.example.appbanhangonline.service.GetDataRetrofitCard;
import com.example.appbanhangonline.service.RetrofitContact;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

    private Button btn_mua;
    private Intent intents;

    private List<Items> list_product;

    private TextView txt_title;
    private ImageView img_back;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String id;
    private String address,name,phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Giỏ Hàng");
        swipeRefreshLayout = findViewById(R.id.swiperefresh_giohang);
        list = new ArrayList<>();
        btn_mua = findViewById(R.id.buy_now_btn);
        intents = new Intent(getApplicationContext(),MainActivity_HoaDon.class);
        sharedPreferences = getSharedPreferences(LoginFragment.USER, Context.MODE_PRIVATE);
        Log.d("gggg",String.valueOf(sharedPreferences.getString(LoginFragment.ID,"")));
        id = sharedPreferences.getString(LoginFragment.ID,"");
        btn_mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataRetrofitCard service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitCard.class);
                Call<String> call = service.thanhthoan(id);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            list.clear();
                            cardAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        //Toast.makeText(getApplicationContext(),"That Bai",Toast.LENGTH_LONG).show();
                    }
                });
                startActivity(intents);
            }
        });
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

        recyclerView_card.setHasFixedSize(true);

        intent = this.getIntent();

         address = intent.getStringExtra("address");
         name = intent.getStringExtra("name");
         phone = intent.getStringExtra("phone");
         list_cart = new ArrayList<>();
         list_product = new ArrayList<>();

         ///


        GetDataRetrofitCard service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitCard.class);
        Call<List<Items>> call = service.hienThi(id.trim());
        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if (response.isSuccessful()){
                    list.add(new CardItemModel(0,name, phone,address));
                    list_product = response.body();
                    Log.d("gggg", String.valueOf(list_product.size()));
                    for (int i = 0; i < list_product.size();i++){
                        list.add(new CardItemModel(1,id,list_product.get(i).getTestproduct().getId(),list_product.get(i).getTestproduct().getHinhAnh().get(0).getUrl(),list_product.get(i).getTestproduct().getTenSanPham(),"10%",list_product.get(i).getTestproduct().getGia(),list_product.get(i).getSoLuongMua()));
                    }

                    list.add(new CardItemModel(2,0,"199","213","123"));
                    cardAdapter = new CardAdapter(list,getApplicationContext());
                    recyclerView_card.setAdapter(cardAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                list_product.clear();
                cardAdapter.notifyDataSetChanged();
                load();
                swipeRefreshLayout.setRefreshing(false);
            }
        });




//        GetDataRetrofitCard serice = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitCard.class);
//        Call<List<Cart>> call = serice.getAll();
//        Log.d("a2","tau vao roi");
//        call.enqueue(new Callback<List<Cart>>() {
//            @Override
//            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
//                if (response.isSuccessful()){
//                    Log.d("a1", "onResponse: "+response.body());
//                    list.add(new CardItemModel(0,name, phone,address));
//                    list_cart = response.body();
//                    for(int i = 0; i < list_cart.size();i++){
////
//                        list.add(new CardItemModel(1,list_cart.get(i).getImageProduct(),list_cart.get(i).getNameProduct(),list_cart.get(i).getGiamGia(),list_cart.get(i).getPriceProduct(),list_cart.get(i).getAmountProduct()));
//                    }
//
//                    list.add(new CardItemModel(2,1,"199","213","123"));
//                    cardAdapter = new CardAdapter(list);
//                    recyclerView_card.setAdapter(cardAdapter);
//                    cardAdapter.notifyDataSetChanged();
//                    Log.d("list", String.valueOf(list_cart.size()));
//                }else {
//                    Toast.makeText(getApplicationContext(),"faild",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Cart>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"fall",Toast.LENGTH_SHORT).show();
//            }
//        });


        ///





//       list.add(new CardItemModel(1,0,R.drawable.ao1,"123",123,"123",123));
//       list.add(new CardItemModel(1,1,R.drawable.ao1,product_name,product_price,product_giamgia,product_soluong));






    }
    private void load(){
        GetDataRetrofitCard service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitCard.class);
        Call<List<Items>> call = service.hienThi(id.trim());
        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if (response.isSuccessful()){
                    list.add(new CardItemModel(0,name, phone,address));
                    list_product = response.body();
                    Log.d("gggg", String.valueOf(list_product.size()));
                    for (int i = 0; i < list_product.size();i++){
                        list.add(new CardItemModel(1,id,list_product.get(i).getTestproduct().getId(),list_product.get(i).getTestproduct().getHinhAnh().get(0).getUrl(),list_product.get(i).getTestproduct().getTenSanPham(),"10%",list_product.get(i).getTestproduct().getGia(),list_product.get(i).getSoLuongMua()));
                    }

                    list.add(new CardItemModel(2,0,"199","213","123"));
                    cardAdapter = new CardAdapter(list,getApplicationContext());
                    recyclerView_card.setAdapter(cardAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"That bai",Toast.LENGTH_SHORT).show();
            }
        });
    }

}