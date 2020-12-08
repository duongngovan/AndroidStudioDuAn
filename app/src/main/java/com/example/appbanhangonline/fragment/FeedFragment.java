package com.example.appbanhangonline.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.appbanhangonline.adapter.TopProductAdapter;
import com.example.appbanhangonline.model.Product;
import com.example.appbanhangonline.R;

import java.util.ArrayList;

public class FeedFragment extends Fragment {
    private Toolbar toolbar;
    private TopProductAdapter topProductAdapter;
    private ArrayList<Product> productList = new ArrayList<>();
    private RecyclerView rv2;
    private TextView tvTittle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_feed, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        tvTittle = view.findViewById(R.id.title);
        tvTittle.setText("Lượn Lờ Shopping");
        rv2 = view.findViewById(R.id.rv_list_sp);
        topProductAdapter = new TopProductAdapter(productList,getContext());
        rv2.setHasFixedSize(true);
        rv2.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
        rv2.setAdapter(topProductAdapter);

        productList.add(new Product(R.drawable.ao1,"ao thun 1",5,200000,234));
        productList.add(new Product(R.drawable.ao2,"ao thun 2",3.5f,200000,234));
        productList.add(new Product(R.drawable.ao3,"ao thun dai tay gia da",5,200000,234));
        productList.add(new Product(R.drawable.ao4,"ao thun 2",4,200000,234));
        productList.add(new Product(R.drawable.ao1,"ao thun 1",2.5f,200000,234));
        productList.add(new Product(R.drawable.ao2,"ao thun ao thun dai tay gia da2",5,200000,234));
        productList.add(new Product(R.drawable.ao4,"ao thun 1",5,10000,234));
        productList.add(new Product(R.drawable.ao2,"ao thun 2",1,10000,234));
        productList.add(new Product(R.drawable.ao1,"ao thun ao thun dai tay gia da1",5,10000,234));
        productList.add(new Product(R.drawable.ao4,"ao thun 2",5,10000,234));
        productList.add(new Product(R.drawable.ao2,"ao thun dai tay gia da",5,10000,234));

        return view;
    }

   
}