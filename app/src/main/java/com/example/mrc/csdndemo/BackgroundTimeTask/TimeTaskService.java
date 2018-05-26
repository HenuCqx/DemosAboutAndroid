package com.example.mrc.csdndemo.BackgroundTimeTask;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Calendar;

public class TimeTaskService extends Service {

    private static final int INTERVAL_TIME  = 10 ;
    AlarmManager alarmManager ;
    PendingIntent pIntent ;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    Calendar mCalendar ;
    public TimeTaskService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sharedPreferences =getApplicationContext().getSharedPreferences("com.example.mrc.csdnDemo" , 0);
        sharedPreferences.edit();
        //这里模拟后台操作
        Handler handler=new Handler(Looper.getMainLooper());
        handler.post(new Runnable(){
            public void run(){
                int count =sharedPreferences.getInt("count" ,0);
                count ++ ;
                Log.e("messages","循环执行了"+ count +"次定时任务，执行时间为："+ System.currentTimeMillis());
                Toast.makeText(getApplicationContext() ,"循环执行了"+ count +"次定时任务，执行时间为："+ System.currentTimeMillis() ,Toast.LENGTH_LONG).show();
                editor = sharedPreferences.edit();
                editor.putInt("count" ,count);
                editor.commit();
            }
        });

        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.add(Calendar.SECOND, INTERVAL_TIME);
        //通过AlarmManager定时启动广播
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent timeTaskIntent=new Intent(this, AlarmReceiver.class);
        pIntent=PendingIntent.getBroadcast(this,0,timeTaskIntent ,PendingIntent.FLAG_CANCEL_CURRENT);
        int apiLevel = getApiLevel();
        if (apiLevel < Build.VERSION_CODES.KITKAT) {
            Log.d("api<19", "setExactAlarmCompat ");
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), INTERVAL_TIME, pIntent);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntent);
            }
            Log.d("19<api<23", "setExactAlarmCompat ");
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntent);
            Log.d("api>23", "setExactAlarmCompat ");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public static int getApiLevel() {
        try {
            Field f = Build.VERSION.class.getField("SDK_INT");
            f.setAccessible(true);
            return f.getInt(null);
        } catch (Throwable e) {
            return 3;
        }
    }
    @Override
    public void onDestroy(){
        alarmManager.cancel(pIntent);
        Handler handler=new Handler(Looper.getMainLooper());
        handler.post(new Runnable(){
            public void run() {
                Toast.makeText(getApplicationContext() ,"停止后台循环任务，停止执行时间为："
                                + System.currentTimeMillis() , Toast.LENGTH_LONG ).show();
            }
        });
        Log.e("messages","停止后台循环任务，停止执行时间为："+ System.currentTimeMillis());
    }

}
