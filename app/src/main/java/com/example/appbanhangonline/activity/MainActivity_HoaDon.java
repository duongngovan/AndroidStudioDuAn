package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.MyOrderAdapter;
import com.example.appbanhangonline.model.MyOrders;
import com.example.appbanhangonline.model.Orders;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_HoaDon extends AppCompatActivity {

    private TextView txt_title;
    private ImageView img_back;
    private RecyclerView recyclerView;
    private MyOrderAdapter myOrderAdapter;
    private List<MyOrders> list = new ArrayList<>();

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

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        list.add(new MyOrders("ngovanduong123@gmail.com","20/11/2020",30000,false));
        list.add(new MyOrders("ngovanduong123@gmail.com","08/12/2020",30000,true));
        myOrderAdapter = new MyOrderAdapter(list,getApplicationContext());
        recyclerView.setAdapter(myOrderAdapter);

    }
    private void init(){
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        recyclerView = findViewById(R.id.recycler_hoadon);
    }
}