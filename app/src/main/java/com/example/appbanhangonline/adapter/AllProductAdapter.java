package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.model.All;
import com.example.appbanhangonline.activity.ProductDetails;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.common.ClickWatched;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.AllProductViewHolder> {
    private List<All> productList;
    private Intent intent;




    private Context context;

    public AllProductAdapter(List<All> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public AllProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_de_xuat,parent,false);

        AllProductViewHolder flashSaleViewHolder = new AllProductViewHolder(view);
        return flashSaleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductViewHolder holder, int position) {
        All product = productList.get(position);

        Picasso.get().load(product.getUrls()).into(holder.image);

        holder.title.setText(product.getNameProduct());
        holder.price.setText(product.getPriceProduct() + "đ");

        holder.giam_gia.setText(String.valueOf(product.getAmountProduct()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.button_scale_down));
                new ClickWatched().click("ngovanduong123@gmail.com",product.getNameProduct(),product.getPriceProduct(),product.getAmountProduct(),1,"100 d",product.getUrls(),holder.itemView.getContext());
                holder.click(product.getUrls(),product.getNameProduct(),product.getPriceProduct());

            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class AllProductViewHolder extends RecyclerView.ViewHolder  {
        ImageView image;
        TextView title,giam_gia,price;
        public AllProductViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            giam_gia = itemView.findViewById(R.id.cutted_price);

        }
        public void click(String image,String name,int price){
            intent = new Intent(itemView.getContext(), ProductDetails.class);
            intent.putExtra("product_name",name);
            intent.putExtra("product_price",price);
            intent.putExtra("product_image",image);

            itemView.getContext().startActivity(intent);
        }


    }

}
