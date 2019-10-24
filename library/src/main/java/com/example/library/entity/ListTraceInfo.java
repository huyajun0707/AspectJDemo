package com.example.library.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-09-04 20:17
 */
public class ListTraceInfo implements Parcelable {

    private List<TraceItem> list;

    public List<TraceItem> getList() {
        return list;
    }

    public void setList(List<TraceItem> list) {
        this.list = list;
    }

    public static class TraceItem implements Parcelable {


        /**
         * appChannel : string
         * appVersion : string
         * buryingPointType : string
         * busiExt : string
         * buttonDesc : string
         * buttonId : string
         * createTime : 2019-09-04T11:47:57.619Z
         * deviceIdAndroid : string
         * deviceIdIdfa : string
         * deviceIdImei : string
         * deviceIdMac : string
         * deviceIdUuid : string
         * deviceType : string
         * mobile : string
         * osType : string
         * osVersion : string
         * pageDesc : string
         * pageId : string
         */

        private String appChannel;
        private String appVersion;
        private String buryingPointType;
        private String busiExt;
        private String buttonDesc;
        private String buttonId;
        private String createTime;
        private String deviceIdAndroid;
        private String deviceIdIdfa;
        private String deviceIdImei;
        private String deviceIdMac;
        private String deviceIdUuid;
        private String deviceType;
        private String mobile;
        private String osType;
        private String osVersion;
        private String pageDesc;
        private String pageId;

        public String getAppChannel() {
            return appChannel;
        }

        public void setAppChannel(String appChannel) {
            this.appChannel = appChannel;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getBuryingPointType() {
            return buryingPointType;
        }

        public void setBuryingPointType(String buryingPointType) {
            this.buryingPointType = buryingPointType;
        }

        public String getBusiExt() {
            return busiExt;
        }

        public void setBusiExt(String busiExt) {
            this.busiExt = busiExt;
        }

        public String getButtonDesc() {
            return buttonDesc;
        }

        public void setButtonDesc(String buttonDesc) {
            this.buttonDesc = buttonDesc;
        }

        public String getButtonId() {
            return buttonId;
        }

        public void setButtonId(String buttonId) {
            this.buttonId = buttonId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDeviceIdAndroid() {
            return deviceIdAndroid;
        }

        public void setDeviceIdAndroid(String deviceIdAndroid) {
            this.deviceIdAndroid = deviceIdAndroid;
        }

        public String getDeviceIdIdfa() {
            return deviceIdIdfa;
        }

        public void setDeviceIdIdfa(String deviceIdIdfa) {
            this.deviceIdIdfa = deviceIdIdfa;
        }

        public String getDeviceIdImei() {
            return deviceIdImei;
        }

        public void setDeviceIdImei(String deviceIdImei) {
            this.deviceIdImei = deviceIdImei;
        }

        public String getDeviceIdMac() {
            return deviceIdMac;
        }

        public void setDeviceIdMac(String deviceIdMac) {
            this.deviceIdMac = deviceIdMac;
        }

        public String getDeviceIdUuid() {
            return deviceIdUuid;
        }

        public void setDeviceIdUuid(String deviceIdUuid) {
            this.deviceIdUuid = deviceIdUuid;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOsType() {
            return osType;
        }

        public void setOsType(String osType) {
            this.osType = osType;
        }

        public String getOsVersion() {
            return osVersion;
        }

        public void setOsVersion(String osVersion) {
            this.osVersion = osVersion;
        }

        public String getPageDesc() {
            return pageDesc;
        }

        public void setPageDesc(String pageDesc) {
            this.pageDesc = pageDesc;
        }

        public String getPageId() {
            return pageId;
        }

        public void setPageId(String pageId) {
            this.pageId = pageId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.appChannel);
            dest.writeString(this.appVersion);
            dest.writeString(this.buryingPointType);
            dest.writeString(this.busiExt);
            dest.writeString(this.buttonDesc);
            dest.writeString(this.buttonId);
            dest.writeString(this.createTime);
            dest.writeString(this.deviceIdAndroid);
            dest.writeString(this.deviceIdIdfa);
            dest.writeString(this.deviceIdImei);
            dest.writeString(this.deviceIdMac);
            dest.writeString(this.deviceIdUuid);
            dest.writeString(this.deviceType);
            dest.writeString(this.mobile);
            dest.writeString(this.osType);
            dest.writeString(this.osVersion);
            dest.writeString(this.pageDesc);
            dest.writeString(this.pageId);
        }

        public TraceItem() {
        }

        protected TraceItem(Parcel in) {
            this.appChannel = in.readString();
            this.appVersion = in.readString();
            this.buryingPointType = in.readString();
            this.busiExt = in.readString();
            this.buttonDesc = in.readString();
            this.buttonId = in.readString();
            this.createTime = in.readString();
            this.deviceIdAndroid = in.readString();
            this.deviceIdIdfa = in.readString();
            this.deviceIdImei = in.readString();
            this.deviceIdMac = in.readString();
            this.deviceIdUuid = in.readString();
            this.deviceType = in.readString();
            this.mobile = in.readString();
            this.osType = in.readString();
            this.osVersion = in.readString();
            this.pageDesc = in.readString();
            this.pageId = in.readString();
        }

        public static final Creator<TraceItem> CREATOR = new Creator<TraceItem>() {
            @Override
            public TraceItem createFromParcel(Parcel source) {
                return new TraceItem(source);
            }

            @Override
            public TraceItem[] newArray(int size) {
                return new TraceItem[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.list);
    }

    public ListTraceInfo() {
    }

    protected ListTraceInfo(Parcel in) {
        this.list = in.createTypedArrayList(TraceItem.CREATOR);
    }

    public static final Creator<ListTraceInfo> CREATOR = new Creator<ListTraceInfo>() {
        @Override
        public ListTraceInfo createFromParcel(Parcel source) {
            return new ListTraceInfo(source);
        }

        @Override
        public ListTraceInfo[] newArray(int size) {
            return new ListTraceInfo[size];
        }
    };
}
