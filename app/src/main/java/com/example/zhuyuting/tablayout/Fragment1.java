package com.example.zhuyuting.tablayout;

import android.content.Intent;
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
        View view = inflater.inflate(R.layout.fragment1,container,false);
        initCoachs();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        CoachAdapter adapter = new CoachAdapter(coachList);
        recyclerView.setAdapter(adapter);
        return view;
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
}
