package com.example.aspectdemo;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends FragmentPagerAdapter {


    private List<String> beanList = new ArrayList<>();


    public MyAdapter(FragmentManager fm) {
        super(fm);
        beanList.add("1");
        beanList.add("2");
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {//类型 0：全部 1：进行中 2：已完成
            case 0:
                fragment = BlankFragment.newInstance(1);//进行中
                break;
            case 1:
            default:
                fragment = BlankFragment.newInstance(2);//已完成
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return beanList.get(position);
    }
}
