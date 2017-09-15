package com.lijiankun24.androidpractice;

import android.app.Application;

import com.lijiankun24.androidpractice.util.storage.CustomStorageManager;

/**
 * MyApplication.java
 * <p>
 * Created by lijiankun on 17/9/14.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CustomStorageManager.getInstance().init(this);
    }
}
