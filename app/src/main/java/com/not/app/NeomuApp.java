package com.not.app;


import android.app.Application;

public class NeomuApp extends Application {
    private static NeomuApp mApp;
    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;
    }

    public static NeomuApp getApp() {
        return mApp;
    }
}
