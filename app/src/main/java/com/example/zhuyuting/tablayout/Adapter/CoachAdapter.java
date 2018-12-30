package com.example.zhuyuting.tablayout.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhuyuting.tablayout.Entity.Coach;
import com.example.zhuyuting.tablayout.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoachAdapter extends RecyclerView.Adapter<CoachAdapter.ViewHolder> {

    private Context mContext;
    private List<Coach> mCoachList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView coachImage;
        TextView coachName;
        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            coachImage = view.findViewById(R.id.coach_image);
            coachName = view.findViewById(R.id.coach_name);
        }
    }

    public CoachAdapter(List<Coach>coachList){
        mCoachList = coachList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion = holder.getAdapterPosition();
                Coach coach = mCoachList.get(postion);
                Toast.makeText(v.getContext(),"Coach:"+coach.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        holder.coachImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int postion = holder.getAdapterPosition();
                Coach coach = mCoachList.get(postion);
                Toast.makeText(v.getContext(),"Coach:"+coach.getImageUrl(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Coach coach =mCoachList.get(position);
        holder.coachName.setText(coach.getName());

        //holder.coachImage.setImageResource(coach.getImageId());//法1加载图片
        //Glide.with(mContext).load(coach.getImageId()).into(holder.coachImage);//换了一种方式加载图片
        Picasso.with(mContext).load(coach.getImageUrl()).into(holder.coachImage);
    }

    @Override
    public int getItemCount(){
        return mCoachList.size();
    }
}
