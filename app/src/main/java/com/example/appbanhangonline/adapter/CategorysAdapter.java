package com.example.appbanhangonline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.model.Category;
import com.example.appbanhangonline.R;

import java.util.List;

public class CategorysAdapter extends RecyclerView.Adapter<CategorysAdapter.CategorysViewHolder> {

    private List<Category> list;

    public CategorysAdapter(List<Category> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CategorysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categorys, parent, false);
        return new CategorysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorysViewHolder holder, int position) {

        Category category = list.get(position);
        holder.image.setImageResource(category.getImage_view());
        holder.title.setText(category.getTitle());
        holder.rating.setRating(category.getRatting());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategorysViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView title,amount;
        private RatingBar rating;

        public CategorysViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_name);
            amount = itemView.findViewById(R.id.product_price);
            rating = itemView.findViewById(R.id.ratingBar);
        }
    }
}
