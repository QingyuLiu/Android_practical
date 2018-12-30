package com.example.zhuyuting.tablayout.Adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zhuyuting.tablayout.UI.Fragment1;
import com.example.zhuyuting.tablayout.UI.Fragment2;
import com.example.zhuyuting.tablayout.UI.Fragment3;
import com.example.zhuyuting.tablayout.UI.Fragment4;

public class FragmentPageAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"首页", "团课", "商城","我的"};
    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new Fragment2();
        } else if (position == 2) {
            return new Fragment3();
        }else if (position==3){
            return new Fragment4();
        }
        return new Fragment1();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
