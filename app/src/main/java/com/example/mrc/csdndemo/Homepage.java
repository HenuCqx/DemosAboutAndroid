package com.example.mrc.csdndemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mrc.csdndemo.BackgroundTimeTask.TimeTask;
import com.example.mrc.csdndemo.RecyclerViewCategory.RecyclerViewCategoryStyle;
import com.example.mrc.csdndemo.SetAlarmAndTimerByGoogleVoiceAction.MainActivity;
import com.example.mrc.csdndemo.UseGoogleVoiceActionInTheAPP.UseGoogleVoiceActionInTheAPPActivity;

public class Homepage extends AppCompatActivity implements View.OnClickListener {

    Button mBackgroundTimeTask,mRecyclerViewCategoryStyle ,mSetAlarmAndTimerByGoogleVoiceAction ,
            mUseGoogleVoiceActionInTheAPP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        findView();
        Init();
    }
    void findView(){
        mBackgroundTimeTask = (Button) findViewById(R.id.background_time_task);
        mRecyclerViewCategoryStyle =(Button) findViewById(R.id.recyclerView_CategoryStyle);
        mSetAlarmAndTimerByGoogleVoiceAction = (Button)findViewById(R.id.set_alarm_and_timer_by_google_voice_action);
        mUseGoogleVoiceActionInTheAPP = (Button)findViewById(R.id.use_google_voice_action_in_the_app);
        mBackgroundTimeTask.setOnClickListener(this);
        mRecyclerViewCategoryStyle.setOnClickListener(this);
        mSetAlarmAndTimerByGoogleVoiceAction.setOnClickListener(this);
        mUseGoogleVoiceActionInTheAPP.setOnClickListener(this);
    }

    void Init(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.background_time_task :
                Intent backgroundTimeTaskIntent =new Intent(Homepage.this , TimeTask.class);
                startActivity(backgroundTimeTaskIntent);
                break;
            case R.id.recyclerView_CategoryStyle :
                Intent recyclerViewCategoryStyle =new Intent(Homepage.this ,RecyclerViewCategoryStyle.class);
                startActivity(recyclerViewCategoryStyle);
                break;
            case R.id.set_alarm_and_timer_by_google_voice_action :
                Intent setAlarmAndTimerByGoogleVoiceAction = new Intent(Homepage.this ,MainActivity.class);
                startActivity(setAlarmAndTimerByGoogleVoiceAction);
                break;
            case R.id.use_google_voice_action_in_the_app :
                Intent useGoogleVoiceActionIntheAPPIntent = new Intent(Homepage.this , UseGoogleVoiceActionInTheAPPActivity.class);
                startActivity(useGoogleVoiceActionIntheAPPIntent);
                break;
            default:break;
        }
    }
}
