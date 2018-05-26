package com.example.mrc.csdndemo.BackgroundTimeTask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class AlarmReceiver extends BroadcastReceiver {
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, TimeTaskService.class);
        context.startService(i);
    }

}
