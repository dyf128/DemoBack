package com.dmeos.test.androidart.base;

import android.app.Application;
import android.content.Context;


public class AppApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext(){
        return sContext;
    }

}
