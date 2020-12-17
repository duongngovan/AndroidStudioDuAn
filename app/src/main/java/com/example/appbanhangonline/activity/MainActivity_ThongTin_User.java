package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.login.LoginFragment;
import com.example.appbanhangonline.model.UserModel;
import com.example.appbanhangonline.service.GetDataRetrofitUser;
import com.example.appbanhangonline.service.RetrofitContact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_ThongTin_User extends AppCompatActivity {

    private TextView txt_title;
    private ImageView img_back;
    private Button btn_capnhat;
    private EditText ed_name,ed_sodienthoi,ed_diachi,ed_email;
    private String id,email,address,phone,name;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__thong_tin__user);
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Thông tin cá nhân");
        btn_capnhat = findViewById(R.id.thongtin_capnhat);
        ed_diachi = findViewById(R.id.thongtin_diachi);
        ed_email = findViewById(R.id.thongtin_email);
        ed_name = findViewById(R.id.thongtin_ten);
        ed_sodienthoi = findViewById(R.id.thongtin_sodienthoai);


        sharedPreferences = getSharedPreferences(LoginFragment.USER, Context.MODE_PRIVATE);
        Log.d("gggg",String.valueOf(sharedPreferences.getString(LoginFragment.ID,"")));
        id = sharedPreferences.getString(LoginFragment.ID,"");
        email = sharedPreferences.getString(LoginFragment.EMAIL,"");
        name = sharedPreferences.getString(LoginFragment.FULLNAME,"");
        address = sharedPreferences.getString(LoginFragment.ADDRESS,"");
        phone = sharedPreferences.getString("dienthoai","");

        ed_sodienthoi.setText(phone);
        ed_name.setText(name);
        ed_email.setText(email);
        ed_diachi.setText(address);

        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataRetrofitUser service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitUser.class);
                Call<UserModel> call = service.capNhat(id,ed_name.getText().toString(),ed_sodienthoi.getText().toString(),ed_diachi.getText().toString());
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {

                    }
                });
            }
        });

    }
}