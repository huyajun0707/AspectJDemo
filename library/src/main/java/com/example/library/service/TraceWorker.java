package com.example.library.service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.library.constant.AopConstant;
import com.example.library.trace.TraceDataPrivate;
import com.example.library.util.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author      ： HuYajun <huyajun0707@gmail.com>
 * @version     ： 1.0
 * @date        ： 2019-09-04 19:29
 * @depiction   ： 负责后台定时上传的任务类，不适用于立即执行任务
 */
public class TraceWorker extends Worker {
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            LogUtil.getInstance().d("TraceWorker","重复任务："+getId());
//            TraceDataPrivate.uploadTraceData();
        }
    };

    public TraceWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {
        if (timer != null)
            timer.schedule(task, 0, AopConstant.REPEAT_TIME);

        return Result.success();
    }

    @Override
    public void onStopped() {
        LogUtil.getInstance().d("TraceWorker", "任务onStop");
        super.onStopped();
        task.cancel();
        timer.cancel();
        task = null;
        timer = null;
    }


}
