package com.example.mrc.csdndemo.BackgroundTimeTask;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Calendar;

public class MoreTimeTaskService extends Service {
    private static final int INTERVAL_TIME_ONE  = 10 ;
    private static final int INTERVAL_TIME_TWO  = 15 ;
    private static final int INTERVAL_TIME_THREE  = 20 ;
    AlarmManager mAlarmManagerOne;
    AlarmManager mAlarmManagerTwo;
    AlarmManager mAlarmManagerThree;
    PendingIntent pIntentOne ;
    PendingIntent pIntentTwo ;
    PendingIntent pIntentThree ;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    Calendar mCalendar ;

    public MoreTimeTaskService() {
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
        int type = sharedPreferences.getInt("type" ,0);
        //1表示执行第一个定时任务 2，3同理 ；0表示未执行，则三个一起执行
        mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        int apiLevel = getApiLevel();

        //这里模拟后台操作
        if(type ==0){
            Handler handlerOne=new Handler(Looper.getMainLooper());
            handlerOne.post(new Runnable(){
                public void run(){
                    int countOne =sharedPreferences.getInt("countOne" ,0);
                    countOne ++ ;
                    Log.e("messages","第1个定时任务循环执行了"+ countOne +"次，执行时间为："+ System.currentTimeMillis());
                    Toast.makeText(getApplicationContext() ,"第1个定时任务循环执行了"+ countOne +"次，执行时间为："+ System.currentTimeMillis() ,Toast.LENGTH_LONG).show();
                    editor = sharedPreferences.edit();
                    editor.putInt("countOne" ,countOne);
                    editor.commit();
                }
            });
            Handler handlerTwo=new Handler(Looper.getMainLooper());
            handlerTwo.post(new Runnable(){
                public void run(){
                    int countTwo =sharedPreferences.getInt("countTwo" ,0);
                    countTwo ++ ;
                    Log.e("messages","第2个定时任务循环执行了"+ countTwo +"次，执行时间为："+ System.currentTimeMillis());
                    Toast.makeText(getApplicationContext() ,"第2个定时任务循环执行了"+ countTwo +"次，执行时间为："+ System.currentTimeMillis() ,Toast.LENGTH_LONG).show();
                    editor = sharedPreferences.edit();
                    editor.putInt("countTwo" ,countTwo);
                    editor.commit();
                }
            });
            Handler handlerThree=new Handler(Looper.getMainLooper());
            handlerThree.post(new Runnable(){
                public void run(){
                    int countThree =sharedPreferences.getInt("countThree" ,0);
                    countThree ++ ;
                    Log.e("messages","第3个定时任务循环执行了"+ countThree +"次，执行时间为："+ System.currentTimeMillis());
                    Toast.makeText(getApplicationContext() ,"第3个定时任务循环执行了"+ countThree +"次，执行时间为："+ System.currentTimeMillis() ,Toast.LENGTH_LONG).show();
                    editor = sharedPreferences.edit();
                    editor.putInt("countThree" ,countThree);
                    editor.commit();
                }
            });

            mCalendar.add(Calendar.SECOND, INTERVAL_TIME_ONE);
            //通过AlarmManager定时启动广播
            mAlarmManagerOne= (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent timeTaskIntentOne=new Intent(this, MoreAlarmReceiver.class);
            timeTaskIntentOne.putExtra("type" ,1);
            pIntentOne=PendingIntent.getBroadcast(this,1,timeTaskIntentOne ,PendingIntent.FLAG_CANCEL_CURRENT);
            if (apiLevel < Build.VERSION_CODES.KITKAT) {
                Log.d("api<19", "setExactAlarmCompat ");
                mAlarmManagerOne.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), INTERVAL_TIME_ONE, pIntentOne);
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mAlarmManagerOne.setExact(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentOne);
                }
                Log.d("19<api<23", "setExactAlarmCompat ");
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mAlarmManagerOne.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentOne);
                Log.d("api>23", "setExactAlarmCompat ");
            }

            mCalendar.add(Calendar.SECOND, INTERVAL_TIME_TWO);
            //通过AlarmManager定时启动广播
            mAlarmManagerTwo= (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent timeTaskIntentTwo=new Intent(this, MoreAlarmReceiver.class);
            timeTaskIntentTwo.putExtra("type" ,2) ;
            pIntentTwo=PendingIntent.getBroadcast(this,2,timeTaskIntentTwo ,PendingIntent.FLAG_CANCEL_CURRENT);
            if (apiLevel < Build.VERSION_CODES.KITKAT) {
                Log.d("api<19", "setExactAlarmCompat ");
                mAlarmManagerTwo.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), INTERVAL_TIME_TWO, pIntentTwo);
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mAlarmManagerTwo.setExact(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentTwo);
                }
                Log.d("19<api<23", "setExactAlarmCompat ");
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mAlarmManagerTwo.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentTwo);
                Log.d("api>23", "setExactAlarmCompat ");
            }

            mCalendar.add(Calendar.SECOND, INTERVAL_TIME_THREE);
            //通过AlarmManager定时启动广播
            mAlarmManagerThree= (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent timeTaskIntentThree=new Intent(this, MoreAlarmReceiver.class);
            timeTaskIntentThree.putExtra("type" ,3) ;
            pIntentThree=PendingIntent.getBroadcast(this,3,timeTaskIntentThree ,PendingIntent.FLAG_CANCEL_CURRENT);
            if (apiLevel < Build.VERSION_CODES.KITKAT) {
                Log.d("api<19", "setExactAlarmCompat ");
                mAlarmManagerThree.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), INTERVAL_TIME_THREE, pIntentThree);
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mAlarmManagerThree.setExact(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentThree);
                }
                Log.d("19<api<23", "setExactAlarmCompat ");
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mAlarmManagerThree.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentThree);
                Log.d("api>23", "setExactAlarmCompat ");
            }

        } else if(type ==1){
            Handler handlerOne=new Handler(Looper.getMainLooper());
            handlerOne.post(new Runnable(){
                public void run(){
                    int countOne =sharedPreferences.getInt("countOne" ,0);
                    countOne ++ ;
                    Log.e("messages","第1个定时任务循环执行了"+ countOne +"次，执行时间为："+ System.currentTimeMillis());
                    Toast.makeText(getApplicationContext() ,"第1个定时任务循环执行了"+ countOne +"次，执行时间为："+ System.currentTimeMillis() ,Toast.LENGTH_LONG).show();
                    editor = sharedPreferences.edit();
                    editor.putInt("countOne" ,countOne);
                    editor.commit();
                }
            });

            mCalendar.add(Calendar.SECOND, INTERVAL_TIME_ONE);
            //通过AlarmManager定时启动广播
            mAlarmManagerOne= (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent timeTaskIntentOne=new Intent(this, MoreAlarmReceiver.class);
            timeTaskIntentOne.putExtra("type" ,1);
            pIntentOne=PendingIntent.getBroadcast(this,1,timeTaskIntentOne ,PendingIntent.FLAG_CANCEL_CURRENT);
            if (apiLevel < Build.VERSION_CODES.KITKAT) {
                Log.d("api<19", "setExactAlarmCompat ");
                mAlarmManagerOne.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), INTERVAL_TIME_ONE, pIntentOne);
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mAlarmManagerOne.setExact(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentOne);
                }
                Log.d("19<api<23", "setExactAlarmCompat ");
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mAlarmManagerOne.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentOne);
                Log.d("api>23", "setExactAlarmCompat ");
            }
        }else if(type ==2){
            Handler handlerTwo=new Handler(Looper.getMainLooper());
            handlerTwo.post(new Runnable(){
                public void run(){
                    int countTwo =sharedPreferences.getInt("countTwo" ,0);
                    countTwo ++ ;
                    Log.e("messages","第2个定时任务循环执行了"+ countTwo +"次，执行时间为："+ System.currentTimeMillis());
                    Toast.makeText(getApplicationContext() ,"第2个定时任务循环执行了"+ countTwo +"次，执行时间为："+ System.currentTimeMillis() ,Toast.LENGTH_LONG).show();
                    editor = sharedPreferences.edit();
                    editor.putInt("countTwo" ,countTwo);
                    editor.commit();
                }
            });

            mCalendar.add(Calendar.SECOND, INTERVAL_TIME_TWO);
            //通过AlarmManager定时启动广播
            mAlarmManagerTwo= (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent timeTaskIntentTwo=new Intent(this, MoreAlarmReceiver.class);
            timeTaskIntentTwo.putExtra("type" ,2);
            pIntentTwo=PendingIntent.getBroadcast(this,2,timeTaskIntentTwo ,PendingIntent.FLAG_CANCEL_CURRENT);
            if (apiLevel < Build.VERSION_CODES.KITKAT) {
                Log.d("api<19", "setExactAlarmCompat ");
                mAlarmManagerTwo.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), INTERVAL_TIME_TWO, pIntentTwo);
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mAlarmManagerTwo.setExact(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentTwo);
                }
                Log.d("19<api<23", "setExactAlarmCompat ");
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mAlarmManagerTwo.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentTwo);
                Log.d("api>23", "setExactAlarmCompat ");
            }

        }else if(type ==3){
            Handler handlerThree=new Handler(Looper.getMainLooper());
            handlerThree.post(new Runnable(){
                public void run(){
                    int countThree =sharedPreferences.getInt("countThree" ,0);
                    countThree ++ ;
                    Log.e("messages","第3个定时任务循环执行了"+ countThree +"次，执行时间为："+ System.currentTimeMillis());
                    Toast.makeText(getApplicationContext() ,"第3个定时任务循环执行了"+ countThree +"次，执行时间为："+ System.currentTimeMillis() ,Toast.LENGTH_LONG).show();
                    editor = sharedPreferences.edit();
                    editor.putInt("countThree" ,countThree);
                    editor.commit();
                }
            });

            mCalendar.add(Calendar.SECOND, INTERVAL_TIME_THREE);
            //通过AlarmManager定时启动广播
            mAlarmManagerThree= (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent timeTaskIntentThree=new Intent(this, MoreAlarmReceiver.class);
            timeTaskIntentThree.putExtra("type" ,3);
            pIntentThree=PendingIntent.getBroadcast(this,3,timeTaskIntentThree ,PendingIntent.FLAG_CANCEL_CURRENT);
            if (apiLevel < Build.VERSION_CODES.KITKAT) {
                Log.d("api<19", "setExactAlarmCompat ");
                mAlarmManagerThree.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), INTERVAL_TIME_THREE, pIntentThree);
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mAlarmManagerThree.setExact(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentThree);
                }
                Log.d("19<api<23", "setExactAlarmCompat ");
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mAlarmManagerThree.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pIntentThree);
                Log.d("api>23", "setExactAlarmCompat ");
            }

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

        Handler handler=new Handler(Looper.getMainLooper());
        handler.post(new Runnable(){
            public void run() {
                mAlarmManagerOne.cancel(pIntentOne);
                Toast.makeText(getApplicationContext() ,"停止了第1个后台循环任务，停止执行时间为："
                        + System.currentTimeMillis() , Toast.LENGTH_LONG ).show();

                mAlarmManagerTwo.cancel(pIntentTwo);
                Toast.makeText(getApplicationContext() ,"停止了第2个后台循环任务，停止执行时间为："
                        + System.currentTimeMillis() , Toast.LENGTH_LONG ).show();

                mAlarmManagerThree.cancel(pIntentThree);
                Toast.makeText(getApplicationContext() ,"停止了第3个后台循环任务，停止执行时间为："
                        + System.currentTimeMillis() , Toast.LENGTH_LONG ).show();
            }
        });
        editor.putInt("type" ,0) ;
        editor.commit();
        Log.e("messages","停止后台循环任务，停止执行时间为："+ System.currentTimeMillis());
    }

}
