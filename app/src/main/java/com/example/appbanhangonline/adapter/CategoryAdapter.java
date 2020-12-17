package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.activity.MainActivity_HoaDon_Chi_Tiet;
import com.example.appbanhangonline.activity.MainActivity_NganhHang;
import com.example.appbanhangonline.model.Category;
import com.example.appbanhangonline.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> list;
    private Context context;
    private Intent intent;

    public CategoryAdapter(List<Category> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = list.get(position);
        holder.img_category.setImageResource(category.getImage());
        holder.txt_title.setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.click(category.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private ImageView img_category;
        private TextView txt_title;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img_category = (ImageView) itemView.findViewById(R.id.category_image);
            txt_title = (TextView) itemView.findViewById(R.id.category_title);
        }
        public void click(String idHoDon){

            intent = new Intent(itemView.getContext(), MainActivity_NganhHang.class);
            intent.putExtra("nganh",idHoDon);
            itemView.getContext().startActivity(intent);
        }

    }

}
