package com.example.appbanhangonline.adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.appbanhangonline.model.HomePageModel;
import com.example.appbanhangonline.model.SliderModel;
import com.example.appbanhangonline.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private List<HomePageModel> list;
    private int lastPosition = -1;

    public HomePageAdapter(List<HomePageModel> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getType()){
            case 0:
                return HomePageModel.SLIDER;
            case 1:
                return HomePageModel.STRIP_AD;
            case 2:
                return HomePageModel.FLASH_SALE;
            case 3:
                return HomePageModel.PRODUCT_HOT;
            case 4:
                return HomePageModel.PRODUCT_WATCHED;
            case 5:
                return HomePageModel.PRODUCT_ALL;
            default:
                return -1;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case HomePageModel.SLIDER:
                 View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpaper_silde, parent, false);
                 return new SliderViewHolder(view);
            case HomePageModel.FLASH_SALE:
                 View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flash_sale, parent, false);
                 return new FlashSaleViewHolder(view1);
            case HomePageModel.PRODUCT_ALL:
                 View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_de_xuat, parent, false);
                 return new AllViewholder(view2);
            case HomePageModel.PRODUCT_HOT:
                 View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot, parent, false);
                 return  new HotViewholder(view3);
            case HomePageModel.PRODUCT_WATCHED:
                 View view4 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_de_xuat, parent, false);
                 return  new WatchedViewholder(view4);
            case HomePageModel.STRIP_AD:
                 View view5 = LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_ad, parent, false);
                 return  new StripAdViewholder(view5);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
          int image = list.get(position).getImage();
          String price = list.get(position).getPrice();
          String giam_gia = list.get(position).getGiam_gia();
          String title = list.get(position).getTitle();
          float rating = list.get(position).getRatting();
          int amount = list.get(position).getAmount();
         switch (list.get(position).getType()){
             case HomePageModel.SLIDER:
                  List<SliderModel> slider_list = list.get(position).getSliderList();
                 ((SliderViewHolder)holder).setBannerSliderViewPager(slider_list);
                 break;
             case HomePageModel.FLASH_SALE:
                 ((FlashSaleViewHolder)holder).setFlash(image,price,giam_gia,rating, title);
                 break;
             case HomePageModel.PRODUCT_ALL:
                 ((AllViewholder)holder).setAll(image,price,giam_gia,rating,title);
                 break;
             case HomePageModel.PRODUCT_HOT:
                 ((HotViewholder)holder).setHot(image,price,giam_gia,rating,title);
                 break;
             case HomePageModel.PRODUCT_WATCHED:
                 ((WatchedViewholder)holder).setWatched(image,price,giam_gia,rating,title);
                 break;
             case HomePageModel.STRIP_AD:
                 ((StripAdViewholder)holder).setStripAd(list.get(position).getResource());
                 break;

         }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StripAdViewholder extends RecyclerView.ViewHolder{
        private ImageView stripAdImage;
        public StripAdViewholder(@NonNull View itemView) {
            super(itemView);
            stripAdImage = itemView.findViewById(R.id.strip_ad_image);
        }
        public void setStripAd(int recource) {
            //Glide.with(itemView.getContext()).load(recource).apply(new RequestOptions().placeholder(R.drawable.banner_placeholder)).into(stripAdImage);
            stripAdImage.setImageResource(recource);
//            stripAdContainer.setBackgroundColor(Color.parseColor(color));
        }
    }
    public class SliderViewHolder extends RecyclerView.ViewHolder{
        private ViewPager bannerSliderViewPager;
        private int currentPage = 2;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        private List<SliderModel> arrangedList = new ArrayList<>();

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.slider_images_viewpager);
        }

        private void setBannerSliderViewPager(final List<SliderModel> sliderModelList) {
            currentPage = 0;
            if (timer != null) {
                timer.cancel();
            }
            arrangedList = new ArrayList<>();
            for (int x = 0; x < arrangedList.size(); x++) {
                arrangedList.add(x, sliderModelList.get(x));
            }
            arrangedList.add(0, sliderModelList.get(sliderModelList.size() - 2));
            arrangedList.add(0, sliderModelList.get(sliderModelList.size() - 1));
            arrangedList.add(sliderModelList.get(0));
            arrangedList.add(sliderModelList.get(1));

            SliderAdapters sliderAdapter = new SliderAdapters(arrangedList);
            bannerSliderViewPager.setAdapter(sliderAdapter);
            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setPageMargin(20);

            bannerSliderViewPager.setCurrentItem(currentPage);
            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int i) {
                    currentPage = i;
                }

                @Override
                public void onPageScrollStateChanged(int i) {
                    if (i == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrangedList);
                    }
                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startBannerSliderShow(arrangedList);

            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    pageLooper(arrangedList);
                    stopBannerSliderShow();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSliderShow(sliderModelList);
                    }
                    return false;
                }
            });
        }

        private void pageLooper(List<SliderModel> sliderModelList) {
            if (currentPage == sliderModelList.size() - 2) {
                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size() - 3;
                bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
        }

        private void startBannerSliderShow(final List<SliderModel> sliderModelList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage >= sliderModelList.size()) {
                        currentPage = 0;
                    }
                    bannerSliderViewPager.setCurrentItem(currentPage++, true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopBannerSliderShow() {
            timer.cancel();
        }
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };
    public class FlashSaleViewHolder extends RecyclerView.ViewHolder {
        private ImageView product_image;
        private TextView txt_price, txt_giaGoc,txt_name;
        private RatingBar txt_rating;

        public FlashSaleViewHolder(@NonNull View itemView) {
            super(itemView);

            product_image = itemView.findViewById(R.id.product_image);
            txt_name = itemView.findViewById(R.id.product_name);
            txt_price = itemView.findViewById(R.id.product_price);
            txt_giaGoc = itemView.findViewById(R.id.cutted_price);
            txt_rating= itemView.findViewById(R.id.ratingBar);

        }
        private void setFlash(int image, String price,String giam_gia, float rating,String name ){
            product_image.setImageResource(image);
            txt_price.setText(price + " đ ");
            txt_giaGoc.setText(giam_gia + " đ ");
            txt_rating.setRating(rating);
            txt_name.setText(name);
        }

    }
    public class HotViewholder extends RecyclerView.ViewHolder{

        private ImageView product_image;
        private TextView txt_price, txt_giaGoc,txt_name;
        private RatingBar txt_rating;

        public HotViewholder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            txt_name = itemView.findViewById(R.id.product_name);
            txt_price = itemView.findViewById(R.id.product_price);
            txt_giaGoc = itemView.findViewById(R.id.cutted_price);
            txt_rating= itemView.findViewById(R.id.ratingBar);
        }
        private void setHot(int image, String price,String giam_gia, float rating, String name ){
            product_image.setImageResource(image);
            txt_price.setText(price + " đ ");
            txt_giaGoc.setText(giam_gia + " đ ");
            txt_rating.setRating(rating);
            txt_name.setText(name);
        }
    }
    public class WatchedViewholder extends RecyclerView.ViewHolder{

        private ImageView product_image;
        private TextView txt_price, txt_giaGoc,txt_name;
        private RatingBar txt_rating;

        public WatchedViewholder(@NonNull View itemView) {
            super(itemView);

            product_image = itemView.findViewById(R.id.product_image);
            txt_name = itemView.findViewById(R.id.product_name);
            txt_price = itemView.findViewById(R.id.product_price);
            txt_giaGoc = itemView.findViewById(R.id.cutted_price);
            txt_rating= itemView.findViewById(R.id.ratingBar);
        }
        private void setWatched(int image, String price,String giam_gia, float rating,String name ){
            product_image.setImageResource(image);
            txt_price.setText(price + " đ ");
            txt_giaGoc.setText(giam_gia + " đ ");
            txt_rating.setRating(rating);
            txt_name.setText(name);
        }
    }
    public class AllViewholder extends RecyclerView.ViewHolder{
        private ImageView product_image;
        private TextView txt_price, txt_giaGoc,txt_name;
        private RatingBar txt_rating;
        public AllViewholder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            txt_name = itemView.findViewById(R.id.product_name);
            txt_price = itemView.findViewById(R.id.product_price);
            txt_giaGoc = itemView.findViewById(R.id.cutted_price);
            txt_rating= itemView.findViewById(R.id.ratingBar);
        }
        private void setAll(int image, String price,String giam_gia, float rating,String name ){
            product_image.setImageResource(image);
            txt_price.setText(price + " đ ");
            txt_giaGoc.setText(giam_gia + " đ ");
            txt_rating.setRating(rating);
            txt_name.setText(name);
        }
    }


}
