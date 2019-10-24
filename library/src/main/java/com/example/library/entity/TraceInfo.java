package com.example.library.entity;

import com.example.library.BuildConfig;
import com.example.library.constant.TrackEventType;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author ： HuYajun <huyajun0707@gmail.com>
 * @version ： 1.0
 * @date ： 2019-09-03 15:10
 */

@Entity
public class TraceInfo {

    @Override
    public String toString() {
        return "TraceInfo{" +
                "id=" + id +
                ", appVersion='" + appVersion + '\'' +
                ", buryingPointType='" + buryingPointType + '\'' +
                ", busiExt='" + busiExt + '\'' +
                ", buttonDesc='" + buttonDesc + '\'' +
                ", buttonId='" + buttonId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", mobile='" + mobile + '\'' +
                ", pageDesc='" + pageDesc + '\'' +
                ", pageId='" + pageId + '\'' +
                '}';
    }

    /**
     * appChannel (string, optional): APP包渠道 ,
     * appVersion (string, optional): APP版本号 ,
     * buryingPointType (string, optional): 埋点类型 ,
     * busiExt (string, optional): 业务扩展信息 json方式存储 ,
     * buttonDesc (string, optional): 按钮的描述 ,
     * buttonId (string, optional): 按钮的埋点Id ,
     * createTime (string, optional): 数据生成时间 ,
     * deviceIdAndroid (string, optional): id_android ,
     * deviceIdIdfa (string, optional): IDFA ,
     * deviceIdImei (string, optional): IMEI ,
     * deviceIdMac (string, optional): mac ,
     * deviceIdUuid (string, optional): id_uuid ,
     * deviceType (string, optional): 设备类型 ios android H5 ,
     * mobile (string, optional): 手机号码 ,
     * osType (string, optional): 操作系统类型 ,
     * osVersion (string, optional): 操作系统版本号 ,
     * pageDesc (string, optional): 页面的描述 ,
     * pageId (string, optional): 埋点页面对应的编码
     */
    @Id(autoincrement = true)
    private Long id;
    private String appVersion = BuildConfig.VERSION_NAME;
    private String buryingPointType;
    private String busiExt;
    private String buttonDesc;
    private String buttonId;
    private String createTime;
    private String mobile;
    private String pageDesc;
    private String pageId;
    private String inputId;
    private String input_desc;
    private String input_value;
    private String choose_id;
    private String choose_desc;
    private String choose_value;
    private String start_time;
    private String end_time;
    private long duration;

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getInputId() {
        return inputId;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public String getInput_desc() {
        return input_desc;
    }

    public void setInput_desc(String input_desc) {
        this.input_desc = input_desc;
    }

    public String getInput_value() {
        return input_value;
    }

    public void setInput_value(String input_value) {
        this.input_value = input_value;
    }

    public String getChoose_id() {
        return choose_id;
    }

    public void setChoose_id(String choose_id) {
        this.choose_id = choose_id;
    }

    public String getChoose_desc() {
        return choose_desc;
    }

    public void setChoose_desc(String choose_desc) {
        this.choose_desc = choose_desc;
    }

    public String getChoose_value() {
        return choose_value;
    }

    public void setChoose_value(String choose_value) {
        this.choose_value = choose_value;
    }

    @Generated(hash = 378866630)
    public TraceInfo(Long id, String appVersion, String buryingPointType, String busiExt,
            String buttonDesc, String buttonId, String createTime, String mobile, String pageDesc,
            String pageId, String inputId, String input_desc, String input_value, String choose_id,
            String choose_desc, String choose_value, String start_time, String end_time,
            long duration) {
        this.id = id;
        this.appVersion = appVersion;
        this.buryingPointType = buryingPointType;
        this.busiExt = busiExt;
        this.buttonDesc = buttonDesc;
        this.buttonId = buttonId;
        this.createTime = createTime;
        this.mobile = mobile;
        this.pageDesc = pageDesc;
        this.pageId = pageId;
        this.inputId = inputId;
        this.input_desc = input_desc;
        this.input_value = input_value;
        this.choose_id = choose_id;
        this.choose_desc = choose_desc;
        this.choose_value = choose_value;
        this.start_time = start_time;
        this.end_time = end_time;
        this.duration = duration;
    }

    @Generated(hash = 1627636996)
    public TraceInfo() {
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public static TraceInfo newInstance(TrackEventType eventType) {
        // 1 appStart 2 pv   3 onclick
        TraceInfo traceInfo = new TraceInfo();
        traceInfo.setBuryingPointType(TrackEventType.autoTrackEventNameFromEventType(eventType));
        return traceInfo;
    }

}
