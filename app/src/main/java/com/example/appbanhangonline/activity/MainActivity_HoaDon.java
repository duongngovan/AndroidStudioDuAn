package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.DonHangAdapter;
import com.example.appbanhangonline.adapter.MyOrderAdapter;
import com.example.appbanhangonline.login.LoginFragment;
import com.example.appbanhangonline.model.MyOrders;
import com.example.appbanhangonline.model.Orders;
import com.example.appbanhangonline.model.TestDonHang;
import com.example.appbanhangonline.service.GetDataRetrofitCard;
import com.example.appbanhangonline.service.RetrofitContact;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_HoaDon extends AppCompatActivity {

    private TextView txt_title;
    private ImageView img_back;
    private RecyclerView recyclerView;
    private MyOrderAdapter myOrderAdapter;
    private List<MyOrders> list = new ArrayList<>();
    private List<TestDonHang> list_don_hang = new ArrayList<>();
    private DonHangAdapter donHangAdapter;
    private SharedPreferences sharedPreferences;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__hoa_don);
        init();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Hóa đơn");
        swipeRefreshLayout = findViewById(R.id.swiperefresh_hoadon);

        sharedPreferences = getSharedPreferences(LoginFragment.USER, Context.MODE_PRIVATE);
        Log.d("gggg",String.valueOf(sharedPreferences.getString(LoginFragment.ID,"")));
        id = sharedPreferences.getString(LoginFragment.ID,"");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        GetDataRetrofitCard service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitCard.class);
        Call<List<TestDonHang>> call = service.donHangUser(id);

        call.enqueue(new Callback<List<TestDonHang>>() {
            @Override
            public void onResponse(Call<List<TestDonHang>> call, Response<List<TestDonHang>> response) {

                if (response.isSuccessful()){

                    list_don_hang = response.body();
                    donHangAdapter = new DonHangAdapter(list_don_hang,getApplicationContext());
                    recyclerView.setAdapter(donHangAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<TestDonHang>> call, Throwable t) {

            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
    private void init(){
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        recyclerView = findViewById(R.id.recycler_hoadon);
    }
    private void load(){
        GetDataRetrofitCard service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitCard.class);
        Call<List<TestDonHang>> call = service.donHangUser(id);

        call.enqueue(new Callback<List<TestDonHang>>() {
            @Override
            public void onResponse(Call<List<TestDonHang>> call, Response<List<TestDonHang>> response) {

                if (response.isSuccessful()){

                    list_don_hang = response.body();
                    donHangAdapter = new DonHangAdapter(list_don_hang,getApplicationContext());
                    recyclerView.setAdapter(donHangAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<TestDonHang>> call, Throwable t) {

            }
        });
    }
}