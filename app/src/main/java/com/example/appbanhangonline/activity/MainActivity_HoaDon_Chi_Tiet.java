package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhangonline.R;

public class MainActivity_HoaDon_Chi_Tiet extends AppCompatActivity {


    private TextView txt_title;
    private ImageView img_back;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__hoa_don__chi__tiet);
        init();
    }
    private void init(){
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        recyclerView = findViewById(R.id.recycler_hoadon);
    }
}