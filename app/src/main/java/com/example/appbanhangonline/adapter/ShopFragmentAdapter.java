package com.example.appbanhangonline.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.appbanhangonline.fragment.Product_Shop_Fragment;

public class ShopFragmentAdapter extends FragmentPagerAdapter {

    public ShopFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Shop_CuaHang_Fragment shop_cuaHang_fragment = new Shop_CuaHang_Fragment();
                return  shop_cuaHang_fragment;

            case 1:
                Product_Shop_Fragment product_shop_fragment = new Product_Shop_Fragment();
                return  product_shop_fragment;

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Cửa hàng";
                break;
            case 1:
                title = "Thông tin";
                break;

        }
        return title;
    }
}
