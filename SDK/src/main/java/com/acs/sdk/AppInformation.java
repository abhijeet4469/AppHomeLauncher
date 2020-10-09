package com.acs.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppInformation {

    public static AppInformation appInfoSingliton;

    // can make it synchronized for thread safety
    public synchronized static AppInformation getInstance(){

        // if any instance is not created, then create a new instance
        if (appInfoSingliton == null){
            appInfoSingliton = new AppInformation();
        }

        return appInfoSingliton;
    }

    // keep it private so no object can not be created externally
    private AppInformation(){
        if (appInfoSingliton != null){
            throw new RuntimeException("use getInstance() to create instance of the class");
        }
    }


    // Get list of all installed application
    public List<ModalAppInfo> getInstalledApps(Context context) {

        List<ModalAppInfo> listAllApp = new ArrayList<>();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);

        for (PackageInfo p : packs) {

            // can activate/deactivate this line to remove/show system applications
            if (!isSystemPackage(p))
            {
                // Icon, App name, Package name, Main Activity class name, Version code, and Version name.
                String className = "";
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(p.applicationInfo.packageName);
                if (intent != null) {
                    String[] name = intent.getComponent().getClassName().split("\\.");
                    className = name[name.length-1];
                }

                listAllApp.add(
                        new ModalAppInfo(p.applicationInfo.loadIcon(context.getPackageManager()),
                                p.applicationInfo.loadLabel(context.getPackageManager()).toString(),
                                p.applicationInfo.packageName,
                                className,
                                p.versionName,
                                android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P?p.getLongVersionCode():p.versionCode)
                );
            }
        }

        // sort in ascending order
        Collections.sort(listAllApp, new AppInfoComparator());
        return listAllApp;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }
}
