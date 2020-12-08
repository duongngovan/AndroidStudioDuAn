package com.example.appbanhangonline.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appbanhangonline.model.SlideItem;
import com.example.appbanhangonline.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private  List<SlideItem> list;
    private ViewPager2 viewPager;

    public SliderAdapter(List<SlideItem> list, ViewPager2 viewPager) {
        this.list = list;
        this.viewPager = viewPager;
    }



    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpaper,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        SlideItem slideItem = list.get(position);
        holder.imageView.setImageResource(slideItem.getImage());
        if(position == list.size() - 2){
            viewPager.post(runnable);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.banner_slider);
        }

    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            list.addAll(list);
            notifyDataSetChanged();
        }
    };
}
