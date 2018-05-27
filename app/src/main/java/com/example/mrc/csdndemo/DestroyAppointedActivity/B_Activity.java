package com.example.mrc.csdndemo.DestroyAppointedActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mrc.csdndemo.R;

public class B_Activity extends AppCompatActivity implements View.OnClickListener {
    Button mStartAActivity;

    //用来标识B Activity的状态
    public static B_Activity mInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mInstance = this;

        mStartAActivity = (Button)findViewById(R.id.start_a_activity);
        mStartAActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_a_activity :
                Intent intent = new Intent(B_Activity.this ,A_Activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //执行onDestroy()方法后赋值为零
        mInstance = null;
    }
}
