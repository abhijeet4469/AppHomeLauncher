package com.acs.sdk;

import android.graphics.drawable.Drawable;

public class ModalAppInfo {

    private Drawable icon;
    private String appName, packages, className, versionName;
    private long versionCode;

    public ModalAppInfo(Drawable icon, String appName, String packages, String className, String versionName, long versionCode){
        this.icon= icon;
        this.appName= appName;
        this.packages= packages;
        this.className= className;
        this.versionName= versionName;
        this.versionCode= versionCode;
    }
    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(long versionCode) {
        this.versionCode = versionCode;
    }

}
