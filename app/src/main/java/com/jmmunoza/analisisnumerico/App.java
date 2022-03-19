package com.jmmunoza.analisisnumerico;


import android.app.Application;
import android.content.Context;

public class App  extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getContext(){
        return appContext;
    }

}