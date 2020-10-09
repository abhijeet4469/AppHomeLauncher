package com.acs.sdk;

import com.acs.sdk.ModalAppInfo;

import java.util.Comparator;

public class AppInfoComparator implements Comparator<ModalAppInfo> {
    @Override
    public int compare(ModalAppInfo obj1, ModalAppInfo obj2) {
        return obj1.getAppName().compareTo(obj2.getAppName());
    }
}
