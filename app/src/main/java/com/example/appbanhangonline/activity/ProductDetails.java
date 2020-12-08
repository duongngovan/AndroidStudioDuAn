package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.Activity_card;
import com.example.appbanhangonline.adapter.ProductImagesAdapter;
import com.example.appbanhangonline.adapter.ProductSuggestAdapter;
import com.example.appbanhangonline.model.CardItemModel;
import com.example.appbanhangonline.model.Cart;
import com.example.appbanhangonline.model.Product;
import com.example.appbanhangonline.model.Watched;
import com.example.appbanhangonline.model.Wishlist;
import com.example.appbanhangonline.service.GetDataRetrofitCard;
import com.example.appbanhangonline.service.GetDataRetrofitWishList;
import com.example.appbanhangonline.service.RetrofitContact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails extends AppCompatActivity {

    private TabLayout viewpagerIndicator;
    private ViewPager productImagesViewPager;
    private ProductImagesAdapter productImagesAdapter;
    private ArrayList<Product> productList = new ArrayList<>();

    public static int initialRating;
    public static LinearLayout rateNowContainer;
    private RecyclerView recyclerView;
    private ProductSuggestAdapter flashSaleAdapter;
    private Intent intent;
    private TextView txt_name,txt_price,txt_giamgia,txt_soluong;
    private ImageView imgPlus,imgMuis;
    private RatingBar ratingBar;
    private static boolean ADDED_TO_WISHLIST = false;
    private FloatingActionButton addToWishListbtn;
    private int a = 1;
    private LinearLayout add_to_card;
    private String name;
    private int price;
    private String image;

    ///
    private SharedPreferences sharedPreferences,sharedPreferences_wishlist;

    public static final String MYCARD = "mycard";
    public static final String PRICE = "price";
    public static final String AMOUNT = "amount";
    public static final String NAME = "name";
    public static final String GIAM_GIA = "giam_gia";

    private List<CardItemModel> list_card = new ArrayList<>();
    private Gson gson = new Gson();
    private CardItemModel cardItemModel;
    private List<Watched> list_wishlist = new ArrayList<>();
    private Watched watched = new Watched();

    private TextView txt_title;
    private ImageView img_back;
    private Button btn_mua;
    private Intent intens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        init();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Sản phẩm");

        sharedPreferences = getSharedPreferences(MYCARD, Context.MODE_PRIVATE);


        intent = this.getIntent();
        price = intent.getIntExtra("product_price",0);
        txt_name.setText(intent.getStringExtra("product_name"));
        txt_price.setText(String.valueOf(intent.getIntExtra("product_price",0)));
//        txt_giamgia.setText(intent.getStringExtra("product_giam_gia"));
//        ratingBar.setRating(intent.getFloatExtra("product_rating",0));
        image = intent.getStringExtra("product_image");

        txt_soluong.setText("1");



        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++a;
                txt_soluong.setText(String.valueOf(a));
            }
        });
        imgMuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --a;
                if(a<1){
                    txt_soluong.setText(String.valueOf(1));
                    a=1;
                }else {
                    txt_soluong.setText(String.valueOf(a));
                }
            }
        });

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);
        recyclerView = findViewById(R.id.recycler_sugget);




        List<String> list = new ArrayList<>();
        list.add(image);

        productImagesAdapter = new ProductImagesAdapter(list);
        productImagesViewPager.setAdapter(productImagesAdapter);
        viewpagerIndicator.setupWithViewPager(productImagesViewPager, true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        productList.add(new Product(R.drawable.ao1,"ao thun 1",5,200000,234));
        productList.add(new Product(R.drawable.ao2,"ao thun 2",3.5f,10000000,234));
        productList.add(new Product(R.drawable.ao3,"ao thun dai tay gia da",5,500000,234));
        productList.add(new Product(R.drawable.ao4,"ao thun 2",4,100000,234));
        productList.add(new Product(R.drawable.ao1,"ao thun 1",2.5f,200000,234));
        productList.add(new Product(R.drawable.ao2,"ao thun ao thun dai tay gia da2",5,20000,234));
        productList.add(new Product(R.drawable.ao3,"ao thun 1",5,200000,234));
        productList.add(new Product(R.drawable.cover,"ao thun 2",1,1600000,234));
        productList.add(new Product(R.drawable.ao1,"ao thun ao thun dai tay gia da1",5,1200000,234));
        productList.add(new Product(R.drawable.ao4,"ao thun 2",5,1800000,234));
        productList.add(new Product(R.drawable.ao1,"ao thun dai tay gia da",5,200000,234));

        flashSaleAdapter = new ProductSuggestAdapter(productList);
        recyclerView.setAdapter(flashSaleAdapter);

        String name = txt_name.getText().toString();
        String giamgia = txt_giamgia.getText().toString();

        int price = Integer.parseInt(txt_price.getText().toString());
        int soluong = Integer.parseInt(txt_soluong.getText().toString());


        addToWishListbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ADDED_TO_WISHLIST){
                    ADDED_TO_WISHLIST = false;
                    addToWishListbtn.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                }else {


                    click("ngovanduong123@gmail.com",name,price,soluong,2,giamgia,image,getApplicationContext());

                    ADDED_TO_WISHLIST = true;
                    addToWishListbtn.setSupportBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));

                }
            }
        });

        add_to_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   setText();
            }
        });
        btn_mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Activity_card.class);
                setText();
                startActivity(intent);
            }
        });
    }
    private void setText(){

        String email = "ngovanduong123@gmail.com";
        String name_product = txt_name.getText().toString();
        int price = Integer.parseInt(txt_price.getText().toString());
        int soluong = Integer.parseInt(txt_soluong.getText().toString());
        int rating = 1;
        String giam_gia = txt_giamgia.getText().toString();
        String images = image;
        Cart cart = new Cart(email,name_product,price,soluong,rating,giam_gia,images);
        GetDataRetrofitCard service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitCard.class);
        Call<Cart> call = service.add(cart);
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()){
                    Cart user1 = response.body();
                    //mainIntent();
                    Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Email ton tai",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"That bai",Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void init(){
        txt_giamgia = findViewById(R.id.cutted_price);
        txt_name = findViewById(R.id.product_title);
        ratingBar = findViewById(R.id.ratingBar);
        txt_price = findViewById(R.id.product_price);
        addToWishListbtn = findViewById(R.id.add_to_wishlist_btn);
        imgMuis = findViewById(R.id.minus);
        imgPlus = findViewById(R.id.plus);
        txt_soluong = findViewById(R.id.soluong);
        add_to_card = findViewById(R.id.add_to_cart_btn);
        txt_title = findViewById(R.id.title);
        img_back = findViewById(R.id.icon_back);
        btn_mua = findViewById(R.id.buy_now_btn);
    }
    private void click(String email, String name_product, int price, int soluong, int rating, String giamgia, String image, Context context){

        Wishlist watcheds= new Wishlist(email,name_product,price,soluong,rating,giamgia,image);
        GetDataRetrofitWishList service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitWishList.class);
        Call<Wishlist> call = service.add(watcheds);
        call.enqueue(new Callback<Wishlist>() {
            @Override
            public void onResponse(Call<Wishlist> call, Response<Wishlist> response) {
                if (response.isSuccessful()){
                    Wishlist user1 = response.body();
                    //mainIntent();
                    Toast.makeText(context,"Thanh cong",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,"Email ton tai",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Wishlist> call, Throwable t) {
                Toast.makeText(context,"That bai",Toast.LENGTH_SHORT).show();
            }
        });
    }
}