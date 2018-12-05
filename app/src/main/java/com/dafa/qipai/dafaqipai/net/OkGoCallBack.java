package com.dafa.qipai.dafaqipai.net;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.annotation.Nullable;


import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.NetWorkUtil;
import com.kongzue.dialog.v2.DialogSettings;
import com.kongzue.dialog.v2.WaitDialog;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Response;


public abstract class OkGoCallBack extends StringCallback {

    private Activity mContext;

    private boolean isShowDialg;
    private WaitDialog waitDialog;


    public OkGoCallBack(Activity context, boolean isShowDialg) {

        this.mContext = context;
        this.isShowDialg = isShowDialg;

    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);


        try {
            if (isShowDialg) {

                if (mContext != null && !mContext.isFinishing()) {

                    try {
                        DialogSettings.unloadAllDialog();
                        waitDialog = WaitDialog.show(mContext, "正在加载...").setCanCancel(false);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAfter(@Nullable String s, @Nullable Exception e) {
        super.onAfter(s, e);

        try {
            if (!mContext.isFinishing() && waitDialog != null) {

                waitDialog.doDismiss();
                DialogSettings.unloadAllDialog();           //卸载掉所有对话框


            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }


    @Override
    public void onSuccess(String s, Call call, Response response) {

        try {
            _onNext(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        //super.onError(call, response, e);


        _onError(e.getMessage());


        if (!NetWorkUtil.isNetworkConnected(this.mContext)) {


            AppUtils.showToast(mContext, "网络不可用");

        } else {

            if (e instanceof SocketTimeoutException) {

                AppUtils.showToast(mContext, "请求超时");

            }

        }

    }


    protected void _onError(String error) {
    }

    protected abstract void _onNext(String s);
}