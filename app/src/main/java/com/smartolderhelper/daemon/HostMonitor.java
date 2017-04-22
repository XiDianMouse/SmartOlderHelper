package com.smartolderhelper.daemon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @auther gbh
 * Created on 2017/4/9.
 */

public class HostMonitor extends Service{
    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();
        Log.e("mydaemondemo","HostMonitor:onCreate!I cant not be Killed!");
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.e("mydeamondemo","HostMonitor:onStartCommand!I can not be Killed!");
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public IBinder onBind(Intent arg0){
        return null;
    }
}
