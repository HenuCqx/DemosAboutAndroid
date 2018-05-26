package com.example.mrc.csdndemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mrc.csdndemo.BackgroundTimeTask.TimeTask;
import com.example.mrc.csdndemo.RecyclerViewCategory.RecyclerViewCategoryStyle;

import java.text.BreakIterator;

public class Homepage extends AppCompatActivity implements View.OnClickListener {

    Button mBackgroundTimeTask,mRecyclerViewCategoryStyle;
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
        mBackgroundTimeTask.setOnClickListener(this);
        mRecyclerViewCategoryStyle.setOnClickListener(this);
    }

    void Init(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.background_time_task :
                Intent backgroundTimeTaskIntent =new Intent(this , TimeTask.class);
                startActivity(backgroundTimeTaskIntent);
                break;
            case R.id.recyclerView_CategoryStyle :
                Intent recyclerViewCategoryStyle =new Intent(this ,MainActivity.class);
                startActivity(recyclerViewCategoryStyle);
            default:break;
        }
    }
}
