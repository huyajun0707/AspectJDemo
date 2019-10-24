package com.example.library.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-09-04 19:26
 */
public class ThreadPoolUtil {

    private static ThreadPoolUtil mThreadPoolUtil;
    private  ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolUtil() {
        mThreadPoolExecutor = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));

    }

    public static ThreadPoolUtil getInstance() {
        if (mThreadPoolUtil == null) {
            synchronized (ThreadPoolUtil.class) {
                if (mThreadPoolUtil == null)
                    mThreadPoolUtil = new ThreadPoolUtil();
            }
        }
        return mThreadPoolUtil;

    }


    public void execute(Runnable runnable){

        mThreadPoolExecutor.execute(runnable);

    }




}
