package com.example.mrc.csdndemo.AndroidViewAbout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mrc.csdndemo.R;

public class AndroidViewDemoActivity extends AppCompatActivity implements View.OnClickListener {
    Button mViewGetCoordinate ; //获取View的坐标
    Button mViewGetXY ; //获取View的x y
    Button mViewGetSpeed ; //获取View的滑动速度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_view_demo);

        findView();
    }

    void findView(){
        mViewGetCoordinate = (Button)findViewById(R.id.view_get_coordinate);
        mViewGetCoordinate.setOnClickListener(this);

        mViewGetXY = (Button)findViewById(R.id.view_xy);
        mViewGetXY.setOnClickListener(this);

        mViewGetSpeed = (Button)findViewById(R.id.view_speed);
        mViewGetSpeed.setOnClickListener(this);
    }

    /**
     * 按钮点击事件
     * @author  cqx
     * create at 2018/6/21 18:55
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.view_get_coordinate:
                getCoordinate();
                break;
            case R.id.view_xy:
                getViewXY();
                break;
            case R.id.view_speed:
                Intent intentToViewSpeed = new Intent(AndroidViewDemoActivity.this ,ViewSlideSpeedActivity.class);
                startActivity(intentToViewSpeed);
                break;
            default:
                break;
        }
    }

    /**
     * 获取点击的 button 的坐标 和 长度，宽度
     * @author  cqx
     * create at 2018/6/21 16:20
     */
    void getCoordinate(){
        int top = mViewGetCoordinate.getTop();
        int left = mViewGetCoordinate.getLeft();
        int right = mViewGetCoordinate.getRight();
        int bottom = mViewGetCoordinate.getBottom();
        int width = right - left;
        int height = bottom - top;
        String result = "top :" + top +"  " + "left :" + left +"  " + "right :" + right + "   " +
                "bottom :" + bottom + "  " + "width :" + width + "  " + "height ：" + height;
        Toast.makeText(AndroidViewDemoActivity.this ,result ,Toast.LENGTH_LONG).show();
    }

    /**
     * 获取 x y translationX translationY
     * @author  cqx
     * create at 2018/6/21 17:34
     */
    void getViewXY(){
        float x = mViewGetCoordinate.getX();
        float y = mViewGetCoordinate.getY();
        //ranslationX translationY默认是0
        float translationX = mViewGetCoordinate.getTranslationX();
        float translationY = mViewGetCoordinate.getTranslationY();
        String result = "x :" + x +"  " + "y :" + y +"  " + "translationX :" + translationX + "   " +
                "translationY :" + translationY + "  " ;
        Toast.makeText(AndroidViewDemoActivity.this ,result ,Toast.LENGTH_LONG).show();
    }
}
