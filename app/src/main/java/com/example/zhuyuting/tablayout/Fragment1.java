package com.example.zhuyuting.tablayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.zhuyuting.tablayout.Adapter.CoachAdapter;
import com.example.zhuyuting.tablayout.Adapter.SQLService;
import com.example.zhuyuting.tablayout.Entity.BmobCoachTable;
import com.example.zhuyuting.tablayout.Entity.Coach;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Fragment1 extends Fragment {

    private List<Coach> coachList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.activity_book_trainer, null);
        if(Internet_check()){
            updateData();
        }else{
            getData();
        }
        View view = inflater.inflate(R.layout.fragment1,container,false);
//        initCoachs();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        CoachAdapter adapter = new CoachAdapter(coachList);
        recyclerView.setAdapter(adapter);
        return view;
    }


    //在fragment不能直接进行点击事件，需要放到oncreatActivity中
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ImageView img1 = getActivity().findViewById(R.id.imageview_11);
        final ImageView mediaPlayBtn = getActivity().findViewById(R.id.mediaplayBtn);
        final Button positionbwt = getActivity().findViewById(R.id.positionbtw);
        OnClick onclick = new OnClick();
        img1.setOnClickListener(onclick);
        mediaPlayBtn.setOnClickListener(onclick);
        positionbwt.setOnClickListener(onclick);
    }
    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.imageview_11:
                    intent = new Intent(getActivity(),BookTrainerActivity.class);
                    break;
                case R.id.mediaplayBtn:
                    intent = new Intent(getActivity(),MediaplayActivity.class);
                    break;
//                case R.id.positionbtw:
//                    intent = new Intent(getActivity(),MapActivity.class);
//                    break;
            }
            startActivity(intent);
        }
    }

    public void getData(){//离线从数据库读取数据
        SQLService sqlService = new SQLService(getActivity());
        List<Coach> list = sqlService.getScrollData();
        for(Coach p:list){
            coachList.add(p);//加到coachList列表里
        }
    }

    public void updateData(){//从网上读出来存进数据库里
        final SQLService sqlService = new SQLService(getActivity());
        sqlService.DeleteAll();//清空数据库原始数据
        BmobQuery<BmobCoachTable> query = new BmobQuery<BmobCoachTable>();
        query.findObjects(new FindListener<BmobCoachTable>() {
            @Override
            public void done(List<BmobCoachTable> list, BmobException e) {
                if(e == null){
                    for(BmobCoachTable bombCoachTable:list){
                        Coach coach = new Coach(bombCoachTable.getCname(),bombCoachTable.getCimg());
                        sqlService.save(coach);//存入新数据
                        coachList.add(coach);//显示数据
                    }
                    Toast.makeText(getActivity(), "已更新数据库！", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initCoachs(){
        BmobQuery<BmobCoachTable> query = new BmobQuery<BmobCoachTable>();
        query.findObjects(new FindListener<BmobCoachTable>() {
            @Override
            public void done(List<BmobCoachTable> list, BmobException e) {
                if(e == null){
                    for(BmobCoachTable bombCoachTable:list){

                        Coach coach = new Coach(bombCoachTable.getCname(),bombCoachTable.getCimg());
                        coachList.add(coach);//加到coachList列表里
                    }
                }else{
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
