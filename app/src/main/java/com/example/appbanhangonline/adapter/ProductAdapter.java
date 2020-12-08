package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.content.Intent;
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

import com.example.appbanhangonline.model.All;
import com.example.appbanhangonline.activity.ProductDetails;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.common.ClickWatched;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Animation scaleUp, scaleDown;
    private List<All> list;
    private Context context;
    private Intent intent ;

    public ProductAdapter(List<All> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flash_sale,parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        All products = list.get(position);
        scaleUp = AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.button_scale_up);

        scaleDown = AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.button_scale_down);
        //
        Picasso.get().load(products.getUrls()).into(holder.image);
        //holder.ratingBar.setRating(products.getRatting());
        holder.txt_name.setText(products.getNameProduct());
        holder.price.setText(products.getPriceProduct() + " đ ");
        //holder.giaGoc.setText(products.getGiam_gia() + "đ");
        //holder.giaGoc.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.button_scale_down));
                new ClickWatched().click("ngovanduong123@gmail.com",products.getNameProduct(),products.getPriceProduct(),products.getAmountProduct(),1,"100 d",products.getUrls(),holder.itemView.getContext());
                holder.click(products.getUrls(),products.getNameProduct(),products.getPriceProduct());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView price, giaGoc, txt_name;
        RatingBar ratingBar;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.product_name);
            image = itemView.findViewById(R.id.product_image);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            price = itemView.findViewById(R.id.product_price);
            giaGoc = itemView.findViewById(R.id.cutted_price);

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
