package com.dafa.qipai.dafaqipai.youxi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoTuiChu;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dialog.BaseDialog;

import com.dafa.qipai.dafaqipai.dto.SocketSendDto;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.BackgroundMusic;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.websocket.AbsWebSocketActivity;
import com.dafa.qipai.dafaqipai.websocket.ErrorResponse;
import com.dafa.qipai.dafaqipai.websocket.Response;
import com.dafa.qipai.dafaqipai.websocket.WebSocketService;
import com.dafa.qipai.dafaqipai.websocket.WebSocketSetting;
import com.dafa.qipai.dafaqipai.websocket.core.AppResponseDispatcher;
import com.dafa.qipai.dafaqipai.wihget.DragView;
import com.dafa.qipai.dafaqipai.wihget.MoveView;
import com.dafa.qipai.dafaqipai.wihget.StateLayout;
import com.kongzue.dialog.v2.SelectDialog;
import com.lzy.okgo.OkGo;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QiPaiWebAppActivity extends AbsWebSocketActivity {


    @BindView(R.id.statelayout)
    StateLayout statelayout;
    @BindView(R.id.forum_context)
    com.tencent.smtt.sdk.WebView forumContext;

    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewapp2);
        ButterKnife.bind(this);


        url = getIntent().getStringExtra("url");


        forumContext.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);

                statelayout.showContentView();
            }
        });

        loadData();


        BackgroundMusic.getInstance(getApplicationContext()).pauseBackgroundMusic();

        AppUtils.stopBgMuisc(context);

    }


    private void loadData() {


        statelayout.showProgressView();


        forumContext.loadUrl(url);

    }


    @Override
    public void onBackPressed() {
        webViewGoBack();
    }

    /**
     * 内置浏览器能否返回。
     */
    private void webViewGoBack() {


        BaseDialog dialog = new BaseDialog(this, "是否返回大厅？") {

            @Override
            public void btn1DoThing(Dialog mDialog) {
                mDialog.dismiss();
            }

            @Override
            public void btn2DoThing(Dialog mDialog) {
                mDialog.dismiss();
                tuiChu();
            }
        };
        dialog.show();


    }

    private void tuiChu() {


        forumContext.destroy();
        finish();


    }


    @Override
    public void onSendMessageError(ErrorResponse error) {



//        int maxRetryTime = 5;
//        int time = 0;
//        String result = null;
//        do {
//            time++;
//            try {
//                result = testRedo();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } while (null == result && time < maxRetryTime);
//    }

//    private static String testRedo() {
//        System.out.println("执行Redo代码");
//        // 操作成功，返回结果，操作失败返回null
//        return null;
//    }



        new BaseDialog(this, "游戏断开连接，是否重新连接", "取消", "确定") {
            @Override
            public void btn1DoThing(Dialog mDialog) {
                    finish();
            }

            @Override
            public void btn2DoThing(Dialog mDialog) {

                //配置 WebSocket，必须在 WebSocket 服务启动前设置
                String connectUrl = ApiConstant.WS_DOMAIN + "ws?uid="
                        + UserUtil.getUserID(QiPaiWebAppActivity.this) + "&token="
                        + UserUtil.getToken(QiPaiWebAppActivity.this) +
                        "&clientType=Android&companyShortName=" + ApiConstant.COMPANY_SHORT_NAME + "&appVersion=1";

                WebSocketSetting.setConnectUrl(connectUrl);//必选

                WebSocketSetting.setResponseProcessDelivery(new AppResponseDispatcher());
                WebSocketSetting.setReconnectWithNetworkChanged(true);

                //启动 WebSocket 服务
                startService(new Intent(QiPaiWebAppActivity.this, WebSocketService.class));

            }
        }.show();


    }

    @Override
    public void onDisconnected() {
        super.onDisconnected();



    }

    @Override
    public void onMessageResponse(Response message) {

        System.out.println(message.getResponseText());

    }


    private Timer timer;
    private Task task;

    @Override
    public void onServiceBindSuccess() {
        super.onServiceBindSuccess();
        System.out.println("onServiceBindSuccess");

        timer = new Timer();
        task = new Task();
        //schedule 计划安排，时间表
        timer.schedule(task, 1 * 1, 6 * 1000);


    }


    public class Task extends TimerTask {
        @Override
        public void run() {

            String string = GsonUtil.GsonString(new SocketSendDto("ky", UserUtil.getUserID(context)));
            sendText(string);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();


        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }

        forumContext.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BackgroundMusic.getInstance(getApplicationContext()).pauseBackgroundMusic();
    }
}
