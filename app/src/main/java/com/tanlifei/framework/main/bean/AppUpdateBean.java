package com.tanlifei.framework.main.bean;

import java.io.Serializable;

/**
 * Created by tanlifei on 16/8/19.
 */
public class AppUpdateBean implements Serializable {
    /**
     * version_code : 12
     * version_name : 1.0.1
     * name : 灵犀语音助手
     * url : http://gh-game.oss-cn-hangzhou.aliyuncs.com/1435814701749842.apk
     * desc : 灵犀Android
     */

    private int version_code;
    private String version_name;
    private String name;
    private String url;
    private String desc;

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
