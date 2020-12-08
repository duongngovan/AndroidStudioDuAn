package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.MainActivity_HoaDon_Chi_Tiet;
import com.example.appbanhangonline.model.MyOrders;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolderOrder> {

    private List<MyOrders> list;
    private Context context;

    private Intent intent;

    public MyOrderAdapter(List<MyOrders> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders, parent,false);
        return new ViewHolderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOrder holder, int position) {

        MyOrders orders = list.get(position);
        holder.txt_date.setText(orders.getData());
        holder.txt_price.setText(String.valueOf(orders.getTongTien()));
        if (orders.isTrangThai() == true){
            holder.txt_status.setText("Đang giao hàng");
            holder.coupen_icon.setImageResource(R.drawable.select_green);
        }else {
            holder.txt_status.setText("Đang xử lý");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.click();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderOrder extends RecyclerView.ViewHolder{

        private TextView txt_date;
        private TextView txt_status;
        private TextView txt_price;
        private ImageView coupen_icon;

        public ViewHolderOrder(@NonNull View itemView) {
            super(itemView);
            txt_date = (TextView) itemView.findViewById(R.id.datme);
            txt_price = (TextView) itemView.findViewById(R.id.total_price);
            txt_status = (TextView) itemView.findViewById(R.id.trangthai);
            coupen_icon = itemView.findViewById(R.id.coupen_icon);
        }
        public void click(){
            intent = new Intent(itemView.getContext(), MainActivity_HoaDon_Chi_Tiet.class);
            itemView.getContext().startActivity(intent);
        }
    }
}
