package com.example.mrc.csdndemo.TaskStackBuilder;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mrc.csdndemo.R;

public class TaskStackBuilderActivity extends AppCompatActivity {

    /**
     * TaskStackBuilder可以构造一个合成的回退栈，主要用于跨任务导航，应用于Android3.0及新版本。
     我们通常利用返回键导航app，而返回键是基于当前任务的导航，这种局部的导航只有将当前任务中所有
     Activity结束掉时才返回上个任务，若从当前app跳转到另一app时就涉及到不同任务的跳转，也就是
     从任务一跳转到任务二时，从任务二中按返回键时无法直接返回到任务一，除非任务二中只存在一个Activity。
     为了提供更好的用户体验，在跨应用的跳转中，利用TaskStackBuilder的getPendingIntent(int requestCode, int flags)，
     可将当前任务中的Activity和要启动的Activity合成为一个新的任务，而用户按返回键的操作就作用在这个新任务中，
     这相当于实现了跨任务直接跳转。
     若从任务一直接启动一个Intent去开启另一个应用的Activity，会直接把该Activity加入到这个应用的任务二中去，
     按返回键时最终只会回到主界面，而不会回到任务一
     * @author  cqx
     * create at 2018/8/6 19:49
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_stack_builder);

        //        获得TaskStackBuilder对象
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        Intent firstIntent = new Intent(this, this.getClass());
        Intent resultIntent = new Intent();
        ComponentName componentName = new ComponentName("com.example.mrc.csdndemo", "com.example.mrc.csdndemo.HomePage");
        resultIntent.setComponent(componentName);
//        addNextIntent()方法会添加Intent到任务的顶端，将当前app的Activity与另一app的Activity添加到一个由stackBuilder创建的新的任务中
        stackBuilder.addNextIntent(firstIntent);
        stackBuilder.addNextIntent(resultIntent);
//        获取一个PendingIntent去启动stackBuilder所创建的新任务
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//        发送PendingIntent
        try {
            resultPendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}
