package com.example.mrc.csdndemo.ServiceDemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    //使用startService的方式启动Service才会执行该方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Context context = getApplicationContext();
        Toast.makeText(context ,"开启了普通的Service",Toast.LENGTH_SHORT);
        return super.onStartCommand(intent, flags, startId);
    }

    //使用OnBind的方法启动服务才会执行该方法
    @Override
    public IBinder onBind(Intent intent) {
        Context context = getApplicationContext();
        Toast.makeText(context ,"开启了可交互的Service",Toast.LENGTH_SHORT);

        return new MyBind();
    }

    //使用OnBind的方法启动服务才会执行该方法
    @Override
    public boolean onUnbind(Intent intent) {
        Context context = getApplicationContext();
        Toast.makeText(context ,"解绑了可交互的Service",Toast.LENGTH_SHORT);
        return super.onUnbind(intent);
    }

    /**
     * 返回前台的MyBind对象
     * @author  cqx
     * create at 2018/8/6 12:12
     */
    class MyBind extends Binder{
        public void showTip(){
            System.out.println("我是来此服务的提示");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
