package com.example.aspectdemo;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.example.library.constant.AopConstant;
import com.example.library.base.BaseActivity;
import com.example.library.constant.TrackEventType;
import com.example.library.entity.TraceInfo;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.mViewPager);
        tabLayout = findViewById(R.id.mTabLayout);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public TraceInfo getTrackProperties() {
        TraceInfo traceInfo = TraceInfo.newInstance(TrackEventType.VISIT);
        traceInfo.setPageId("home_front");
        traceInfo.setPageDesc("首页");
        return traceInfo;
    }
}
