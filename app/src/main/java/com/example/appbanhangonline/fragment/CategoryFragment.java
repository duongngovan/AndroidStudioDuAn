package com.example.appbanhangonline.fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appbanhangonline.adapter.CategoryAdapter;
import com.example.appbanhangonline.adapter.CategorysAdapter;
import com.example.appbanhangonline.model.Category;
import com.example.appbanhangonline.R;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    private CardView search_bar;
    private TextView tvTittle;
    private RecyclerView recyclerView_category,recyclerView;
    private List<Category> list;
    private CategoryAdapter categoryAdapter;
    private CategorysAdapter categorysAdapter;
    private List<Category> lists;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        search_bar = view.findViewById(R.id.search);
        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.searchActivity);

            }
        });

        recyclerView_category = view.findViewById(R.id.recycview_category);
//        recyclerView = view.findViewById(R.id.recyclerview_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
        recyclerView_category .setHasFixedSize(true);
        recyclerView_category .setLayoutManager(new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false));
        list = new ArrayList<>();
        list.add(new Category(R.drawable.thoitrangnam,"Thời trang nam"));
        list.add(new Category(R.drawable.dt,"Phụ kiện & điện thoại"));
        list.add(new Category(R.drawable.maytinh,"Máy tính"));
        list.add(new Category(R.drawable.dongho,"Đồng hồ"));
        list.add(new Category(R.drawable.giay2,"Giày"));
        list.add(new Category(R.drawable.suckhoe,"Sức khỏe"));
        list.add(new Category(R.drawable.tuixach,"Túi xách"));
        list.add(new Category(R.drawable.balo,"Balo"));
        list.add(new Category(R.drawable.book,"Sách"));



        categoryAdapter = new CategoryAdapter(list);
        recyclerView_category.setAdapter(categoryAdapter);
        lists = new ArrayList<>();
//        lists.add(new Category(R.drawable.ao1,"Áo em 1",4,"10000",1));
//        lists.add(new Category(R.drawable.ao1,"Áo em 1",4,"10000",1));
//        lists.add(new Category(R.drawable.ao1,"Áo em 1",4,"10000",1));
//        lists.add(new Category(R.drawable.ao1,"Áo em 1",4,"10000",1));
//        lists.add(new Category(R.drawable.ao1,"Áo em 1",4,"10000",1));
//        lists.add(new Category(R.drawable.ao1,"Áo em 1",4,"10000",1));
//        categorysAdapter = new CategorysAdapter(lists);
//        recyclerView.setAdapter(categorysAdapter);




        return view;
    }
}