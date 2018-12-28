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
import android.widget.Toast;

public class Fragment3 extends Fragment {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3,container,false);
        btn1 = view.findViewById(R.id.btn_31);
        btn2 = view.findViewById(R.id.btn_32);
        btn3 = view.findViewById(R.id.btn_33);
        btn4 = view.findViewById(R.id.btn_34);
        OnClick onclick = new OnClick();
        btn1.setOnClickListener(onclick);
        btn2.setOnClickListener(onclick);
        btn3.setOnClickListener(onclick);
        btn4.setOnClickListener(onclick);
        return view;
    }
    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_31://捕捉文字信息按钮
                    Toast toast = Toast.makeText(getContext(), "购买成功！", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.btn_32://捕捉文字信息按钮
                    Toast toast2 = Toast.makeText(getContext(), "购买成功！", Toast.LENGTH_SHORT);
                    toast2.show();
                    break;
                case R.id.btn_33://捕捉文字信息按钮
                    Toast toast3 = Toast.makeText(getContext(), "购买成功！", Toast.LENGTH_SHORT);
                    toast3.show();
                    break;
                case R.id.btn_34://捕捉文字信息按钮
                    Toast toast4 = Toast.makeText(getContext(), "购买成功！", Toast.LENGTH_SHORT);
                    toast4.show();
                    break;
            }
        }
    }
}
