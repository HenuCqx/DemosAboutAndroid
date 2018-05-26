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

public class GoogleVoiceActionSetTimerActivity extends Activity {
    int timerSecond ;
    private GoogleApiClient mClient;
    private Uri mUrl;
    private String mTitle;
    private String mDescription;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_google_voice_action_set_timer);
        //无界面
        //setContentView(R.layout.activity_google_voice_action_set_timer);
        Intent intent = getIntent();
        timerSecond=intent.getIntExtra(AlarmClock.EXTRA_LENGTH ,Integer.MAX_VALUE); //获取计时器的秒数
        message =intent.getStringExtra(AlarmClock.EXTRA_MESSAGE);  //获取计时器的信息（一般为null，可能是谷歌语音返回是空值）
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        mUrl = Uri.parse("http://com.example.mrc.csdndemo");  //app的包名
        mTitle = "Set AlarmClock";
        mDescription = "Set "+ timerSecond +" :" + timerSecond + " Timer";
        Toast.makeText(this,"timerSecond:"+timerSecond+"   message:"
                + message ,Toast.LENGTH_LONG).show();
        if(timerSecond== Integer.MAX_VALUE){    //如果谷歌语音传过来的值为空的，则结束定时器的设置
            finish();
        }
        //接下来你可以通过获取的计时器时长去设置一个计时器
        //设置计时器的一些操作，省略。。。
    }

    //把操作记录传回给谷歌语音，会被记录到系统里
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
        if(TimerListActivity.mInstance == null){
            Intent intentToAlarmList = new Intent(this ,TimerListActivity.class);
            startActivity(intentToAlarmList);
            //直接跳转到定时器列表界面
        }
        super.onStop();
    }
}
