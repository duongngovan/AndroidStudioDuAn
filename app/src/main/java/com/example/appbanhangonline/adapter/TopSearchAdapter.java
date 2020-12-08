package com.example.appbanhangonline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.model.Product;
import com.example.appbanhangonline.R;

import java.util.ArrayList;

public class TopSearchAdapter extends RecyclerView.Adapter<TopSearchAdapter.TopSearchViewHolder> {
    ArrayList<Product> productList;




    public TopSearchAdapter(ArrayList<Product> productList) {
        this.productList = productList;

    }



    @NonNull
    @Override
    public TopSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_search,parent,false);

        TopSearchViewHolder flashSaleViewHolder = new TopSearchViewHolder(view);
        return flashSaleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopSearchViewHolder holder, int position) {
        Product product = productList.get(position);

        //holder.image.setImageResource(product.getImage());
        holder.title.setText(product.getTitle());
        if(Integer.toString(product.getAmount()).length() > 3 ){
            holder.amount.setText("Có " + Math.round(product.getAmount()/1000)  + "k+ sản phẩm");
        }
        else {
            holder.amount.setText("Có " +product.getAmount() + " sản phẩm");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Ok chưa " + productList.get(position).getAmount(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class TopSearchViewHolder extends RecyclerView.ViewHolder  {
        ImageView image;
        TextView title,amount;
        public TopSearchViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_name);
            amount = itemView.findViewById(R.id.product_amount);

        }


    }

}
