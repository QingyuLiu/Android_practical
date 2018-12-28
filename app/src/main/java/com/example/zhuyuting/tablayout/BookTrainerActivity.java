package com.example.zhuyuting.tablayout;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BookTrainerActivity extends AppCompatActivity {
    private int imageIds[] = {R.drawable.viewpager1, R.drawable.viewpager2, R.drawable.viewpager3, R.drawable.viewpager4};
    private ArrayList<ImageView> images = new ArrayList<>();
    private ViewPager vp;
    private int oldPosition = 0;//记录上一次点的位置
    private int currentItem; //当前页面
    private ScheduledExecutorService scheduledExecutorService;
    private ImageView Image_t1;
    private ImageView Image_t2;
    private ImageView Image_t3;
    private ImageView Image_t4;
    private Button call_button;
    private Button call_button2;
    private Button call_button3;
    private Button call_button4;
    //图片标题
    private String titles[] = new String[]{"图片1", "图片2", "图片3", "图片4"};
    private ArrayList<View> dots = new ArrayList<View>();;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_trainer);

        Image_t1 = findViewById(R.id.image_t1);
        Image_t2 = findViewById(R.id.image_t2);
        Image_t3 = findViewById(R.id.image_t3);
        Image_t4 = findViewById(R.id.image_t4);
        call_button=findViewById(R.id.call_button);
        call_button2=findViewById(R.id.call_button);
        call_button3=findViewById(R.id.call_button);
        call_button4=findViewById(R.id.call_button);
        OnClick onclick = new OnClick();
        Image_t1.setOnClickListener(onclick);
        Image_t2.setOnClickListener(onclick);
        Image_t3.setOnClickListener(onclick);
        Image_t4.setOnClickListener(onclick);
        call_button.setOnClickListener(onclick);
        call_button2.setOnClickListener(onclick);
        call_button3.setOnClickListener(onclick);
        call_button4.setOnClickListener(onclick);

        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageIds[i]);

            images.add(imageView);
        }


        //显示的点 加入集合
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));

        //获取图片标题的id
        title = (TextView) findViewById(R.id.tv_test_title);

        //获取ViewPager 的id
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new ViewPagerAdapter());

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置页面刷新后的图片标题
                title.setText(titles[position]);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.image_t1:
                    pay();
                    break;
                case R.id.image_t2:
                    pay();
                    break;
                case R.id.image_t3:
                    pay();
                    break;
                case R.id.image_t4:
                    pay();
                    break;
                case R.id.call_button:
                    intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + "18514520601");
                    intent.setData(data);
                    startActivity(intent);
                    break;
                case R.id.call_button2:
                    intent = new Intent(Intent.ACTION_DIAL);
                    Uri data2 = Uri.parse("tel:" + "18514520601");
                    intent.setData(data2);
                    startActivity(intent);
                    break;
                case R.id.call_button3:
                    intent = new Intent(Intent.ACTION_DIAL);
                    Uri data3 = Uri.parse("tel:" + "18514520601");
                    intent.setData(data3);
                    startActivity(intent);
                    break;
                case R.id.call_button4:
                    intent = new Intent(Intent.ACTION_DIAL);
                    Uri data4 = Uri.parse("tel:" + "18514520601");
                    intent.setData(data4);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
        private void pay(){
            AlertDialog.Builder dialog = new AlertDialog.Builder(BookTrainerActivity.this);
            dialog.setTitle("支付前请确认");
            dialog.setMessage("确定要选择这位教练吗？");
            dialog.setCancelable(false);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast toast = Toast.makeText(BookTrainerActivity.this, "购买课程成功！", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            dialog.setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
        }
    }



    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //将试图移除试图组
            View v =images.get(position);
            container.removeView(v);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //将试图添加进试图组
            View v =images.get(position);
            container.addView(v);
            return v;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //每隔两秒换一张图片
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(),2,2, TimeUnit.SECONDS);

    }
    //实现一个碎片的接口
    class ViewPagerTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem+1)%imageIds.length;
            //更新界面
            handler.obtainMessage().sendToTarget();
        }
    }
    //在handler进行碎片跳转
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            vp.setCurrentItem(currentItem);
        }
    };
}