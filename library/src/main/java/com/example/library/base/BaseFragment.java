package com.example.library.base;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.library.entity.TraceInfo;
import com.example.library.util.LogUtil;

import org.json.JSONObject;


/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-09-23 19:52
 * @depiction ：
 */
public abstract class BaseFragment extends Fragment {
    public static final String TAG = "BaseFragment";

    protected boolean isViewPrepared = false;//标识fragment视图已经初始化完毕
    protected boolean hasFetchData = false;//标识已经触发过懒加载数据

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        LogUtil.getInstance().d(TAG, "onResume: ");
        super.onResume();
        if (getUserVisibleHint() && isViewPrepared) {
            setUserVisibleHintTrackData(this,true, getTrackProperties());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
    }

    public String getTitle() {
        return null;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtil.getInstance().d(TAG, "onHiddenChanged: " + hidden);
        if (!hidden)
            setUserVisibleHintTrackData(this, true, getTrackProperties());
        else
            setUserVisibleHintTrackData(this, false, getTrackProperties());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.getInstance().d(TAG, "setUserVisibleHint: " + isVisibleToUser);
        if (isVisibleToUser) {
            if (getUserVisibleHint() && isViewPrepared) {
                setUserVisibleHintTrackData(this, true,getTrackProperties());
            }
        }else if (isViewPrepared) {
            setUserVisibleHintTrackData(this, false,getTrackProperties());
        }
//            Log.d("--->fragment" + type, "onHiddenChanged:" + isVisibleToUser + "");

    }

    /**
     * 获取埋点信息
     *
     * @return
     */
    protected TraceInfo getTrackProperties() {
        return null;
    }

    public void setUserVisibleHintTrackData(Fragment fragment, boolean isShow,TraceInfo pageInfo) {

    }


}
