package com.example.appbanhangonline.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.appbanhangonline.activity.Activity_card;
import com.example.appbanhangonline.activity.Prefconfig;
import com.example.appbanhangonline.adapter.DeXuatApdapter;
import com.example.appbanhangonline.adapter.FlashSaleAdapter;
import com.example.appbanhangonline.adapter.ProductAdapter;
import com.example.appbanhangonline.adapter.SliderAdapter;
import com.example.appbanhangonline.adapter.TestProductAdapter;
import com.example.appbanhangonline.adapter.TopProductAdapter;
import com.example.appbanhangonline.adapter.TopSearchAdapter;
import com.example.appbanhangonline.adapter.WatchedAdapter;
import com.example.appbanhangonline.activity.MainActivity_All_Product;
import com.example.appbanhangonline.login.LoginFragment;
import com.example.appbanhangonline.model.HomePageModel;
import com.example.appbanhangonline.model.Product;
import com.example.appbanhangonline.model.All;
import com.example.appbanhangonline.model.SlideItem;
import com.example.appbanhangonline.model.SliderModel;
import com.example.appbanhangonline.model.Testproduct;
import com.example.appbanhangonline.model.UserModel;
import com.example.appbanhangonline.model.Watcheds;
import com.example.appbanhangonline.R;
import com.example.appbanhangonline.service.GetDataRetrofitProduct;
import com.example.appbanhangonline.service.GetDataRetrofitWatched;
import com.example.appbanhangonline.service.RetrofitContact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    private List<Testproduct> listtest = new ArrayList<>();
    private  TestProductAdapter testProductAdapter;
    private SharedPreferences sharedPreferences;
    private String id;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        search_bar = view.findViewById(R.id.search);
        btnCart = view.findViewById(R.id.badge_icon);
        textCartItemCount = view.findViewById(R.id.badge_count);
        progressBar = view.findViewById(R.id.home_progressbar);
        swipeRefreshLayout = view.findViewById(R.id.home_swip);
        mCartItemCount = 0;
        setupBadge();
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
        sharedPreferences = getContext().getSharedPreferences(LoginFragment.USER, Context.MODE_PRIVATE);
        Log.d("gggg",String.valueOf(sharedPreferences.getString(LoginFragment.ID,"")));
        id = sharedPreferences.getString(LoginFragment.ID,"");
        allProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
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
//        rv2.setHasFixedSize(true);
//        rv2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv3 = view.findViewById(R.id.rv_top_search);
        ////




        allSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });







        recyclerView_watched = view.findViewById(R.id.rv_watched);
        recyclerView_watched.setHasFixedSize(true);
        recyclerView_watched.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));



        homeList.add(new HomePageModel(0,sliderModelFakeList));
        homeList.add(new HomePageModel(1,R.drawable.banner_shop,""));





        flashSaleAdapter = new FlashSaleAdapter(productList,getContext());
        topProductAdapter = new TopProductAdapter(productList,getContext());
        topSearchAdapter = new TopSearchAdapter(productList);

            setSale();
            setWatched();
            setAll();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setSale();
                setWatched();
                setAll();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        //progressBar.setVisibility(View.GONE);



        recyclerView_dexuat = view.findViewById(R.id.rv_dexuat);
       // recyclerView_dexuat.setLayoutAnimation(layoutAnimationController);

        recyclerView_dexuat.setHasFixedSize(true);
        recyclerView_dexuat.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));



        return view;
    }
    private void setWatched(){
        GetDataRetrofitWatched service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitWatched.class);
        Call<List<Watcheds>> call = service.getAll(id);
        call.enqueue(new Callback<List<Watcheds>>() {
            @Override
            public void onResponse(Call<List<Watcheds>> call, Response<List<Watcheds>> response) {
                if (response.isSuccessful()){

                    list_watched = response.body();
                    Collections.shuffle(list_watched);
                    watchedAdapter = new WatchedAdapter(list_watched,getContext());
                    recyclerView_watched.setAdapter(watchedAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Watcheds>> call, Throwable t) {

            }
        });
    }
    private void setSale(){
        GetDataRetrofitProduct service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitProduct.class);
        Call<List<Testproduct>> call = service.getSanPham();

        call.enqueue(new Callback<List<Testproduct>>() {
            @Override
            public void onResponse(Call<List<Testproduct>> call, Response<List<Testproduct>> response) {
                if (response.isSuccessful()){

                    listtest = response.body();
                    Collections.shuffle(listtest);
                    testProductAdapter = new TestProductAdapter(listtest,getContext());
                    rv1.setAdapter(testProductAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Testproduct>> call, Throwable t) {

            }
        });
    }
    private void setAll(){
        GetDataRetrofitProduct service = RetrofitContact.getRetrofitInstance().create(GetDataRetrofitProduct.class);
        Call<List<Testproduct>> call = service.getSanPham();

        call.enqueue(new Callback<List<Testproduct>>() {
            @Override
            public void onResponse(Call<List<Testproduct>> call, Response<List<Testproduct>> response) {
                if (response.isSuccessful()){

                    listtest = response.body();
                    Collections.shuffle(listtest);
                    deXuatApdapter = new DeXuatApdapter(listtest,getContext());
                    recyclerView_dexuat.setAdapter(deXuatApdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Testproduct>> call, Throwable t) {

            }
        });
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