package com.example.mrc.csdndemo.BackgroundTimeTask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mrc.csdndemo.R;

public class TimeTask extends AppCompatActivity implements View.OnClickListener {

    Button mOpenBtn ,mCloseBtn ,mOpenMoreBtn ,mCloseMoreBtn;
    Intent mOneIntent = null ;
    Intent mMoreIntent =null;
    TimeTaskService timeTaskService ;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_task);
        /*sharedPreferences =getApplicationContext().getSharedPreferences("com.example.mrc.csdnDemo" , 0);
        editor=sharedPreferences.edit();
        editor.putInt("count",0);
        editor.commit();*/

        findView();
        Init();
    }

    void findView(){
        mOpenBtn = (Button)findViewById(R.id.open_time_task);
        mCloseBtn = (Button)findViewById(R.id.close_time_task);
        mOpenMoreBtn = (Button)findViewById(R.id.open_more_time_task);
        mCloseMoreBtn = (Button)findViewById(R.id.close_more_time_task);

        mOpenBtn.setOnClickListener(this);
        mCloseBtn.setOnClickListener(this);
        mOpenMoreBtn.setOnClickListener(this);
        mCloseMoreBtn.setOnClickListener(this);
    }

    void Init(){

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.open_time_task :
                mOneIntent =new Intent(this ,TimeTaskService.class) ;
                /*mOneIntent.setAction("ITOP.MOBILE.SIMPLE.SERVICE.SENSORSERVICE");*/
                startService(mOneIntent);
                break;
            case R.id.close_time_task :
                Intent intentOneStop = new Intent(this, TimeTaskService.class);
                /*Intent intent =new Intent();*/
               /* intent.setAction("ITOP.MOBILE.SIMPLE.SERVICE.SENSORSERVICE");*/  //android5.0以下才适合使用
                stopService(intentOneStop);
                break;
            case R.id.open_more_time_task :
                mMoreIntent =new Intent(this ,MoreTimeTaskService.class) ;
                /*mOneIntent.setAction("ITOP.MOBILE.SIMPLE.SERVICE.SENSORSERVICE");*/
                startService(mMoreIntent);
                break;
            case R.id.close_more_time_task :
                Intent intentMoreStop = new Intent(this, MoreTimeTaskService.class);
                /*Intent intentMoreStop =new Intent();*/
               /* intent.setAction("ITOP.MOBILE.SIMPLE.SERVICE.SENSORSERVICE");*/  //android5.0以下才适合使用
                stopService(intentMoreStop);
                break;
            default:break;
        }
    }
}
