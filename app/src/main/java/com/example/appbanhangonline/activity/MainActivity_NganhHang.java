package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.AllProductAdapter;
import com.example.appbanhangonline.model.Testproduct;
import com.example.appbanhangonline.service.GetDataRetrofitProduct;
import com.example.appbanhangonline.service.RetrofitContact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_NganhHang extends AppCompatActivity {

    private ImageView img_back;
    private TextView txt_title;
    private RecyclerView recyclerView;
    private AllProductAdapter allProductAdapter;
    private List<Testproduct> list = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__nganh_hang);
        init();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Ngành hàng");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));

        intent = this.getIntent();
        String nganh = intent.getStringExtra("nganh");
        setAdapter(nganh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setAdapter(nganh);
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }
    private void init(){
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        recyclerView = findViewById(R.id.recycler_nganhhang);
        swipeRefreshLayout = findViewById(R.id.swiperefresh_nganhhang);
    }
    private void setAdapter(String nganhhang){
        GetDataRetrofitProduct serices = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitProduct.class);
        Call<List<Testproduct>> calls = serices.timKiemTheoNganh(nganhhang);
        Log.d("a2","tau vao roi");
        calls.enqueue(new Callback<List<Testproduct>>() {
            @Override
            public void onResponse(Call<List<Testproduct>> call, Response<List<Testproduct>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    Collections.shuffle(list);
                    allProductAdapter = new AllProductAdapter(list,getApplicationContext());
                    recyclerView.setAdapter(allProductAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Testproduct>> call, Throwable t) {

            }
        });
    }
}