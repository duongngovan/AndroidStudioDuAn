package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.model.Product;
import com.example.appbanhangonline.activity.ProductDetails;
import com.example.appbanhangonline.R;

import java.util.ArrayList;

public class TopProductAdapter extends RecyclerView.Adapter<TopProductAdapter.TopProductViewHolder> {
    private ArrayList<Product> productList;
    private Intent intent;
    private Context context;


    public TopProductAdapter(ArrayList<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public TopProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot,parent,false);

        TopProductViewHolder flashSaleViewHolder = new TopProductViewHolder(view);
        return flashSaleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.image.setImageResource(product.getImage());
        holder.title.setText(product.getTitle());
        holder.rating.setRating(product.getRatting());
        holder.txt_giam_gia.setText(product.getGiam_gia() +" đ ");
        holder.txt_giam_gia.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        if(Integer.toString(product.getAmount()).length() > 4 ){
//            holder.price.setText("Đã bán " + Math.round(product.getAmount()/1000)  + "k+ sản phẩm");
//        }
//        else {
        holder.price.setText(product.getPrice()+" đ ");
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtion_animation_sale));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.click(product.getTitle(),product.getPrice(),product.getGiam_gia(),product.getRatting());
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class TopProductViewHolder extends RecyclerView.ViewHolder  {
        ImageView image;
        TextView title,price,txt_giam_gia;
        RatingBar rating;
        public TopProductViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            rating = itemView.findViewById(R.id.ratingBar);
            txt_giam_gia = itemView.findViewById(R.id.cutted_price);
        }
        public void click(String name, int price, String giam_gia, float rating){
            intent = new Intent(itemView.getContext(), ProductDetails.class);
            intent.putExtra("product_name",name);
            intent.putExtra("product_price",price);
            intent.putExtra("product_giam_gia",giam_gia);
            intent.putExtra("product_rating",rating);
            itemView.getContext().startActivity(intent);
        }


    }


}