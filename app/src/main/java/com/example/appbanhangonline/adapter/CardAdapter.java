package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.activity.Add_Address;
import com.example.appbanhangonline.model.CardItemModel;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.service.GetDataRetrofitCard;
import com.example.appbanhangonline.service.RetrofitContact;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardAdapter extends RecyclerView.Adapter  {

    private List<CardItemModel> cartItemModelList;
    private  int a ;
    private int b;
    private Context context;

    private int lastPosition = -1;
    private SharedPreferences sharedPreferences;


    public CardAdapter(List<CardItemModel> cartItemModelList, Context context) {
        this.cartItemModelList = cartItemModelList;
        this.context = context;
    }

    public CardAdapter(List<CardItemModel> list) {
        this.cartItemModelList = list;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()) {
            case 0:
                return CardItemModel.ADDRESS;
            case 1:
                return CardItemModel.CART_ITEM;
            case 2:
                return CardItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case CardItemModel.ADDRESS:
                View cardAddressView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
                return new CardAddressViewHolder(cardAddressView);
            case CardItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
                return new CardItemViewHolder(cartItemView);
            case CardItemModel.TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout, parent, false);
                return new CartTotalAmountViewHolder(cartTotalView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



        switch (cartItemModelList.get(position).getType()) {

            case CardItemModel.ADDRESS:

                String name = cartItemModelList.get(position).getName();
                String phone = cartItemModelList.get(position).getPhone_number();
                String address = cartItemModelList.get(position).getAddress();
                ((CardAddressViewHolder) holder).setAddress(name,phone,address);

                break;

            case CardItemModel.CART_ITEM:
                String image = cartItemModelList.get(position).getImage_product();
                //String productID = cartItemModelList.get(position).getId();
                String names = cartItemModelList.get(position).getName_product();
                String giam_gia = "10%";
                int price_product = cartItemModelList.get(position).getPrice_product();
                int soluong = cartItemModelList.get(position).getSo_luong();


                ((CardItemViewHolder) holder).setItemDetails(image,names,giam_gia,price_product,soluong);






                ((CardItemViewHolder) holder).img_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("vua","vuabam");

                        GetDataRetrofitCard service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitCard.class);

                        Call<List<String>> call = service.delte(cartItemModelList.get(position).getId_user(),cartItemModelList.get(position).getId_item());
                        Log.d("id",String.valueOf(cartItemModelList.get(position).getId_user()));
                        Log.d("idSanPham",String.valueOf(cartItemModelList.get(position).getId_item()));
                        call.enqueue(new Callback<List<String>>() {
                           @Override
                           public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                              if (response.isSuccessful()){
                                  Toast.makeText(context,"Thanh cong",Toast.LENGTH_SHORT).show();
                              }

                           }

                           @Override
                           public void onFailure(Call<List<String>> call, Throwable t) {
                               Toast.makeText(context,"That bai",Toast.LENGTH_SHORT).show();
                           }
                       });
                        cartItemModelList.remove(cartItemModelList.get(holder.getAdapterPosition()));
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyDataSetChanged();


                    }
                });
                break;
            case CardItemModel.TOTAL_AMOUNT:
//                int totalItems = 0;
//                int totalItemPrice = 0;
//                String deliveryPrice;
//                int totalAmount;
//                int savedAmount = 0;
//
//                for (int x = 0; x < cartItemModelList.size(); x++) {
//                    if (cartItemModelList.get(x).getType() == CardItemModel.CART_ITEM) {
//                        totalItems++;
//                        totalItemPrice = totalItemPrice + Integer.parseInt(cartItemModelList.get(x).getProductPrice());
//                    }
//                }
//                if (totalItemPrice>500){
//                    deliveryPrice = "Free";
//                    totalAmount = totalItemPrice;
//                }else {
//                    deliveryPrice = "60";
//                    totalAmount = totalItemPrice + 60;
//                }
                int soLuong = a;
                int totalItems = cartItemModelList.size() - 1;
                int tong = 0;
                for(int i = 0; i < cartItemModelList.size(); i++){
                   tong += (cartItemModelList.get(i).getPrice_product() * cartItemModelList.get(i).getSo_luong() );
                }
                String totalItemPrice = cartItemModelList.get(position).getTotalItemPrice();

                String totalAmount = cartItemModelList.get(position).getTotalAmount();
                String savedAmount = cartItemModelList.get(position).getSavedAmount();

                ((CartTotalAmountViewHolder) holder).setTotalAmount(totalItems, (tong), String.valueOf(tong), savedAmount);
                break;
            default:
                return;
        }
        if (lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
            holder.itemView.setAnimation(animation);
            lastPosition = position;
        }

    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    public class CardItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView product_image;
        private TextView txt_title;
        private TextView txt_price;
        private TextView txt_amount;
        private TextView txt_cutted_price;
        private ImageView img_delete;
        private ImageView imgPlus,imgMius;
        private TextView txt_soluong;


        public CardItemViewHolder(@NonNull View itemView) {
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
    public class CartTotalAmountViewHolder extends RecyclerView.ViewHolder{

        private TextView totalItems;
        private TextView totalItemsPrice;
        private TextView deliveryPrice;
        private TextView totalPrice;
        private TextView savedAmount;


        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);
            totalItems = itemView.findViewById(R.id.total_amount);
            totalItemsPrice = itemView.findViewById(R.id.total_item_price);

            totalPrice = itemView.findViewById(R.id.total_price);
            savedAmount = itemView.findViewById(R.id.saved_amount);
        }
        private void setTotalAmount(int totalItemText, int totalItemPriceText, String totalPriceText,String savedAmountText) {
            totalItems.setText("Giá tiền("+totalItemText+" sản phẩm)");
            totalItemsPrice.setText(totalItemPriceText+"đ");

            totalPrice.setText(totalPriceText+"đ");

        }
    }
    public class CardAddressViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_name,txt_phone,txt_address,txt_add;
        private Intent intent;


        public CardAddressViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.name_address);
            txt_phone = (TextView) itemView.findViewById(R.id.phone_address);
            txt_address = (TextView) itemView.findViewById(R.id.address_address);
            txt_add = (TextView) itemView.findViewById(R.id.add_address);

        }
        private void setAddress(String name,String phone, String address){
            if (name == null && phone == null && address == null){
                txt_name.setText("Vui lòng thêm địa chỉ");
            } else {
                txt_name.setText(name+" - ");
                txt_address.setText(address);
                txt_phone.setText(phone);
            }

            txt_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click();
                }
            });

        }
        



        public void click(){
            intent = new Intent(itemView.getContext(), Add_Address.class);
            itemView.getContext().startActivity(intent);
        }
    }
}
