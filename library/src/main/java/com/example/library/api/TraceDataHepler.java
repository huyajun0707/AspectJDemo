package com.example.library.api;

import android.app.Application;

import androidx.annotation.Keep;

import com.example.library.trace.TraceDataPrivate;
import com.example.library.util.TraceWorkerUtil;

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-02 16:21
 * @depiction   ：
 */
@Keep
public class TraceDataHepler {
    private static TraceDataHepler INSTANCE;
    private static final Object mLock = new Object();

    @Keep
    @SuppressWarnings("UnusedReturnValue")
    public static TraceDataHepler init(Application application) {
        synchronized (mLock) {
            if (null == INSTANCE) {
                INSTANCE = new TraceDataHepler(application);
            }
            return INSTANCE;
        }
    }

    @Keep
    public static TraceDataHepler getInstance() {

        return INSTANCE;
    }

    private TraceDataHepler(Application application) {
        TraceDataPrivate.registerActivityLifecycleCallbacks(application);
        TraceWorkerUtil.startWork();
        TraceDataPrivate.registerActivityStateObserver(application);

    }

    /**
     * 指定不采集哪个 Activity 的页面浏览事件
     *
     * @param activity Activity
     */
    public void ignoreAutoTrackActivity(Class<?> activity) {
        TraceDataPrivate.ignoreAutoTrackActivity(activity);
    }

    /**
     * 恢复采集某个 Activity 的页面浏览事件
     *
     * @param activity Activity
     */
    public void removeIgnoredActivity(Class<?> activity) {
        TraceDataPrivate.removeIgnoredActivity(activity);
    }


}
