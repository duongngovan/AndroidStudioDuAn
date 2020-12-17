package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.MainActivity_HoaDon_Chi_Tiet;
import com.example.appbanhangonline.model.DonHangChiTiet;
import com.example.appbanhangonline.model.Items;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DonHangChiTietAdapter extends RecyclerView.Adapter<DonHangChiTietAdapter.DonHangViewHolder> {

    private List<Items> list;
    private Context context;

    public DonHangChiTietAdapter(List<Items> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new DonHangViewHolder(cartItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolder holder, int position) {

        Items items = list.get(position);

        String image = items.getTestproduct().getHinhAnh().get(0).getUrl();
        //String productID = cartItemModelList.get(position).getId();
        String names = items.getTestproduct().getTenSanPham();
        String giam_gia = "10%";
        int price_product = items.getTestproduct().getGia();
        int soluong = items.getSoLuongMua();


         holder.setItemDetails(image,names,giam_gia,price_product,soluong);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DonHangViewHolder extends RecyclerView.ViewHolder{

        private ImageView product_image;
        private TextView txt_title;
        private TextView txt_price;
        private TextView txt_amount;
        private TextView txt_cutted_price;
        private ImageView img_delete;
        private ImageView imgPlus,imgMius;
        private TextView txt_soluong;


        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            txt_title = itemView.findViewById(R.id.product_title);
            txt_price = (TextView) itemView.findViewById(R.id.product_price);
            txt_amount = (TextView) itemView.findViewById(R.id.product_amount);
            txt_cutted_price = (TextView) itemView.findViewById(R.id.cutted_price);
            img_delete = (ImageView) itemView.findViewById(R.id.btn_delete);

            txt_soluong = itemView.findViewById(R.id.card_soluong);
            img_delete = itemView.findViewById(R.id.btn_delete);

        }
        private void setItemDetails(String resource ,String title, String cuttedPriceText,int productPriceText,int amount) {

            Picasso.get().load(resource).into(product_image);
            txt_title.setText(title);

            txt_price.setText(productPriceText +" đ ");
            txt_cutted_price.setText(cuttedPriceText );
            txt_cutted_price.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            txt_soluong.setText("Số lượng : "+String.valueOf(amount));

        }
    }
}
