package com.dafa.qipai.dafaqipai;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.bean.DOgetAppCzInfoResult;

import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.ActivityContainer;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.BackgroundMusic;
import com.dafa.qipai.dafaqipai.util.CountTimeUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.view.MainActivity;
import com.dafa.qipai.dafaqipai.view.SheZhiActivity;
import com.kongzue.dialog.v2.DialogSettings;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.kongzue.dialog.v2.DialogSettings.THEME_DARK;


public class MyApp extends Application {


    public static List<DOgetAppCzInfoResult.CzTypeListBean.CzListBean> czList = new ArrayList<>();

    public static String ID;

    public static String NICHENG;

    private static MyApp sApp;

    public static int type = 1;   //不拨声音

    public static String jine;

    public static String baoxianxiang;


    //-------
   // public static boolean isBG = false;


    public static MyApp getInstance() {
        MyApp inst = sApp;  // <<< 在这里创建临时变量
        if (inst == null) {
            synchronized (MyApp.class) {
                inst = sApp;
                if (inst == null) {
                    inst = new MyApp();
                    sApp = inst;
                }
            }
        }

        return inst;  // <<< 注意这里返回的是临时变量
    }


    @Override
    public void onCreate() {
        super.onCreate();


        DialogSettings.type = DialogSettings.TYPE_KONGZUE;
        DialogSettings.dialog_theme = THEME_DARK;

        DialogSettings.dialog_cancelable_default = false;

        initOkGo();

        WebView.setWebContentsDebuggingEnabled(true);

    }

    private void initOkGo() {
        //必须调用初始化
        OkGo.init(this);

        // 初始化 JPush

        try {

            HttpParams params = new HttpParams();
            params.put("uid", UserUtil.getUserID(this));
            params.put("token", UserUtil.getToken(this));
            params.put("clientType", "Android");
            params.put("companyShortName", ApiConstant.COMPANY_SHORT_NAME);
            params.put("appVersion", ApiConstant.VERSION);
            OkGo.getInstance()
                    .setRetryCount(0)
                    .addCommonParams(params)
                    .setConnectTimeout(8000)  //全局的连接超时时间
                    .setReadTimeOut(8000)     //全局的读取超时时间
                    .setWriteTimeOut(8000)    //全局的写入超时时间
                    .debug("OkGo");

        } catch (Exception e) {
            e.printStackTrace();
        }





        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                if (!UserUtil.isLoginApp(MyApp.this)) {
                    return;
                }

                OkGo.post(ApiConstant.API_DOMAIN + "member/checkOnline.json")
                        .tag(MyApp.this)
                        .params("token", UserUtil.getToken(MyApp.this))
                        .params("uid", UserUtil.getUserID(MyApp.this))
                        .execute(new OkGoCallBack(null, false) {
                            @Override
                            protected void _onNext(String json) {
                                BaseDo baseDo = GsonUtil.GsonToBean(json, BaseDo.class);
                                switch (baseDo.getResult()) {
                                    case 1:
                                        break;
                                    case 108:
                                        UserUtil.loginOut(MyApp.this);
                                        showDialog(baseDo.getDescription());
                                        break;
                                    case 109:
                                        UserUtil.loginOut(MyApp.this);
                                        showDialog(baseDo.getDescription());
                                        break;
                                }
                            }
                        });


            }
        }, 1000, 20000);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                if (AppUtils.isAppOnForeground(getApplicationContext())) {

                    BackgroundMusic.getInstance(getApplicationContext()).resumeBackgroundMusic();

                } else {

                    BackgroundMusic.getInstance(getApplicationContext()).pauseBackgroundMusic();

                }



            }
        }, 1000, 1000);


    }

    private void showDialog(String description) {

//        Process.killProcess(Process.myPid()); //获取PID
//        System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出

        ActivityContainer.getInstance().finishAllActivity();

        Intent intent = new Intent(MyApp.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }


    /**
     * 判断app是否处于前台
     *
     * @param context
     * @return
     */
    public static boolean isAppForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Service.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        if (runningAppProcessInfoList == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcessInfoList) {
            if (processInfo.processName.equals(context.getPackageName()) &&
                    processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断app是否处于前台
     *
     * @param context
     * @return
     */
    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
            return true;
        }
        return false;
    }


}
