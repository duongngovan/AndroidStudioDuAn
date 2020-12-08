package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.model.Product;
import com.example.appbanhangonline.activity.ProductDetails;
import com.example.appbanhangonline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FlashSaleAdapter extends RecyclerView.Adapter<FlashSaleAdapter.FlashSaleViewHolder> {
    private ArrayList<Product> productList;
    private Intent intent ;
    private Animation scaleUp, scaleDown;

    private Context context;


    public FlashSaleAdapter(ArrayList<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public FlashSaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flash_sale,parent,false);

        FlashSaleViewHolder flashSaleViewHolder = new FlashSaleViewHolder(view);
        return flashSaleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FlashSaleViewHolder holder, int position) {

        Product product = productList.get(position);
        scaleUp = AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.button_scale_up);

        scaleDown = AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.button_scale_down);
        //

        //holder.image.setImageResource(product.getImage());
        Picasso.get().load(product.getImage()).into(holder.image);
        holder.ratingBar.setRating(product.getRatting());
        holder.txt_name.setText(product.getTitle());
        holder.price.setText(product.getPrice() + " đ ");
        holder.giaGoc.setText(product.getGiam_gia() + "đ");
        holder.giaGoc.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtion_animation_sale));


//        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    holder.itemView.startAnimation( scaleUp);
//                }else   if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    holder.itemView.startAnimation( scaleDown);
//                }
//                return true;
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.button_scale_down));
                holder.click(product.getTitle(),product.getPrice(),product.getGiam_gia(),product.getRatting());
            }
        });




    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class FlashSaleViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView price, giaGoc, txt_name;
        RatingBar ratingBar;

        public FlashSaleViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.product_name);
            image = itemView.findViewById(R.id.product_image);
           ratingBar = itemView.findViewById(R.id.ratingBar);
            price = itemView.findViewById(R.id.product_price);
            giaGoc = itemView.findViewById(R.id.cutted_price);

        }
        public void click(String name, int price, String giam_gia, float rating){
            intent = new Intent(itemView.getContext(),ProductDetails.class);
            intent.putExtra("product_name",name);
            intent.putExtra("product_price",price);
            intent.putExtra("product_giam_gia",giam_gia);
            intent.putExtra("product_rating",rating);
            itemView.getContext().startActivity(intent);
        }

    }
}
