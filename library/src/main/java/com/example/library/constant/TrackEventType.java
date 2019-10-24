package com.example.library.constant;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-09-05 15:59
 * @depiction ：
 */



public enum TrackEventType {
    LAUNCH(1),
    VISIT(2),
    CLICK(3);

    private int mContent;

    TrackEventType(int content) {
        this.mContent = content;
    }

    public int getContent() {
        return mContent;
    }

    public static String autoTrackEventNameFromEventType(TrackEventType eventType) {
        if (!(eventType instanceof TrackEventType)) {
            return null;
        }

        switch (eventType) {
            case LAUNCH:
                return "launch";
            case VISIT:
                return "visit";
            case CLICK:
                return "click";
            default:
                break;
        }

        return null;
    }


}