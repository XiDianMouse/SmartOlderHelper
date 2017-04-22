package com.smartolderhelper.daemon;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.List;

/**
 * @auther gbh
 * Created on 2017/4/9.
 */

public class PhoneStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context,Intent intent){
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Toast.makeText(context,"开机完成,正在开启服务",Toast.LENGTH_SHORT).show();
            NativeRuntime.getInstance().startService(context.getPackageName()+"/com.smartolderhelper.daemon.HostMonitor",FileUtils.createRootPath());

        }
        else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            judgeService(context);
        }
    }

    private void judgeService(Context context){
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = activityManager.getRunningServices(100);
        for(int i=0;i<runningServiceInfos.size();i++)
        {
            ActivityManager.RunningServiceInfo runningServiceInfo = runningServiceInfos.get(i);
            if("com.smartolderhelper.daemon.HostMonitor".equals(runningServiceInfo.service.getClassName()))
            {
                Toast.makeText(context,"服务已开启",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(context,"正在开启服务...",Toast.LENGTH_SHORT).show();
        NativeRuntime.getInstance().startService(context.getPackageName()+"/com.smartolderhelper.daemon.HostMonitor",FileUtils.createRootPath());
    }
}
