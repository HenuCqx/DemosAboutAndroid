package com.example.mrc.csdndemo.UseGoogleVoiceActionInTheAPP;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Mr.C on 2018/5/26.
 */

public class VoiceSearchUtil {

    private static Context context;
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1;

    public static void getVoiceSearch(Context c) {
        context = c;
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> activities = pm
                .queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() != 0) {
            /* 设备存在 */
            Log.d("testvoice" ,"设备存在！");
            voiceSearch();
        } else {
            Log.d("testvoice" ,"设备不存在！");
            /* 没有设备请点击安装按钮进行安装呢 */
            if (copyApkFromAssets(context, "google_voice_search.apk", Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/google_voice_search.apk")) {
                new AlertDialog.Builder(context).setMessage("检测到未安装语音搜索设备，是否安装？")
                        .setPositiveButton("安装", new android.content.DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setDataAndType(
                                        Uri.parse("file://"
                                                + Environment.getExternalStorageDirectory().getAbsolutePath()
                                                + "/google_voice_search.apk"),
                                        "application/vnd.android.package-archive");
                                context.startActivity(intent);
                            }
                        }).setNegativeButton("取消", null).show();
            }
        }
    }

    /**
     * 安装语音搜索功能,写入数据
     *
     * @param context
     *            上下文
     * @param fileName
     *            文件名称
     * @param path
     *            文件路径
     * @return 所需文件
     */
    public static boolean copyApkFromAssets(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }

    // 语音搜索
    private static void voiceSearch() {
        try {
            // 通过Intent传递语音识别的模式，开启语音
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            // 语言模式和自由模式的语音识别
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            // 提示语音开始
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "开始语音");
            // 开始语音识别
            ((Activity) context).startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "找不到语音设备", Toast.LENGTH_LONG).show();
        }
    }
}
