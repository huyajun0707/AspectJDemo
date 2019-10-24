package com.example.library.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.library.entity.TraceInfo;

public  class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public TraceInfo getTrackProperties(){

        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取fragment，然后


    }
}
