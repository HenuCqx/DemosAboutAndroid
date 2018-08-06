package com.example.mrc.csdndemo.ServiceDemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mrc.csdndemo.R;

public class LaunchServiceActivity extends AppCompatActivity implements View.OnClickListener {
    Button mNormalService ,mInteractiveService;
    boolean isOpenNormal;
    boolean isOpenInteractive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_service);

        findView();
        init();
    }

    void findView(){
        mNormalService = (Button)findViewById(R.id.normal_service_example);
        mInteractiveService = (Button)findViewById(R.id.interactive_service_example);
    }

    void init(){
        isOpenNormal = false;
        isOpenInteractive = false;
        mNormalService.setOnClickListener(this);
        mInteractiveService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.normal_service_example:
                Intent intent = new Intent(LaunchServiceActivity.this , MyService.class);
                if (isOpenNormal){
                    mNormalService.setText("关闭普通的Service");
                    startService(intent);
                }else {
                    mNormalService.setText("打开普通的Service");
                    stopService(intent);
                }
                break;
            case R.id.interactive_service_example:
                Intent intentInteractive = new Intent(LaunchServiceActivity.this , MyService.class);
                if (isOpenInteractive){
                    mInteractiveService.setText("关闭可交互的Service");
                    bindService(intentInteractive ,con ,BIND_AUTO_CREATE);
                }else {
                    mInteractiveService.setText("打开可交互的Service");
                    unbindService(con);
                    stopService(intentInteractive);
                }
                break;
        }
    }

    private ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBind myBind = (MyService.MyBind)service;
            myBind.showTip();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
