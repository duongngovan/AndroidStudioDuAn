package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.model.Watcheds;
import com.example.appbanhangonline.activity.ProductDetails;
import com.example.appbanhangonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WatchedAdapter extends RecyclerView.Adapter<WatchedAdapter.WatchedViewHolder> {

    private List<Watcheds> productList;
    private Intent intent;

    private Context context;


    public WatchedAdapter(List<Watcheds> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public WatchedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_watched,parent,false);

        WatchedViewHolder viewHolder = new WatchedViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WatchedViewHolder holder, int position) {
        Watcheds list = productList.get(position);
        Picasso.get().load(list.getImageProduct()).into(holder.image);
        holder.title.setText(list.getNameProduct());
        holder.rating.setRating(list.getRatingProduct());
        //holder.txt_giagoc.setText(list.getGiam_gia() + " đ ");
        holder.price.setText(list.getPriceProduct()+ " đ ");
        //holder.txt_giagoc.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        if(Integer.toString(list.getAmount()).length() > 4 ){
//            holder.amount.setText("Đã bán " + Math.round(list.getAmount()/1000)  + "k+ sản phẩm");
//        }
//        else {
//            holder.amount.setText("Đã bán " +list.getAmount() + " sản phẩm");
//        }

        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transtion_animation_sale));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.click(list.getNameProduct(),list.getPriceProduct(),list.getGiamGia(),list.getRatingProduct(),list.getImageProduct());
            }
        });


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class WatchedViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView title,price,txt_giagoc;
        private RatingBar rating;
        public WatchedViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            rating = itemView.findViewById(R.id.ratingBar);
            txt_giagoc = itemView.findViewById(R.id.cutted_price);
        }
        public void click(String name, int price, String giam_gia, float rating,String image){
            intent = new Intent(itemView.getContext(), ProductDetails.class);
            intent.putExtra("product_name",name);
            intent.putExtra("product_price",price);
            intent.putExtra("product_image",image);
            intent.putExtra("product_giam_gia",giam_gia);
            intent.putExtra("product_rating",rating);
            itemView.getContext().startActivity(intent);
        }
    }

}
