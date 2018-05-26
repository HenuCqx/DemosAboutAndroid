package com.example.mrc.csdndemo.UseGoogleVoiceActionInTheAPP;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mrc.csdndemo.R;

import java.util.ArrayList;

public class UseGoogleVoiceActionInTheAPPActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1;
    private String[] str;
    private Context context;
    Button search_voice_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_google_voice_action_in_the_app);

        context = this;
        search_voice_btn =(Button)findViewById(R.id.search_voice_btn);
        search_voice_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_voice_btn:
                VoiceSearchUtil.getVoiceSearch(context);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 回调获取从谷歌得到的数据
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            // 取得语音的字符
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            // 循环所有结果存入数组
            str = new String[results.size()];
            for (int i = 0; i < results.size(); i++) { // 循环所有结果
                str[i] = results.get(i);
            }
            // Dialog显示
            getSearchDialog();
            // 这里只获取第一条数据
            Toast.makeText(this, results.get(0), Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /***
     * 搜索后dialog显示结果
     */
    private void getSearchDialog() {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("你是不是想找：").setIcon(R.drawable.ic_launcher_background)
                .setItems(str, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(UseGoogleVoiceActionInTheAPPActivity.this, str[which], Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", null).create();
        dialog.show();
    }
}
