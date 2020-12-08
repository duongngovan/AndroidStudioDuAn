package com.example.appbanhangonline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.model.Wishlist;
import com.example.appbanhangonline.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishListViewHolder> {


    private List<Wishlist> productList;

    public WishlistAdapter(List<Wishlist> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);

        return new WishListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListViewHolder holder, int position) {
        Wishlist list = productList.get(position);
        Picasso.get().load(list.getImageProduct()).into(holder.image);
        holder.title.setText(list.getNameProduct());
        holder.rating.setRating(list.getRatingProduct());
        //holder.txt_giagoc.setText(list.getGiam_gia() + " đ ");
        holder.price.setText(list.getPriceProduct()+ " đ ");
        //holder.txt_giagoc.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
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
