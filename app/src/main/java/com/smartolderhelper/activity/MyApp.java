package com.smartolderhelper.activity;

import android.app.Application;
import android.content.Context;

/**
 * @auther gbh
 * Created on 2017/4/9.
 */

public class MyApp  extends Application{
    public static Context context;
    @Override
    public void onCreate(){
        super.onCreate();
        context = this;
    }
}
