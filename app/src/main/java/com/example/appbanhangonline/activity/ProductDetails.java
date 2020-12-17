package com.example.appbanhangonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.Activity_card;
import com.example.appbanhangonline.adapter.ProductImagesAdapter;
import com.example.appbanhangonline.adapter.ProductSuggestAdapter;
import com.example.appbanhangonline.adapter.SearchAdapter;
import com.example.appbanhangonline.adapter.TestProductAdapter;
import com.example.appbanhangonline.login.LoginFragment;
import com.example.appbanhangonline.model.CardItemModel;
import com.example.appbanhangonline.model.Cart;
import com.example.appbanhangonline.model.Product;
import com.example.appbanhangonline.model.TestCard;
import com.example.appbanhangonline.model.Testproduct;
import com.example.appbanhangonline.model.Watched;
import com.example.appbanhangonline.model.Wishlist;
import com.example.appbanhangonline.service.GetDataRetrofitCard;
import com.example.appbanhangonline.service.GetDataRetrofitProduct;
import com.example.appbanhangonline.service.GetDataRetrofitWishList;
import com.example.appbanhangonline.service.RetrofitContact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.sufficientlysecure.htmltextview.HtmlAssetsImageGetter;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails extends AppCompatActivity {

    private TabLayout viewpagerIndicator;
    private ViewPager productImagesViewPager;
    private ProductImagesAdapter productImagesAdapter;
    private List<Testproduct> productList = new ArrayList<>();

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
    private String id;
    private String deatils;

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
    private HtmlTextView txt_details;
    private TestProductAdapter testproducts;
    private List<TestCard> testCardList = new ArrayList<>();

    ///


    private TextView textCartItemCount;
    public static int mCartItemCount ;
    private ImageView btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        init();
        btnCart = findViewById(R.id.badge_icon);
        textCartItemCount = findViewById(R.id.badge_count);
        mCartItemCount = 0;
        setupBadge();
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Activity_card.class);
                startActivity(intent);
                finish();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_title.setText("Sản phẩm");
        txt_details = findViewById(R.id.text_details);


        sharedPreferences = getSharedPreferences(MYCARD, Context.MODE_PRIVATE);


        intent = this.getIntent();
        price = intent.getIntExtra("product_price",0);

        txt_name.setText(intent.getStringExtra("product_name"));
        txt_price.setText(String.valueOf(intent.getIntExtra("product_price",0)));
//        txt_giamgia.setText(intent.getStringExtra("product_giam_gia"));
//        ratingBar.setRating(intent.getFloatExtra("product_rating",0));
        image = intent.getStringExtra("product_image");
        String id_product = intent.getStringExtra("id_product");
        deatils = intent.getStringExtra("details");
        //txt_details.setHtml(deatils,new HtmlHttpImageGetter(txt_details));
        txt_soluong.setText("1");

        int soluongss = Integer.parseInt(intent.getStringExtra("soluong"));



        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a <= soluongss){
                    ++a;
                    txt_soluong.setText(String.valueOf(a));
                }else {
                    imgPlus.setVisibility(View.VISIBLE);
                }
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


//        flashSaleAdapter = new ProductSuggestAdapter(productList);
//        recyclerView.setAdapter(flashSaleAdapter);

        String name = txt_name.getText().toString();
        String giamgia = txt_giamgia.getText().toString();

        int price = Integer.parseInt(txt_price.getText().toString());
        int soluong = Integer.parseInt(txt_soluong.getText().toString());


        sharedPreferences = getSharedPreferences(LoginFragment.USER, Context.MODE_PRIVATE);
        Log.d("gggg",String.valueOf(sharedPreferences.getString(LoginFragment.ID,"")));
        String id = sharedPreferences.getString(LoginFragment.ID,"");
        String a = intent.getStringExtra("product_name");
        setTheo(a);

        addToWishListbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ADDED_TO_WISHLIST){
                    ADDED_TO_WISHLIST = false;
                    addToWishListbtn.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                }else {


                    click(id,id_product,getApplicationContext());

                    ADDED_TO_WISHLIST = true;
                    addToWishListbtn.setSupportBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));

                }
            }
        });

        add_to_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   setText();
                   mCartItemCount += 1;
                   setupBadge();
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



        sharedPreferences = getSharedPreferences(LoginFragment.USER, Context.MODE_PRIVATE);
        Log.d("gggg",String.valueOf(sharedPreferences.getString(LoginFragment.ID,"")));
        id = sharedPreferences.getString(LoginFragment.ID,"");
        String id_product = intent.getStringExtra("id_product");
        int soluong = Integer.parseInt(txt_soluong.getText().toString());
        TestCard testCard = new TestCard(id,id_product,soluong);
        GetDataRetrofitCard service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitCard.class);
        Call<TestCard> call = service.adds(testCard);
        call.enqueue(new Callback<TestCard>() {
            @Override
            public void onResponse(Call<TestCard> call, Response<TestCard> response) {
                if(response.isSuccessful()){

                    Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"That bai",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TestCard> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"That bai",Toast.LENGTH_LONG).show();

            }
        });

//        String email = "ngovanduong123@gmail.com";
//        String name_product = txt_name.getText().toString();
//        int price = Integer.parseInt(txt_price.getText().toString());
//        int soluong = Integer.parseInt(txt_soluong.getText().toString());
//        int rating = 1;
//        String giam_gia = txt_giamgia.getText().toString();
//        String images = image;
//        Cart cart = new Cart(email,name_product,price,soluong,rating,giam_gia,images);
//        GetDataRetrofitCard service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitCard.class);
//        Call<Cart> call = service.add(cart);
//        call.enqueue(new Callback<Cart>() {
//            @Override
//            public void onResponse(Call<Cart> call, Response<Cart> response) {
//                if (response.isSuccessful()){
//                    Cart user1 = response.body();
//                    //mainIntent();
//                    Toast.makeText(getApplicationContext(),"Thanh cong",Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(),"Email ton tai",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Cart> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"That bai",Toast.LENGTH_SHORT).show();
//            }
//        });



    }
    private void setTheo(String ten){
        GetDataRetrofitProduct service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitProduct.class);
        Call<List<Testproduct>> call = service.timSanPham(ten);
        call.enqueue(new Callback<List<Testproduct>>() {
            @Override
            public void onResponse(Call<List<Testproduct>> call, Response<List<Testproduct>> response) {
                if (response.isSuccessful()){
                    productList = response.body();
                    testproducts = new TestProductAdapter(productList,getApplicationContext());
                    recyclerView.setAdapter(testproducts);
                }
            }

            @Override
            public void onFailure(Call<List<Testproduct>> call, Throwable t) {

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
    private void click(String idUser,String idSanPham, Context context){


        GetDataRetrofitWishList service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitWishList.class);
        Call<String> call = service.add(idUser,idSanPham);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"thanh cong",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"That bai",Toast.LENGTH_LONG).show();
            }
        });

    }
    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}