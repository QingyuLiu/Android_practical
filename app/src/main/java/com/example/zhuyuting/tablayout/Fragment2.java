package com.example.zhuyuting.tablayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class Fragment2 extends Fragment {
    private ImageView Image_class1;
    private ImageView Image_class2;
    private ImageView Image_class3;
    private ImageView Image_class4;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        Image_class1 = view.findViewById(R.id.image_class1);
        Image_class2 = view.findViewById(R.id.image_class2);
        Image_class3 = view.findViewById(R.id.image_class3);
        Image_class4 = view.findViewById(R.id.image_class4);
        OnClick onclick = new OnClick();
        Image_class1.setOnClickListener(onclick);
        Image_class2.setOnClickListener(onclick);
        Image_class3.setOnClickListener(onclick);
        Image_class4.setOnClickListener(onclick);
        return view;
    }
    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.image_class1://捕捉文字信息按钮
                    Toast toast = Toast.makeText(getContext(), "您已成功预定NTC全能综训", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.image_class2://捕捉文字信息按钮
                    Toast toast2 = Toast.makeText(getContext(), "您已成功预定杠铃雕塑", Toast.LENGTH_SHORT);
                    toast2.show();
                    break;
                case R.id.image_class3://捕捉文字信息按钮
                    Toast toast3 = Toast.makeText(getContext(), "该课程还未开放，请稍后再预约！", Toast.LENGTH_SHORT);
                    toast3.show();
                    break;
                case R.id.image_class4://捕捉文字信息按钮
                    Toast toast4 = Toast.makeText(getContext(), "该课程还未开放，请稍后再预约！", Toast.LENGTH_SHORT);
                    toast4.show();
                    break;
            }
        }
    }

}
