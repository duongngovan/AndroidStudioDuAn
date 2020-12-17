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

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.model.Watcheds;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WatchedAdapterss extends RecyclerView.Adapter<WatchedAdapterss.WishListViewHolder> {

    private List<Watcheds> productList;
    private Intent intent;

    private Context context;

    public WatchedAdapterss(List<Watcheds> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);

        return new WishListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListViewHolder holder, int position) {
        Watcheds list = productList.get(position);
        Picasso.get().load(list.getTestproduct().getHinhAnh().get(0).getUrl()).into(holder.image);
        holder.title.setText(list.getTestproduct().getTenSanPham());
        holder.rating.setRating(3);
        holder.txt_giagoc.setText(String.valueOf(list.getTestproduct().getGia()+1000) + " đ ");
        holder.price.setText(String.valueOf(list.getTestproduct().getGia())+ " đ ");
        holder.txt_giagoc.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        if(Integer.toString(list.getAmount()).length() > 4 ){
//            holder.amount.setText("Đã bán " + Math.round(list.getAmount()/1000)  + "k+ sản phẩm");
//        }
//        else {
//            holder.amount.setText("Đã bán " +list.getAmount() + " sản phẩm");
//        }

        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtion_animation_sale));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.click(list.getNameProduct(),list.getPriceProduct(),list.getGiamGia(),list.getRatingProduct(),list.getImageProduct());
//            }
//        });
        holder.image.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.txt_giagoc.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.price.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.rating.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.image.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class WishListViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView title,price,txt_giagoc;
        private RatingBar rating;
        public WishListViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title);
            price = itemView.findViewById(R.id.product_price);
            rating = itemView.findViewById(R.id.ratingBar);
            txt_giagoc = itemView.findViewById(R.id.cutted_price);
        }
    }
}
