package com.example.zhuyuting.tablayout.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhuyuting.tablayout.Entity.Person;
import com.example.zhuyuting.tablayout.R;
import com.squareup.picasso.Picasso;

import cn.bmob.v3.BmobUser;

public class Fragment4 extends Fragment {


    private TextView tname;
    private String name;
    private TextView tbalance;
    private int balance;
    private Person person;

    private ImageView imageView;
    private String url;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4,container,false);

        person = BmobUser.getCurrentUser(Person.class);
        name = (String) BmobUser.getObjectByKey("username");
        tname = view.findViewById(R.id.textview_43);
        tname.setText(name);

        balance = (int)BmobUser.getObjectByKey("balance");
        tbalance = view.findViewById(R.id.textview_45);
        tbalance.setText(balance+"");

        imageView = view.findViewById(R.id.imgview_profile);

        url = person.getProfile_photo();
        Picasso.with(getContext())
                .load(url)
                .into(imageView);
        return view;
    }
}
