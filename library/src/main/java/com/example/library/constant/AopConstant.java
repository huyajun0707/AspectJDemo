package com.example.library.constant;

/**
 * =========================================================
 *
 * @author :   HuYajun     <13426236872@163.com>
 * @version :
 * @date :   2019/4/18 10:31
 * @description :
 * =========================================================
 */
public class AopConstant {

    public static final String DB_NAME = "trace_dp";

    public static final String CLICK_METHOD = "execution(* android.view.View.OnClickListener.onClick(..))  && target(Object) && this(Object)";

    public static final String FRAGMENT_METHOD = "execution(* com.example.library.base.BaseFragment.setUserVisibleHintTrackData(..))";

    public static final String POPUVIEW_METHOD = "execution(* *(..)) && @annotation(com.example.library.entity.PopupViewTrack)";

    public static final String INPUT_METHOD = "execution(* com.example.aspectdemo.widget.SupEditText.OnFinishComposingListener.finishComposing(..))";

    public static final long REPEAT_TIME = 1000 * 10;

    public static class DateFormat {
        public static final String DATE_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss";
        public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";

        public static final String GMT8 = "GMT+8:00";


        public static final String DATE = "yyyy-MM-dd HH:mm:ss.SSS";

    }

    public static class AppStartAndEndKey {

        public static final String APP_STARTED = "app_started";
        public static final String APP_PAUSED_TIME = "app_paused_time";
        public static final String APP_END_STATE = "app_end_state";
    }


}
