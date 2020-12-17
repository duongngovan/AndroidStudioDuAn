package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.SearchAdapter;
import com.example.appbanhangonline.model.Testproduct;
import com.example.appbanhangonline.service.GetDataRetrofitProduct;
import com.example.appbanhangonline.service.RetrofitContact;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private EditText edSearch;
    private ImageView btnBack;
    private List<Testproduct> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        recyclerView = findViewById(R.id.search_recycler);
        edSearch = (EditText) findViewById(R.id.ed_search);
        btnBack = findViewById(R.id.btnBack);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

        edSearch.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0){

                    list.clear();
                    searchAdapter = new SearchAdapter(list,getApplicationContext());
                    searchAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(searchAdapter);

                }else {
                    timKiem(edSearch.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
    private void timKiem(String find){

        GetDataRetrofitProduct service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitProduct.class);
        Call<List<Testproduct>> call = service.timSanPham(find);
        call.enqueue(new Callback<List<Testproduct>>() {
            @Override
            public void onResponse(Call<List<Testproduct>> call, Response<List<Testproduct>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    searchAdapter = new SearchAdapter(list,getApplicationContext());
                    recyclerView.setAdapter(searchAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Testproduct>> call, Throwable t) {

            }
        });
    }
}