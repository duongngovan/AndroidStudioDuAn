package com.example.appbanhangonline.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhangonline.adapter.NotificationAdapter;
import com.example.appbanhangonline.model.Notification;
import com.example.appbanhangonline.R;

import java.util.ArrayList;
import java.util.List;


public class NotificationsFragment extends Fragment {
    private TextView tvTittle;
    private List<Notification> list;
    private NotificationAdapter notificationAdapter;
    private RecyclerView recyclerView;
    private ImageView btnCart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        btnCart = view.findViewById(R.id.badge_icon);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.activity_card);
            }
        });

        tvTittle = view.findViewById(R.id.title);
        tvTittle.setText("Thông Báo");
        recyclerView = view.findViewById(R.id.recycler_notification);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        list = new ArrayList<>();
        list.add(new Notification("Black Friday","Sale tất cả sản phẩm 50%","10:10 27-11-2020"));
        list.add(new Notification("Black Friday","Sale tất cả sản phẩm 50%","10:10 27-11-2020"));
        list.add(new Notification("Black Friday","Sale tất cả sản phẩm 50%","10:10 27-11-2020"));
        list.add(new Notification("Black Friday","Sale tất cả sản phẩm 50%","10:10 27-11-2020"));


        notificationAdapter = new NotificationAdapter(list);
        recyclerView.setAdapter(notificationAdapter);

        return view;
    }
}