package com.example.appbanhangonline.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.login.LoginFragment;


public class ProfileFragment extends Fragment {
    private Toolbar toolbar;
    private CardView btn1,profile_shop,wishlist,watched,thongtin,btn_dangxuat;
    private SharedPreferences sharedPreferences;
    private String email,fullname;
    private TextView txt_email;
    private TextView txt_fullname;
    private ImageView btnCart;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        btn1 = view.findViewById(R.id.btn1);
        btnCart = view.findViewById(R.id.badge_icon);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.activity_card);
            }
        });
        btn_dangxuat = view.findViewById(R.id.btn_dangxuat);

        txt_email = view.findViewById(R.id.email_user);
        txt_fullname = view.findViewById(R.id.fullname_user);

        wishlist = view.findViewById(R.id.btn_wishlist);
        watched = view.findViewById(R.id.profile_watched);
        thongtin = view.findViewById(R.id.thongtincanhan);
        thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.mainActivity_ThongTin_User);
            }
        });
        watched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.mainActivity_Watched);
            }
        });
//        profile_shop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(view).navigate(R.id.shop_Details);
//            }
//        });

        sharedPreferences = getContext().getSharedPreferences(LoginFragment.USER, Context.MODE_PRIVATE);
        Log.d("gggg",String.valueOf(sharedPreferences.getString(LoginFragment.ID,"")));
        email = sharedPreferences.getString(LoginFragment.EMAIL,"");
        fullname = sharedPreferences.getString(LoginFragment.FULLNAME,"");
        txt_fullname.setText(fullname);
        txt_email.setText(email);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.mainActivity_HoaDon);
            }
        });
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.mainActivity_wishlist);
            }
        });
        btn_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
                Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Navigation.findNavController(view).navigate(R.id.loginActivity);
            }
        });

        return view;
    }
}