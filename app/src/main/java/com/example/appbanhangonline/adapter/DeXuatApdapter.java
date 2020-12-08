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

import com.example.appbanhangonline.model.All;
import com.example.appbanhangonline.activity.ProductDetails;
import com.example.appbanhangonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DeXuatApdapter extends RecyclerView.Adapter<DeXuatApdapter.DeXuatViewHolder> {
    private List<All> productList;
    private Intent intent;
    private Context context;

    public DeXuatApdapter(List<All> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public DeXuatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_de_xuat,parent,false);

        return new DeXuatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeXuatViewHolder holder, int position) {
        All product = productList.get(position);

        Picasso.get().load(product.getUrls()).into(holder.image);
        holder.title.setText(product.getNameProduct());
        holder.rating.setRating(1);
        holder.txt_giam_gia.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        if(Integer.toString(product.getAmount()).length() > 4 ){
//            holder.price.setText("Đã bán " + Math.round(product.getAmount()/1000)  + "k+ sản phẩm");
//        }
//        else {
            holder.price.setText(product.getPriceProduct()+ " đ ");
        holder.txt_giam_gia.setText(1000+" đ ");
        holder.image.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.txt_giam_gia.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.price.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.rating.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.image.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.click(product.getNameProduct(),product.getPriceProduct(),1,1,product.getUrls());
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class DeXuatViewHolder extends RecyclerView.ViewHolder  {
        ImageView image;
        TextView title,price,txt_giam_gia;
        RatingBar rating;
        public DeXuatViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_giam_gia = itemView.findViewById(R.id.cutted_price);
            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            rating = itemView.findViewById(R.id.ratingBar);
        }
        public void click(String name, int price, int giam_gia, float rating, String image){
            intent = new Intent(itemView.getContext(), ProductDetails.class);
            intent.putExtra("product_name",name);
            intent.putExtra("product_price",price);
            intent.putExtra("product_giam_gia",giam_gia);
            intent.putExtra("product_rating",rating);
            itemView.getContext().startActivity(intent);
        }


    }
}
