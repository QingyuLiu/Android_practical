package com.example.zhuyuting.tablayout.UI;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhuyuting.tablayout.Entity.Person;
import com.example.zhuyuting.tablayout.R;
import com.squareup.picasso.Picasso;

import cn.bmob.v3.BmobUser;

public class Fragment4 extends Fragment {


    private TextView tname;
    private String name;
    private TextView tbalance;
    private int balance;
    private Person person = null;
    private ImageView imageView;
    private String url;
    private TextView gender;
    private TextView gender2;
    private TextView jianshenhao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4,container,false);
            person = BmobUser.getCurrentUser(Person.class);
            tname = view.findViewById(R.id.textview_43);
            tbalance = view.findViewById(R.id.textview_45);
            imageView = view.findViewById(R.id.imgview_profile);
            if(null != person) {
                name = (String) BmobUser.getObjectByKey("username");
                tname.setText(name);
                balance = (int) BmobUser.getObjectByKey("balance");
                tbalance.setText(balance + "");
                if(person.getProfile_photo()!=null){
//                    url = person.getProfile_photo();
//                    Picasso.with(getContext())
//                            .load(url)
//                            .into(imageView);
                }
            }else{
                gender = view.findViewById(R.id.textview_44);
                gender2 = view.findViewById(R.id.textview_45);
                jianshenhao = view.findViewById(R.id.textview_47);
                jianshenhao.setText("qquser");
                gender.setText("性别");
                gender2.setText(LoginActivity.gender);
                tname.setText(LoginActivity.tname);
                Picasso.with(getContext())
                        .load(LoginActivity.imgurl)
                        .into(imageView);
            }
        return view;
    }

    private boolean Internet_check(){
        boolean result = false;
        Activity act = (Activity) this.getContext();
        ConnectivityManager cm = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            Toast.makeText(getActivity(), "没网", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "有网", Toast.LENGTH_SHORT).show();
            result = true;
        }
        return result;
    }


}
