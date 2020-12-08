package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.AllProductAdapter;
import com.example.appbanhangonline.model.All;
import com.example.appbanhangonline.service.GetDataRetrofitProduct;
import com.example.appbanhangonline.service.RetrofitContact;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_All_Product extends AppCompatActivity {

    private ImageView img_back;
    private TextView txt_title;
    private RecyclerView recyclerView;
    private AllProductAdapter allProductAdapter;
    private List<All> list = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__all__product);
        init();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Tất cả sản phẩm");
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));



        setAdapter();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setAdapter();
                swipeRefreshLayout.setRefreshing(false);
            }
        });



    }
    private void setAdapter(){
        GetDataRetrofitProduct serices = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitProduct.class);
        Call<List<All>> calls = serices.getAll();
        Log.d("a2","tau vao roi");
        calls.enqueue(new Callback<List<All>>() {
            @Override
            public void onResponse(Call<List<All>> call, Response<List<All>> response) {
                if (response.isSuccessful()){
                    Log.d("a1", "onResponse: "+response.body());
                    list = response.body();
                    allProductAdapter = new AllProductAdapter(list,getApplicationContext());
                    recyclerView.setAdapter(allProductAdapter);

                    Log.d("list", String.valueOf(response.body()));
                }else {
                    Toast.makeText(getApplicationContext(),"faild",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<All>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"fall",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void init(){
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        recyclerView = findViewById(R.id.recycler_all);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
    }
}