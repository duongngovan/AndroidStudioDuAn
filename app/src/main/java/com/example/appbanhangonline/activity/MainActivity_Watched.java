package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.adapter.Activity_Watched_Adapter;
import com.example.appbanhangonline.model.Watched;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Watched extends AppCompatActivity {

    private TextView txt_title;
    private ImageView img_back;
    private RecyclerView recycler_watched;

    private List<Watched> list_watched = new ArrayList<>();
    private Activity_Watched_Adapter watchedAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__watched);
        init();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Sản phẩm đã xem");
        list_watched.add(new Watched(R.drawable.ao1,"ao thun 1",5,200000,234));
        list_watched.add(new Watched(R.drawable.ao1,"ao thun 1",5,200000,234));
        list_watched.add(new Watched(R.drawable.ao1,"ao thun 1",5,200000,234));
        list_watched.add(new Watched(R.drawable.ao1,"ao thun 1",5,200000,234));
        list_watched.add(new Watched(R.drawable.ao1,"ao thun 1",5,200000,234));
        //
        watchedAdapter = new Activity_Watched_Adapter((ArrayList<Watched>) list_watched,getApplicationContext());
        recycler_watched.setHasFixedSize(true);
        recycler_watched.setLayoutManager(new LinearLayoutManager(getApplicationContext(),GridLayoutManager.VERTICAL,false));
        recycler_watched.setAdapter(watchedAdapter);

    }
    private void init(){
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        recycler_watched = findViewById(R.id.recycler_watched);
    }
}