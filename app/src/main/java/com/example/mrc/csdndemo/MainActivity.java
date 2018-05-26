package com.example.mrc.csdndemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ccc" ,"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ccc" ,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ccc" ,"onResume");
        this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ccc" ,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ccc" ,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ccc" ,"onDestroy");
    }

}
