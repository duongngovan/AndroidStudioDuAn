package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.DonHangChiTietAdapter;
import com.example.appbanhangonline.model.DonHangChiTiet;
import com.example.appbanhangonline.model.Items;
import com.example.appbanhangonline.service.GetDataRetrofitCard;
import com.example.appbanhangonline.service.GetDataRetrofitDonHang;
import com.example.appbanhangonline.service.RetrofitContact;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_HoaDon_Chi_Tiet extends AppCompatActivity {


    private TextView txt_title;
    private ImageView img_back;
    private RecyclerView recyclerView;
    private TextView ed_ten,ed_diaChi,ed_tongTien,ed_total_item,ed_phone;
    private List<DonHangChiTiet> list = new ArrayList<>();
    private Intent intent;
    private DonHangChiTietAdapter donHangChiTietAdapter;
    private List<Items> itemsList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private String idHoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__hoa_don__chi__tiet);
        init();

        intent = this.getIntent();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Hóa đơn chi tiết");
        swipeRefreshLayout = findViewById(R.id.swiperefresh_hoadon_chitiet);
        idHoaDon = intent.getStringExtra("id_hoaDon");
        Log.d("HoaDon",idHoaDon);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        GetDataRetrofitDonHang services = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitDonHang.class);
        Call<List<Items>> call1 = services.donHangChiTiets(idHoaDon);
        call1.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if (response.isSuccessful()){
                    itemsList = response.body();
                    donHangChiTietAdapter = new DonHangChiTietAdapter(itemsList,getApplicationContext());
                    recyclerView.setAdapter(donHangChiTietAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {

            }
        });
        GetDataRetrofitDonHang service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitDonHang.class);
        Call<List<DonHangChiTiet>> call = service.donHangChiTiet(idHoaDon);
        call.enqueue(new Callback<List<DonHangChiTiet>>() {
            @Override
            public void onResponse(Call<List<DonHangChiTiet>> call, Response<List<DonHangChiTiet>> response) {
                if (response.isSuccessful()){

                    list = response.body();

                   for(int i = 0; i < list.size(); i++){
                       ed_ten.setText(list.get(i).getUserModel().getFullname());
                       ed_diaChi.setText(list.get(i).getUserModel().getDiaChi());
                       ed_phone.setText(list.get(i).getUserModel().getPhone());
                       ed_total_item.setText(String.valueOf(list.get(i).getTongTien())+" đ ");
                       ed_tongTien.setText(String.valueOf(list.get(i).getTongTien())+" đ ");

                   }
                }
            }

            @Override
            public void onFailure(Call<List<DonHangChiTiet>> call, Throwable t) {

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
        recyclerView = findViewById(R.id.recycler_hoadon_chitiet);
        ed_ten = findViewById(R.id.name_khachhangs);
        ed_diaChi = findViewById(R.id.address_khachhang);
        ed_total_item = findViewById(R.id.loicaigi);
        ed_tongTien = findViewById(R.id.total_price);
        ed_phone = findViewById(R.id.phone_khachhang);

    }
    private void load(){
        GetDataRetrofitDonHang services = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitDonHang.class);
        Call<List<Items>> call1 = services.donHangChiTiets(idHoaDon);
        call1.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if (response.isSuccessful()){
                    itemsList = response.body();
                    donHangChiTietAdapter = new DonHangChiTietAdapter(itemsList,getApplicationContext());
                    recyclerView.setAdapter(donHangChiTietAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {

            }
        });
        GetDataRetrofitDonHang service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitDonHang.class);
        Call<List<DonHangChiTiet>> call = service.donHangChiTiet(idHoaDon);
        call.enqueue(new Callback<List<DonHangChiTiet>>() {
            @Override
            public void onResponse(Call<List<DonHangChiTiet>> call, Response<List<DonHangChiTiet>> response) {
                if (response.isSuccessful()){

                    list = response.body();

                    for(int i = 0; i < list.size(); i++){
                        ed_ten.setText(list.get(i).getUserModel().getFullname());
                        ed_diaChi.setText(list.get(i).getUserModel().getDiaChi());
                        ed_phone.setText(list.get(i).getUserModel().getPhone());
                        ed_total_item.setText(String.valueOf(list.get(i).getTongTien())+" đ ");
                        ed_tongTien.setText(String.valueOf(list.get(i).getTongTien())+" đ ");

                    }
                }
            }

            @Override
            public void onFailure(Call<List<DonHangChiTiet>> call, Throwable t) {

            }
        });
    }
}