package com.example.library.base;

import android.app.Application;

import com.example.library.api.TraceDataHepler;
import com.example.library.util.LogUtil;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-09-03 14:04
 */
public class BaseApplication extends Application {


    private static BaseApplication application;

    public static BaseApplication getInstance() {
        return application;
    }


    @Override
    public void onCreate() {
        application = this;
        super.onCreate();
        LogUtil.getInstance().setDebug(true);
        TraceDataHepler.init(this);
    }
}
