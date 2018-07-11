package com.example.mrc.csdndemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mrc.csdndemo.AndroidViewAbout.AndroidViewDemoActivity;
import com.example.mrc.csdndemo.BackgroundTimeTask.TimeTask;
import com.example.mrc.csdndemo.DestroyAppointedActivity.A_Activity;
import com.example.mrc.csdndemo.PreferenceExample.PreferenceExampleActivity;
import com.example.mrc.csdndemo.RecyclerViewCategory.RecyclerViewCategoryStyle;
import com.example.mrc.csdndemo.SetAlarmAndTimerByGoogleVoiceAction.MainActivity;
import com.example.mrc.csdndemo.TimerPickerExample.TimerPickerExampleActivity;
import com.example.mrc.csdndemo.UseGoogleVoiceActionInTheAPP.UseGoogleVoiceActionInTheAPPActivity;

public class Homepage extends AppCompatActivity implements View.OnClickListener {

    Button mBackgroundTimeTask,mRecyclerViewCategoryStyle ,mSetAlarmAndTimerByGoogleVoiceAction ,
            mUseGoogleVoiceActionInTheAPP ,mPreferenceExample ,mTimerPickerExample ,mDestroyAppointedActivity ,
            mAndroidView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        findView();
        Init();
    }
    void findView(){
        mPreferenceExample = (Button)findViewById(R.id.preference_example);
        mTimerPickerExample = (Button)findViewById(R.id.timePicker_example);
        mBackgroundTimeTask = (Button) findViewById(R.id.background_time_task);
        mRecyclerViewCategoryStyle =(Button) findViewById(R.id.recyclerView_CategoryStyle);
        mSetAlarmAndTimerByGoogleVoiceAction = (Button)findViewById(R.id.set_alarm_and_timer_by_google_voice_action);
        mUseGoogleVoiceActionInTheAPP = (Button)findViewById(R.id.use_google_voice_action_in_the_app);
        mDestroyAppointedActivity = (Button)findViewById(R.id.destroy_appointed_activity);
        mAndroidView = (Button)findViewById(R.id.android_view);

        mPreferenceExample.setOnClickListener(this);
        mTimerPickerExample.setOnClickListener(this);
        mBackgroundTimeTask.setOnClickListener(this);
        mRecyclerViewCategoryStyle.setOnClickListener(this);
        mSetAlarmAndTimerByGoogleVoiceAction.setOnClickListener(this);
        mUseGoogleVoiceActionInTheAPP.setOnClickListener(this);
        mDestroyAppointedActivity.setOnClickListener(this);
        mAndroidView.setOnClickListener(this);
    }

    void Init(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.preference_example :
                Intent preferenceExampleIntent =new Intent(Homepage.this , PreferenceExampleActivity.class);
                startActivity(preferenceExampleIntent);
                break;
            case R.id.timePicker_example :
                Intent timerPickerExampleIntent =new Intent(Homepage.this , TimerPickerExampleActivity.class);
                startActivity(timerPickerExampleIntent);
                break;
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
            case R.id.destroy_appointed_activity :
                Intent destroyAppointedActivityIntent = new Intent(Homepage.this , A_Activity.class);
                startActivity(destroyAppointedActivityIntent);
                break;
            case R.id.android_view :
                Intent androidViewIntent = new Intent(Homepage.this , AndroidViewDemoActivity.class);
                startActivity(androidViewIntent);
                break;
            default:break;
        }
    }
}
