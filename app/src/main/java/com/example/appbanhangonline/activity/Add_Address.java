package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.Activity_card;

public class Add_Address extends AppCompatActivity {


    private EditText txt_phuong,txt_quan,txt_tinh,txt_name,txt_sđt;
    private ImageView img_back;
    private TextView txt_title;
    private Intent intent;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__address);
        init();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Thêm địa chỉ");







        intent = new Intent(this, Activity_card.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String duong = txt_phuong.getText().toString();
                String quan = txt_quan.getText().toString();
                String tinh = txt_tinh.getText().toString();
                String address = duong + ", "+quan + ", "+tinh;
                String name = txt_name.getText().toString();
                String phone = txt_sđt.getText().toString();
                intent.putExtra("address",address);
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                Log.d("diachi",address);
                startActivity(intent);

            }
        });




    }
    private void init(){
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        txt_phuong = findViewById(R.id.ed_duong);
        txt_quan = findViewById(R.id.ed_quan);
        txt_tinh = findViewById(R.id.ed_tinh);
        txt_name = findViewById(R.id.ed_name);
        txt_sđt = findViewById(R.id.ed_phone);
        btn = findViewById(R.id.save_btn);
    }
}