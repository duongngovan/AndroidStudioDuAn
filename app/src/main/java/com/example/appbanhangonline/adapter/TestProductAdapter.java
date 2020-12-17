package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.ProductDetails;
import com.example.appbanhangonline.common.ClickWatched;
import com.example.appbanhangonline.model.Testproduct;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TestProductAdapter extends RecyclerView.Adapter<TestProductAdapter.TestProductAdapterViewHolder>{

    private List<Testproduct> list;
    private Context context;
    private Intent intent;

    public TestProductAdapter(List<Testproduct> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TestProductAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flash_sale,parent,false);
        return new TestProductAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestProductAdapterViewHolder holder, int position) {
        Testproduct testproduct = list.get(position);
        holder.txt_name.setText(testproduct.getTenSanPham());
        holder.price.setText(String.valueOf(testproduct.getGia()) + " đ");
        Picasso.get().load(testproduct.getHinhAnh().get(0).getUrl()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.click(testproduct.getHinhAnh().get(0).getUrl(),testproduct.getTenSanPham(),testproduct.getGia(),testproduct.getId(),testproduct.getMoTa(),String.valueOf(testproduct.getSoLuong()));
                new ClickWatched().click(testproduct.getId(),context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TestProductAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView price, giaGoc, txt_name;
        RatingBar ratingBar;

        public TestProductAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.product_name);
            image = itemView.findViewById(R.id.product_image);
            //ratingBar = itemView.findViewById(R.id.ratingBar);
            price = itemView.findViewById(R.id.product_price);
            //giaGoc = itemView.findViewById(R.id.cutted_price);

        }
        public void click(String image,String name,int price,String id,String mota,String soluong){
            intent = new Intent(itemView.getContext(), ProductDetails.class);
            intent.putExtra("product_name",name);
            intent.putExtra("product_price",price);
            intent.putExtra("product_image",image);
            intent.putExtra("id_product",id);
            intent.putExtra("mota",mota);
            intent.putExtra("soluong",soluong);
            itemView.getContext().startActivity(intent);
        }



    }
}
