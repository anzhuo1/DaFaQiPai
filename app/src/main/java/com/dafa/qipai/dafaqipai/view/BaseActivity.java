package com.dafa.qipai.dafaqipai.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.chong.YinHangKaZhuanZhangActivity;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.SoundPoolUtil;
import com.kongzue.dialog.v2.DialogSettings;
import com.kongzue.dialog.v2.Notification;


public abstract class BaseActivity extends AppCompatActivity {

    private SoundPoolUtil soundPoolUtil;


    /**
     * 设置点击按钮音乐类型
     *
     * @param musicType FIRST SECOND THIRD三个参数
     */
    public void playMusic(int musicType) {

        soundPoolUtil.play(musicType);
    }

    /**
     * 全局上下文
     */
    public Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soundPoolUtil = SoundPoolUtil.getInstance(this);
        context = getApplicationContext();

        ActivityContainer.getInstance().addActivity(this);
    }


    /**
     * 打开一个Activity 默认 不关闭当前activity
     *
     * @param clz
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(this, clz);
        if (ex != null)
            intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            DialogSettings.unloadAllDialog();           //卸载掉所有对话框
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showError(Context activity, String msg) {

        try {
            DialogSettings.unloadAllDialog();           //卸载掉所有对话框
            Notification.show(activity, 0, "", msg, Notification.SHOW_TIME_SHORT, Notification.TYPE_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInfo(Context activity, String msg) {

        try {
            DialogSettings.unloadAllDialog();           //卸载掉所有对话框
            Notification.show(activity, 0, "", msg, Notification.SHOW_TIME_SHORT, Notification.TYPE_NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
