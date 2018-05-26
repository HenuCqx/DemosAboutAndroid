package com.example.mrc.csdndemo.SetAlarmAndTimerByGoogleVoiceAction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class GoogleVoiceActionSetAlarmActivity extends Activity {
    int hour ;
    int minutes;
    ArrayList<Integer> day =new ArrayList<>();
    private GoogleApiClient mClient;
    private Uri mUrl;
    private String mTitle;
    private String mDescription;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_google_voice_action_set_alarm);
        //通过语音去设置闹钟的Activity，应该设置为无界面的，不会出现页面跳转问题
        //setContentView(R.layout.activity_google_voice_action_set_alarm);
        Intent intent = getIntent();
        hour=intent.getIntExtra(AlarmClock.EXTRA_HOUR ,Integer.MAX_VALUE); //获取传过来的小时数
        minutes =intent.getIntExtra(AlarmClock.EXTRA_MINUTES,Integer.MAX_VALUE);  //获取传过来的分钟数
        message =intent.getStringExtra(AlarmClock.EXTRA_MESSAGE);      //获取传过来的信息
        //传回来一个Integer的列表，其中1表示周日，2表示周一，3表示周二，4表示周三，5表示周四，六表示周五，7表示周六
        day =intent.getIntegerArrayListExtra(AlarmClock.EXTRA_DAYS);     //获取传过来的闹钟重复时间
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        mUrl = Uri.parse("http://com.example.mrc.csdndemo");  //app的包名
        mTitle = "Set AlarmClock";
        mDescription = "Set "+ hour +" :" + minutes + " AlarmClock";
        Toast.makeText(this,"hour:"+hour+"  minutes:"+minutes + "days:" + day + "   message:"
                + message ,Toast.LENGTH_LONG).show();
        //设置闹钟的一些操作，省略。。。
    }

    public Action getAction() {
        Thing object = new Thing.Builder()
                .setName(mTitle)
                .setDescription(mDescription)
                .setUrl(mUrl)
                .build();

        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getAction());
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.finish();
    }

    @Override
    public void onStop() {
        AppIndex.AppIndexApi.end(mClient, getAction());
        mClient.disconnect();
        super.onStop();
    }
}
