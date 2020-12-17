package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.appbanhangonline.MainActivity;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.login.LoginActivity;

public class MainActivity_ManHinhChao extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__man_hinh_chao);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity_ManHinhChao.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}