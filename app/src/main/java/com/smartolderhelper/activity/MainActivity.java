package com.smartolderhelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.smartolderhelper.R;
import com.smartolderhelper.daemon.FileUtils;
import com.smartolderhelper.daemon.NativeRuntime;

public class MainActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.btn_start)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_end)).setOnClickListener(this);
        initService();
    }

    private void initService(){
        Toast.makeText(this,NativeRuntime.getInstance().stringFromJNI(), Toast.LENGTH_SHORT).show();
        String executable = "libhelper.so";
        String aliasfile = "helper";
        String parafind = "/data/data/" + getPackageName() + "/" + aliasfile;
        String retx = "false";
        NativeRuntime.getInstance().RunExecutable(getPackageName(), executable, aliasfile, getPackageName() + "/com.smartolderhelper.daemon.HostMonitor");
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_start:
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        try{
                            NativeRuntime.getInstance().startService(getPackageName()+"/com.smartolderhelper.daemon.HostMonitor",FileUtils.createRootPath());
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.btn_end:
                try{
                    NativeRuntime.getInstance().stopService();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }
}
