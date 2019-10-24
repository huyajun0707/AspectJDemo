package com.example.library.trace;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.library.constant.AopConstant;
import com.example.library.R;
import com.example.library.entity.PopupViewTrack;
import com.example.library.entity.TraceInfo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-08-29 17:35
 * @depiction: AOP处理类
 */
@Aspect
public class TraceAspect {

    private Map<Fragment, Long> durationMap = new WeakHashMap<>();
    private Map<Fragment, Long> resumeTimeMap = new WeakHashMap<>();


    /**
     * 对按钮点击事件做埋点处理
     *
     * @param joinPoint
     * @throws Throwable
     */
    @After(AopConstant.CLICK_METHOD)
    public void OnClickListener(JoinPoint joinPoint) throws Throwable {
        Object[] objects = joinPoint.getArgs();
        View view = objects.length == 0 ? null : (Button) objects[0];
        TraceInfo traceInfo = (TraceInfo) view.getTag(R.string.tag_view_properties);
        if (traceInfo != null) {
            TraceDataPrivate.insertTraceInfo(traceInfo);
        }

    }


    /**
     * 对Fragment页面浏览做埋点处理
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around(AopConstant.FRAGMENT_METHOD)
    public void onFragmentUserVisible(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objects = joinPoint.getArgs();
        Fragment fragment = objects.length == 0 ? null : (Fragment) objects[0];
        boolean isShow = false;
        if (objects.length > 2) {
            isShow = (boolean) objects[1];
            TraceInfo traceInfo = (TraceInfo) objects[2];
            Log.e("--->", "onFragmentUserVisible: " + isShow);
            if (isShow) {
                durationMap.put(fragment, 0L);
                resumeTimeMap.put(fragment, System.currentTimeMillis());
                traceInfo.setStart_time(String.valueOf(resumeTimeMap.get(fragment)));
            } else {
                durationMap.put(fragment, durationMap.get(fragment)
                        + (System.currentTimeMillis() - resumeTimeMap.get(fragment)));
                long duration = durationMap.get(fragment);
                if (duration > 0) {
                    Log.e("--->duration", fragment.getClass().getName() + "+" + duration);
                    traceInfo.setDuration(duration);
                }
                traceInfo.setEnd_time(String.valueOf(System.currentTimeMillis()));
                resumeTimeMap.remove(fragment);
                durationMap.remove(fragment);

            }
            TraceDataPrivate.insertTraceInfo(traceInfo);
        }


        joinPoint.proceed();
    }


    @Pointcut(AopConstant.POPUVIEW_METHOD)
    private void popuViewPoint() {
    }

    @After("popuViewPoint()")
    public void popuViewEvent(JoinPoint joinPoint) {
        try {
            PopupViewTrack popuViewTracek = null;
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            popuViewTracek = method.getAnnotation(PopupViewTrack.class);
            if (popuViewTracek == null) {
                Method targetMethod = joinPoint.getTarget().getClass().getDeclaredMethod(methodSignature.getName(), methodSignature.getMethod().getParameterTypes());
                popuViewTracek = targetMethod.getAnnotation(PopupViewTrack.class);
            }
            String trackEventType = popuViewTracek.trackEventType();
            String pageId = popuViewTracek.pageId();
            String pageDesc = popuViewTracek.pageDesc();
            TraceInfo traceInfo = new TraceInfo();
            traceInfo.setBuryingPointType(trackEventType);
            traceInfo.setPageId(pageId);
            traceInfo.setPageDesc(pageDesc);
            TraceDataPrivate.insertTraceInfo(traceInfo);

        } catch (Exception e) {

        }


    }

    @After(AopConstant.INPUT_METHOD)
    public void inputEvent(JoinPoint joinPoint) {
        try {
            Object[] objects = joinPoint.getArgs();
            EditText editText = objects.length == 0 ? null : (EditText) objects[0];
            if (editText != null) {
                Log.e("---->", "inputEvent: " + editText.getText().toString());
            }
        } catch (Exception e) {

        }

    }

}
