package com.example.mrc.csdndemo.BackgroundTimeTask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class MoreAlarmReceiver extends BroadcastReceiver {

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    @Override
    public void onReceive(Context context, Intent intent) {
        int type =intent.getIntExtra("type" ,0) ;
        Log.e("cqx" ,type+"");
        sharedPreferences =context.getSharedPreferences("com.example.mrc.csdnDemo" , 0);
        editor =sharedPreferences.edit();
        sharedPreferences.edit();
        if(type ==1){
            editor.putInt("type" ,1);
            editor.commit();
        }else if(type ==2){
            editor.putInt("type" ,2);
            editor.commit();
        }else if(type ==3){
            editor.putInt("type" ,3);
            editor.commit();
        }else {
            editor.putInt("type" ,0);
            editor.commit();
        }
        Intent i = new Intent(context, MoreTimeTaskService.class);
        context.startService(i);
    }
}
