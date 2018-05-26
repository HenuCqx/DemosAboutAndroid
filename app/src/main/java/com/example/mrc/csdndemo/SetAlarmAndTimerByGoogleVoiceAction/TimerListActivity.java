package com.example.mrc.csdndemo.SetAlarmAndTimerByGoogleVoiceAction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mrc.csdndemo.R;

public class TimerListActivity extends AppCompatActivity {
    //用来标识该页面是否处于栈顶
    public static TimerListActivity mInstance = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_list);
        mInstance = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInstance = null;
    }
}
