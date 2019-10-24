package com.example.library.util;

import android.util.Log;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.library.base.BaseApplication;
import com.example.library.service.TraceWorker;

import java.util.concurrent.TimeUnit;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-09-04 17:38
 */
public class TraceWorkerUtil {

    private static OneTimeWorkRequest request;


    /**
     * 由于重复任务，最短间隔为15分钟，此处用单一任务，通过timer定时查询
     */
    public static void startWork() {

        //可约束任务
//        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();

        OneTimeWorkRequest.Builder argsWorkBuilder = new OneTimeWorkRequest.Builder(TraceWorker.class)
//                .setConstraints(constraints)
                .setInitialDelay(1, TimeUnit.SECONDS);

        request = argsWorkBuilder
                .addTag("traceWork")
                .build();

        WorkManager.getInstance(BaseApplication.getInstance())
                .enqueue(request);


    }

    /**
     * 如果是正在运行状态无法取消 等同于线程
     */
    public static void stopWork() {
        WorkManager.getInstance(BaseApplication.getInstance()).cancelAllWork();
    }
}
