package com.example.aspectdemo;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.library.base.BaseFragment;
import com.example.library.constant.TrackEventType;
import com.example.library.entity.PopupViewTrack;
import com.example.library.entity.TraceInfo;
import com.example.library.util.LogUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment implements View.OnClickListener {

    private int type;
    private View view;
    private TextView tvContent;
    private Button btClick1,btClick2;
    private TraceInfo traceInfo;



    public static BlankFragment newInstance(int type) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt("args", type);
        fragment.setArguments(args);
        return fragment;
    }

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public String getTitle() {
        return "Fragment"+type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getArguments() != null) {
            type = getArguments().getInt("args", 1);
        }
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        tvContent = view.findViewById(R.id.tv_content);
        tvContent.setText("当前页面：Fragment"+String.valueOf(type));
        btClick1 = view.findViewById(R.id.btClick1);
        btClick2 = view.findViewById(R.id.btClickd2);
        btClick1.setOnClickListener(this);
        btClick2.setOnClickListener(this);

        traceInfo = TraceInfo.newInstance(TrackEventType.CLICK);
        traceInfo.setButtonId("agree_bt");
        traceInfo.setButtonDesc("点击同意按钮");
        btClick1.setTag(R.string.tag_view_properties,traceInfo);
        return view;
    }




    @Override
    protected TraceInfo getTrackProperties() {
        TraceInfo pageInfo = TraceInfo.newInstance(TrackEventType.VISIT);
        pageInfo.setPageId("home_fragment");
        pageInfo.setPageDesc(getTitle());

        return pageInfo;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        Log.d("---->fragment:"+type,"onHiddenChanged:"+hidden+"");

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btClick1:
                break;
            case R.id.btClickd2:

                startPopuWindow();


                break;
        }


    }

    @PopupViewTrack(trackEventType = "visit",pageId = "privacy_page",pageDesc = "隐私协议页面")
    private void startPopuWindow() {

        LogUtil.getInstance().d("---->","弹出弹窗");
    }


}
