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
import com.example.appbanhangonline.model.TestDonHang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolderOrder> {


    private List<TestDonHang> list;
    private Context context;

    private Intent intent;

    public DonHangAdapter(List<TestDonHang> list, Context context) {
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
        TestDonHang testDonHang = list.get(position);
        holder.txt_price.setText(String.valueOf(testDonHang.getTongTien()));
        holder.txt_status.setText(testDonHang.getTrangThai());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        try {
            Date date = dateFormat.parse(testDonHang.getNgayMuaHang());
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //If you need time just put specific format for time like 'HH:mm:ss'
            String dateStr = formatter.format(date);
            holder.txt_date.setText(String.valueOf(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }


//


//
//
//
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.click(testDonHang.getIdHoaDon());
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
        public void click(String idHoDon){

            intent = new Intent(itemView.getContext(), MainActivity_HoaDon_Chi_Tiet.class);
            intent.putExtra("id_hoaDon",idHoDon);
            itemView.getContext().startActivity(intent);
        }
    }
}
