package com.example.library.trace;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Keep;

import com.example.library.base.BaseActivity;
import com.example.library.daodb.DaoManager;
import com.example.library.entity.ListTraceInfo;
import com.example.library.entity.TraceInfo;
import com.example.library.util.DateUtil;
import com.example.library.daodb.launch.TraceDatabaseHelper;
import com.example.library.util.LogUtil;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class TraceDataPrivate {
    private static List<String> mIgnoredActivities;
    private static TraceDatabaseHelper mDatabaseHelper;
    private static CountDownTimer countDownTimer;
    private static WeakReference<Activity> mCurrentActivity;
    private final static int SESSION_INTERVAL_TIME = 30 * 1000;


    private static Map<Context, Long> durationMap = new WeakHashMap<>();
    private static Map<Context, Long> resumeTimeMap = new WeakHashMap<>();


    static {
        mIgnoredActivities = new ArrayList<>();
    }


    public static void ignoreAutoTrackActivity(Class<?> activity) {
        if (activity == null) {
            return;
        }

        mIgnoredActivities.add(activity.getCanonicalName());
    }

    public static void removeIgnoredActivity(Class<?> activity) {
        if (activity == null) {
            return;
        }

        if (mIgnoredActivities.contains(activity.getCanonicalName())) {
            mIgnoredActivities.remove(activity.getCanonicalName());
        }
    }


    /**
     * Track 页面浏览事件
     *
     * @param activity Activity
     */
    @Keep
    private static void trackAppViewScreen(Activity activity) {
        try {
            if (activity == null) {
                return;
            }
            if (mIgnoredActivities.contains(activity.getClass().getCanonicalName())) {
                return;
            }
            BaseActivity baseActivity = (BaseActivity) activity;
            insertTraceInfo(baseActivity.getTrackProperties());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册 Application.ActivityLifecycleCallbacks
     *
     * @param application Application
     */
    @TargetApi(14)
    public static void registerActivityLifecycleCallbacks(Application application) {

        mDatabaseHelper = new TraceDatabaseHelper(application.getApplicationContext(), application.getPackageName());
        countDownTimer = new CountDownTimer(SESSION_INTERVAL_TIME, 10 * 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if (mCurrentActivity != null) {
                    trackAppEnd(mCurrentActivity.get());
                }
            }
        };

        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
//                FragmentTrackAPI.registerFragmentLifeCycle(activity);
                durationMap.put(activity, 0L);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                //app启动检测判断
                mDatabaseHelper.commitAppStart(true);
                double timeDiff = System.currentTimeMillis() - mDatabaseHelper.getAppPausedTime();
                if (timeDiff > SESSION_INTERVAL_TIME) {
                    if (!mDatabaseHelper.getAppEndEventState()) {
                        trackAppEnd(activity);
                    }
                }

                if (mDatabaseHelper.getAppEndEventState()) {
                    mDatabaseHelper.commitAppEndEventState(false);
                    trackAppStart(activity);
                }

            }

            @Override
            public void onActivityResumed(Activity activity) {
                trackAppViewScreen(activity);
                resumeTimeMap.put(activity, System.currentTimeMillis());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                //app退出记录，开始倒计时，满足30秒则记为应用退出
                mCurrentActivity = new WeakReference<>(activity);
                countDownTimer.start();
                mDatabaseHelper.commitAppPausedTime(System.currentTimeMillis());
                durationMap.put(activity, durationMap.get(activity)
                        + (System.currentTimeMillis() - resumeTimeMap.get(activity)));
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                long duration = durationMap.get(activity);
                if (duration > 0) {
                    Log.e("--->duration",activity.getClass().getName()+"+"+duration);

                }
                resumeTimeMap.remove(activity);
                durationMap.remove(activity);


            }
        });
    }


    /**
     * 注册 AppStart 的监听
     */
    public static void registerActivityStateObserver(Application application) {
        application.getContentResolver().registerContentObserver(mDatabaseHelper.getAppStartUri(),
                false, new ContentObserver(new Handler()) {
                    @Override
                    public void onChange(boolean selfChange, Uri uri) {
                        if (mDatabaseHelper.getAppStartUri().equals(uri)) {
                            countDownTimer.cancel();
                        }
                    }
                });
    }


    /**
     * Track $AppStart 事件
     */
    private static void trackAppStart(Activity activity) {
        try {
            if (activity == null) {
                return;
            }
            Log.e("trackAppStart", "$AppStart");
            // TODO 插入本地数据库
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Track $AppEnd 事件
     */
    private static void trackAppEnd(Activity activity) {
        try {
            if (activity == null) {
                return;
            }
            //TODO 对应数据插入本地数据库 对应insert

            Log.e("trackAppEnd", "$AppEnd");
            mDatabaseHelper.commitAppEndEventState(true);
            mCurrentActivity = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 将埋点数据写入本地数据库
     *
     * @param traceInfo
     */

    public static void insertTraceInfo(final TraceInfo traceInfo) {
        if (traceInfo != null) {
            try {
                traceInfo.setCreateTime(DateUtil.getChinaTime());
                LogUtil.getInstance().d("insertTraceInfo", traceInfo.toString());
                DaoManager.getInstance().getDaoSession().startAsyncSession().runInTx(new Runnable() {
                    @Override
                    public void run() {
                        DaoManager.getInstance().getDaoSession().getTraceInfoDao().insert(traceInfo);

                    }
                });
            } catch (Exception e) {
                LogUtil.getInstance().e("insertTraceInfo", "数据重复插入");

            }
        }
    }


    /**
     * 上传埋点数据
     */
    public static void uploadTraceData() {

        List<TraceInfo> traceInfos = DaoManager.getInstance().getDaoSession().getTraceInfoDao().queryBuilder().limit(10).list();

        if (traceInfos != null && traceInfos.size() > 0) {
            ListTraceInfo listTraceInfo = new ListTraceInfo();
            List<ListTraceInfo.TraceItem> traceItems = new ArrayList<>();
            for (int i = 0; i < traceInfos.size(); i++) {
                ListTraceInfo.TraceItem traceItem = new ListTraceInfo.TraceItem();
//                traceItem.setAppChannel();
                traceItem.setAppVersion(traceInfos.get(i).getAppVersion());
                traceItem.setBuryingPointType(traceInfos.get(i).getBuryingPointType());
                traceItem.setBusiExt(traceInfos.get(i).getBusiExt());
                traceItem.setButtonDesc(traceInfos.get(i).getButtonDesc());
                traceItem.setButtonId(traceInfos.get(i).getButtonId());
                traceItem.setCreateTime(traceInfos.get(i).getCreateTime());
                traceItem.setMobile(traceInfos.get(i).getMobile());
                traceItem.setPageDesc(traceInfos.get(i).getPageDesc());
                traceItem.setPageId(traceInfos.get(i).getPageId());
                traceItem.setDeviceType("android");
//                traceItem.setOsType();
//                traceItem.setOsVersion();
                traceItems.add(traceItem);

            }

            listTraceInfo.setList(traceItems);

            LogUtil.getInstance().d("上传数据", "" + new Gson().toJson(listTraceInfo));


//            //上传成功以后再删除
//            DaoManager.getInstance().getDaoSession().getTraceInfoDao().deleteInTx(traceInfos);
        }


    }


}
