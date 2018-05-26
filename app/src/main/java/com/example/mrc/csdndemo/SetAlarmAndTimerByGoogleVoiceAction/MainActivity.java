package com.example.mrc.csdndemo.SetAlarmAndTimerByGoogleVoiceAction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mrc.csdndemo.R;

import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button mToAlarm ,getmToTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }

    void findView(){
        mToAlarm = (Button)findViewById(R.id.alarm);
        getmToTimer = (Button)findViewById(R.id.timer);
        mToAlarm.setOnClickListener(this);
        getmToTimer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alarm :
                Intent toAlarmIntent = new Intent(MainActivity.this ,AlarmListActivity.class);
                startActivity(toAlarmIntent);
                break;
            case R.id.timer :
                Intent toTimerIntent = new Intent(MainActivity.this , TimerListActivity.class);
                startActivity(toTimerIntent);
        }
    }
}
