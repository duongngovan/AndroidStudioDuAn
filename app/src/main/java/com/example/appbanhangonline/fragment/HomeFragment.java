package com.example.appbanhangonline.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangonline.activity.Activity_card;
import com.example.appbanhangonline.adapter.DeXuatApdapter;
import com.example.appbanhangonline.adapter.FlashSaleAdapter;
import com.example.appbanhangonline.adapter.ProductAdapter;
import com.example.appbanhangonline.adapter.SliderAdapter;
import com.example.appbanhangonline.adapter.TopProductAdapter;
import com.example.appbanhangonline.adapter.TopSearchAdapter;
import com.example.appbanhangonline.adapter.WatchedAdapter;
import com.example.appbanhangonline.activity.MainActivity_All_Product;
import com.example.appbanhangonline.model.HomePageModel;
import com.example.appbanhangonline.model.Product;
import com.example.appbanhangonline.model.All;
import com.example.appbanhangonline.model.SlideItem;
import com.example.appbanhangonline.model.SliderModel;
import com.example.appbanhangonline.model.Watcheds;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.service.GetDataRetrofitProduct;
import com.example.appbanhangonline.service.GetDataRetrofitWatched;
import com.example.appbanhangonline.service.RetrofitContact;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private ImageView btnCart;
    private CardView search_bar;
    private RecyclerView rv1,rv2,rv3,rv4;
    private ArrayList<Product> productList = new ArrayList<>();
    private FlashSaleAdapter flashSaleAdapter;
    private TopProductAdapter topProductAdapter;

    private TopSearchAdapter topSearchAdapter;

    private ViewPager2 viewPager;
    private SliderAdapter sliderAdapter;
    private Handler slideHandler = new Handler();
    private RecyclerView recyclerView_watched;
    private List<Watcheds> list_watched = new ArrayList<>();
    private WatchedAdapter watchedAdapter;


    private DeXuatApdapter deXuatApdapter;
    private RecyclerView recyclerView_dexuat;

    private TextView textCartItemCount;
    public static int mCartItemCount ;


    private  List<SlideItem> list;
    private List<HomePageModel> homeList = new ArrayList<>();
    private Animation scaleUp, scaleDown;
    private Button allSale,allHot;

    private List<All> list_product = new ArrayList<>();
    private ProductAdapter productAdapter;
    private Button allProduct;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        search_bar = view.findViewById(R.id.search);
        btnCart = view.findViewById(R.id.badge_icon);
        textCartItemCount = view.findViewById(R.id.badge_count);
        //
        mCartItemCount = new Activity_card().LIST_CARD;

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.activity_card);
            }
        });
        Intent intent = new Intent(getContext(), MainActivity_All_Product.class);

        scaleUp = AnimationUtils.loadAnimation(getContext(),R.anim.button_scale_up);

        scaleDown = AnimationUtils.loadAnimation(getContext(),R.anim.button_scale_down);
        allProduct = view.findViewById(R.id.gird_product_layout);
        allSale = view.findViewById(R.id.gird_product_layout_viewall_btn);
        allHot = view.findViewById(R.id.gird_product_layout_viewall_btns);
        allProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        allHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
//        allSale.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_UP){
//                    allSale.startAnimation( scaleUp);
//                }else   if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    allSale.startAnimation( scaleDown);
//                }
//                return true;
//            }
//        });
        allHot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    allHot.startAnimation( scaleUp);
                }else   if(event.getAction() == MotionEvent.ACTION_DOWN){
                    allHot.startAnimation( scaleDown);
                }
                return true;
            }
        });


        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animation_down_to_up);

         list = new ArrayList<>();
        list.add(new SlideItem(R.drawable.poster1));
        list.add(new SlideItem(R.drawable.poster2));
        list.add(new SlideItem(R.drawable.poster3));
        list.add(new SlideItem(R.drawable.poster4));
        list.add(new SlideItem(R.drawable.poster5));
        viewPager = view.findViewById(R.id.slider_images_viewpager);

        viewPager.setAdapter(new SliderAdapter(list,viewPager));
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 -Math.abs(position);
                page.setScaleX(0.85f + r * 0.15f);
            }
        });
        viewPager.setPageTransformer(compositePageTransformer);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,3000);


            }
        });


        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.searchActivity);

            }
        });
//        btnCart = view.findViewById(R.id.cart);
//        btnCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Đây là giỏ hàng", Toast.LENGTH_SHORT).show();
//            }
//        });

        List<SliderModel> sliderModelFakeList = new ArrayList<>();
        sliderModelFakeList.add(new SliderModel("null", R.drawable.ao1));
        sliderModelFakeList.add(new SliderModel("null", R.drawable.ao1));
        sliderModelFakeList.add(new SliderModel("null", R.drawable.ao1));
        sliderModelFakeList.add(new SliderModel("null", R.drawable.ao1));
        sliderModelFakeList.add(new SliderModel("null", R.drawable.ao1));
//
//
        rv1 = view.findViewById(R.id.rv_flash_sale);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv2 = view.findViewById(R.id.rv_hot);
        rv2.setHasFixedSize(true);
        rv2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv3 = view.findViewById(R.id.rv_top_search);
        ////

//        GetDataRetrofitProduct serice = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitProduct.class);
//        Call<List<Products>> call = serice.getAll();
//        call.enqueue(new Callback<List<Products>>() {
//            @Override
//            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
//                if (response.isSuccessful()){
//
//                    list_product = response.body();
//                    productAdapter = new ProductAdapter(list_product,getContext());
//                    rv1.setAdapter(productAdapter);
//                    Log.d("list", String.valueOf(response.body()));
//                }else {
//                    Toast.makeText(getContext(),"faild",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Products>> call, Throwable t) {
//
//            }
//        });
        allSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });


        ////
//        rv3.setHasFixedSize(true);
//        rv3.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.HORIZONTAL,false));
//        rv4 = view.findViewById(R.id.rv_list_sp);
//        rv4.setHasFixedSize(true);
//        rv4.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));


        recyclerView_watched = view.findViewById(R.id.rv_watched);
        recyclerView_watched.setHasFixedSize(true);
        recyclerView_watched.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

//        productList.add(new Product(R.drawable.ao1,"ao thun 1",5,200000,234,"123"));
//        productList.add(new Product(R.drawable.ao2,"ao thun 2",3.5f,100000,234,"123"));
//        productList.add(new Product(R.drawable.ao3,"ao thun dai tay gia da",5,100000,234,"123"));
//        productList.add(new Product(R.drawable.ao4,"ao thun 2",4,100000,234,"123"));
//        productList.add(new Product(R.drawable.ao1,"ao thun 1",2.5f,100000,234,"123"));
//        productList.add(new Product(R.drawable.ao2,"ao thun ao thun dai ",5,20000,234,"123"));
//        productList.add(new Product(R.drawable.ao3,"ao thun 1",5,20000,234,"123"));
//        productList.add(new Product(R.drawable.cover,"ao thun 2",1,20000,234,"123"));
//        productList.add(new Product(R.drawable.ao1,"ao thun ao thun dai ",5,20000,234,"123"));
//        productList.add(new Product(R.drawable.ao4,"ao thun 2",5,20000,234,"123"));
//        productList.add(new Product(R.drawable.ao1,"ao thun dai tay gia da",5,20000,234,"123"));
////
//        productList.add(new Product(R.drawable.ao1,"ao thun 1",5,20000,234,"123"));
//        productList.add(new Product(R.drawable.ao2,"ao thun 2",3.5f,20000,234,"123"));
//        productList.add(new Product(R.drawable.ao3,"ao thun dai tay gia da",5,5000,234,"123"));
//        productList.add(new Product(R.drawable.ao4,"ao thun 2",4,5000,234,"123"));
//        productList.add(new Product(R.drawable.ao1,"ao thun 1",2.5f,5000,234,"123"));
//        productList.add(new Product(R.drawable.ao2,"ao thun ao thun dai ",5,5000,234,"123"));
//        productList.add(new Product(R.drawable.ao3,"ao thun 1",5,5000,234,"123"));
//        productList.add(new Product(R.drawable.cover,"ao thun 2",1,5000,234,"123"));
//        productList.add(new Product(R.drawable.ao1,"ao thun ao thun dai ",5,5000,234,"123"));
//        productList.add(new Product(R.drawable.ao4,"ao thun 2",5,5000,234,"123"));
//        productList.add(new Product(R.drawable.ao1,"ao thun dai tay gia da",5,5000,234,"123"));
////
//        productList.add(new Product(R.drawable.ao1,"ao thun 1",5,5000,234,"123"));
//        productList.add(new Product(R.drawable.ao2,"ao thun 2",3.5f,5000,234,"123"));
//        productList.add(new Product(R.drawable.ao3,"ao thun dai tay gia da",5,5000,234,"123"));
//        productList.add(new Product(R.drawable.ao4,"ao thun 2",4,5000,234,"123"));
//        productList.add(new Product(R.drawable.ao1,"ao thun 1",2.5f,30000,234,"123"));
//        productList.add(new Product(R.drawable.ao2,"ao thun ao thun dai ",5,30000,234,"123"));
//        productList.add(new Product(R.drawable.ao3,"ao thun 1",5,30000,234,"123"));
//        productList.add(new Product(R.drawable.cover,"ao thun 2",1,30000,234,"123"));
//        productList.add(new Product(R.drawable.ao1,"ao thun ao thun dai ",5,30000,234,"123"));
//        productList.add(new Product(R.drawable.ao4,"ao thun 2",5,10000,234,"123"));
//        productList.add(new Product(R.drawable.ao1,"ao thun dai tay gia da",5,10000,234,"123"));
////

        homeList.add(new HomePageModel(0,sliderModelFakeList));
        homeList.add(new HomePageModel(1,R.drawable.banner_shop,""));


//        list_watched.add(new Watched(R.drawable.ao1,"ao thun 1",5,200000,234));
//        list_watched.add(new Watched(R.drawable.ao1,"ao thun 1",5,324,234));
//        list_watched.add(new Watched(R.drawable.ao1,"ao thun 1",5,200000,234));
//        list_watched.add(new Watched(R.drawable.ao1,"ao thun 1",5,200000,234));
//        list_watched.add(new Watched(R.drawable.ao1,"ao thun 1",5,200000,234));

        ///list.watch

        GetDataRetrofitWatched serice = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitWatched.class);
        Call<List<Watcheds>> call = serice.getAll();
        Log.d("a2","tau vao roi");
        call.enqueue(new Callback<List<Watcheds>>() {
            @Override
            public void onResponse(Call<List<Watcheds>> call, Response<List<Watcheds>> response) {
                if (response.isSuccessful()){
                    Log.d("a1", "onResponse: "+response.body());
                    list_watched = response.body();
                    watchedAdapter = new WatchedAdapter(list_watched,getContext());
                    recyclerView_watched.setAdapter(watchedAdapter);
                    watchedAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(),"faild",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Watcheds>> call, Throwable t) {
                Toast.makeText(getContext(),"fall",Toast.LENGTH_SHORT).show();
                Log.d("a2","fail");
            }
        });
        ///

        GetDataRetrofitProduct serices = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitProduct.class);
        Call<List<All>> calls = serices.getAll();
        Log.d("a2","tau vao roi");
        calls.enqueue(new Callback<List<All>>() {
            @Override
            public void onResponse(Call<List<All>> call, Response<List<All>> response) {
                if (response.isSuccessful()){
                    Log.d("a1", "onResponse: "+response.body());
                    list_product = response.body();
                    productAdapter = new ProductAdapter(list_product,getContext());
                    rv1.setAdapter(productAdapter);

                    Log.d("list", String.valueOf(response.body()));
                }else {
                    Toast.makeText(getContext(),"faild",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<All>> call, Throwable t) {
                Toast.makeText(getContext(),"fall",Toast.LENGTH_SHORT).show();
            }
        });

        GetDataRetrofitProduct sericess = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitProduct.class);
        Call<List<All>> callss = sericess.getAlls();
        Log.d("a2","tau vao roi");
        callss.enqueue(new Callback<List<All>>() {
            @Override
            public void onResponse(Call<List<All>> call, Response<List<All>> response) {
                if (response.isSuccessful()){
                    Log.d("a1", "onResponse: "+response.body());
                    list_product = response.body();
                    productAdapter = new ProductAdapter(list_product,getContext());

                    rv2.setAdapter(productAdapter);
                    Log.d("list", String.valueOf(response.body()));
                }else {
                    Toast.makeText(getContext(),"faild",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<All>> call, Throwable t) {
                Toast.makeText(getContext(),"fall",Toast.LENGTH_SHORT).show();
            }
        });


        ///
        //
        flashSaleAdapter = new FlashSaleAdapter(productList,getContext());
        topProductAdapter = new TopProductAdapter(productList,getContext());
        topSearchAdapter = new TopSearchAdapter(productList);



//
        //rv1.setAdapter(flashSaleAdapter);

//        rv3.setAdapter(topSearchAdapter);
       // rv4.setAdapter(allProductAdapter);

        setupBadge();

        recyclerView_dexuat = view.findViewById(R.id.rv_dexuat);
       // recyclerView_dexuat.setLayoutAnimation(layoutAnimationController);

        recyclerView_dexuat.setHasFixedSize(true);
        recyclerView_dexuat.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        GetDataRetrofitProduct sericesss = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitProduct.class);
        Call<List<All>> callsss = sericesss.getAll();
        Log.d("a2","tau vao roi");
        callsss.enqueue(new Callback<List<All>>() {
            @Override
            public void onResponse(Call<List<All>> call, Response<List<All>> response) {
                if (response.isSuccessful()){
                    Log.d("a1", "onResponse: "+response.body());
                    list_product = response.body();
                    deXuatApdapter = new DeXuatApdapter(list_product,getContext());
                    recyclerView_dexuat.setAdapter(deXuatApdapter);

                    Log.d("list", String.valueOf(response.body()));
                }else {
                    Toast.makeText(getContext(),"faild",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<All>> call, Throwable t) {
                Toast.makeText(getContext(),"fall",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable,3000);
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
    private void setProductAdapter(){





    }

}