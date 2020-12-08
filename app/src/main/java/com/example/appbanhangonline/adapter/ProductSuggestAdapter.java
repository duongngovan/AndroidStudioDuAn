package com.example.appbanhangonline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.model.Product;
import com.example.appbanhangonline.R;

import java.util.ArrayList;

public class ProductSuggestAdapter extends RecyclerView.Adapter<ProductSuggestAdapter.ProductSuggestViewHolder> {
    private ArrayList<Product> productList;

    public ProductSuggestAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductSuggestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_sugget, parent, false);
        return new ProductSuggestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSuggestViewHolder holder, int position) {

        Product product = productList.get(position);

        //holder.image.setImageResource(product.getImage());
        holder.price.setText(String.valueOf(product.getPrice()));
        //holder.giaGoc.setText(product.getPrice());
        //holder.giaGoc.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductSuggestViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView price, giaGoc;
        public ProductSuggestViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.product_image);

            price = itemView.findViewById(R.id.product_price);
        }
    }
}
