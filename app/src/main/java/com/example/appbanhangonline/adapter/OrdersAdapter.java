package com.example.appbanhangonline.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;

public class OrdersAdapter extends RecyclerView.Adapter {




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class OrdersItem extends RecyclerView.ViewHolder{

        private TextView txt_name_product;
        private TextView txt_price_product;
        private TextView txt_giam_gia;
        private TextView txt_so_luong;

        public OrdersItem(@NonNull View itemView) {
            super(itemView);
            txt_name_product = itemView.findViewById(R.id.product_title);
            txt_price_product = itemView.findViewById(R.id.product_price);
            txt_giam_gia = itemView.findViewById(R.id.cutted_price);
            txt_so_luong = itemView.findViewById(R.id.card_soluong);

        }
    }



    public class OrdersAddress extends RecyclerView.ViewHolder{
        private TextView txt_name;
        private TextView txt_address;
        private TextView txt_phone;
        public OrdersAddress(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.name_khachhangs);
            txt_address = itemView.findViewById(R.id.address_khachhang);
            txt_phone = itemView.findViewById(R.id.phone_khachhang);
        }
    }


}
