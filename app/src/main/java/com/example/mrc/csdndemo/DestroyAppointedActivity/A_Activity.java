package com.example.mrc.csdndemo.DestroyAppointedActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mrc.csdndemo.R;

public class A_Activity extends AppCompatActivity implements View.OnClickListener {
    Button mStartBActivity ,mDestroyBActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        mStartBActivity = (Button)findViewById(R.id.start_b_activity);
        mDestroyBActivity = (Button)findViewById(R.id.destroy_b_activity);
        mStartBActivity.setOnClickListener(this);
        mDestroyBActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_b_activity :
                Intent startBActivityIntent = new Intent(A_Activity.this ,B_Activity.class);
                startActivity(startBActivityIntent);
                break;
            case R.id.destroy_b_activity :
                if(B_Activity.mInstance == null){
                    Toast.makeText(A_Activity.this ,"B Activity未被创建，请先启动 B Activity。" ,Toast.LENGTH_LONG).show();
                }else {
                    B_Activity.mInstance.finish();
                    Toast.makeText(A_Activity.this ,"B Activity被销毁" ,Toast.LENGTH_LONG).show();
                }
        }
    }
}
