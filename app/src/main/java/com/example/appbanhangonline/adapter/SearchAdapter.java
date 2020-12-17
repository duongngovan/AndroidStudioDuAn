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
import com.example.appbanhangonline.activity.ProductDetails;
import com.example.appbanhangonline.model.Testproduct;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<Testproduct> list;
    private Context context;


    public SearchAdapter(List<Testproduct> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_de_xuat,parent,false);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        Testproduct testproduct = list.get(position);
        Picasso.get().load(testproduct.getHinhAnh().get(0).getUrl()).into(holder.image);
        holder.title.setText(testproduct.getTenSanPham());
        //holder.rating.setRating(1);
        holder.txt_giam_gia.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.price.setText(String.valueOf(testproduct.getGia())+ " đ ");
        holder.txt_giam_gia.setText(String.valueOf(testproduct.getGia()+1000)+" đ ");
        holder.image.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.txt_giam_gia.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.price.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.rating.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.image.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder  {
        ImageView image;
        TextView title,price,txt_giam_gia;
        RatingBar rating;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_giam_gia = itemView.findViewById(R.id.cutted_price);
            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            rating = itemView.findViewById(R.id.ratingBar);
        }
//        public void click(String name, int price, int giam_gia, float rating, String image){
//            intent = new Intent(itemView.getContext(), ProductDetails.class);
//            intent.putExtra("product_name",name);
//            intent.putExtra("product_price",price);
//            intent.putExtra("product_giam_gia",giam_gia);
//            intent.putExtra("product_rating",rating);
//            itemView.getContext().startActivity(intent);
//        }


    }
}
