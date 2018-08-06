package com.example.mrc.csdndemo.AndroidViewAbout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import android.widget.Toast;

import com.example.mrc.csdndemo.R;

public class ViewSlideSpeedActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    View mView;
    GestureDetector gestureDetector;
    Scroller scroller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_slide_speed);
        findView();
    }

    void findView(){
        mView = (View)findViewById(R.id.view);

        scroller = new Scroller(mView.getContext());

        gestureDetector = new GestureDetector(this);
        gestureDetector.setIsLongpressEnabled(false);

        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean consume = gestureDetector.onTouchEvent(event);
                String motionEventString = null;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        motionEventString = "按下屏幕";
                        break;
                    case MotionEvent.ACTION_MOVE:
                        motionEventString = "滑动屏幕";
                        break;
                    case MotionEvent.ACTION_UP:
                        motionEventString = "移出屏幕";
                        break;
                }
                /*VelocityTracker velocityTracker = new VelocityTracker.obtain();
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);
                int xVelocity = (int)velocityTracker.getXVelocity();
                int yVelocity = (int)velocityTracker.getYVelocity();
                Toast.makeText(ViewSlideSpeedActivity.this ,"滑动的水平方向的速度：" + xVelocity +
                        "   " + "滑动的竖直方向的速度：" + yVelocity ,Toast.LENGTH_LONG).show();
                velocityTracker.clear();   //重置方法
                velocityTracker.recycle();  //回收方法*/
                return consume;
            }
        });
        cqx(mView.getScrollX() ,mView.getScrollY());
    }

    private void cqx (int destX ,int destY){
        int x= mView.getScrollX();
        int delta = destX -destY;
        scroller.startScroll(x , 0 ,delta , 0 ,1000);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Toast.makeText(ViewSlideSpeedActivity.this ,"按下",Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Toast.makeText(ViewSlideSpeedActivity.this ,"手指轻轻触摸，未拖动和松开",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Toast.makeText(ViewSlideSpeedActivity.this ,"手指松动",Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Toast.makeText(ViewSlideSpeedActivity.this ,"手指按下并拖动",Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Toast.makeText(ViewSlideSpeedActivity.this ,"长按",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Toast.makeText(ViewSlideSpeedActivity.this ,"按下，快速滑动并松开",Toast.LENGTH_LONG).show();
        return false;
    }

}
