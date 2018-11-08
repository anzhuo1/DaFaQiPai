package com.dafa.qipai.dafaqipai.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.HuodongAdapter;
import com.dafa.qipai.dafaqipai.bean.DoGetPromotion;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;


public class HuoDongActivity extends BaseActivity {


    @BindView(R.id.response)
    RecyclerView recyclerView;
    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.bg)
    RelativeLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_huodong);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


        loadData();

    }


    @OnClick(R.id.close)
    public void onViewClicked() {
        finish();
    }

    private void loadData() {

        OkGo.post(ApiConstant.API_DOMAIN + "/promotion/getPromotion.json\n")
                .tag(this)
                .cacheKey("promotion/getPromotion.json")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(3600 * 1000 * 12)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String s) {
                        DoGetPromotion doGetPromotion = GsonUtil.GsonToBean(s, DoGetPromotion.class);
                        List<DoGetPromotion.PromotionListBean> promotionList = doGetPromotion.getPromotionList();
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(new HuodongAdapter(context, promotionList, R.layout.item_huodong));

                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        super.onSuccess(s, call, response);
                        DoGetPromotion doGetPromotion = GsonUtil.GsonToBean(s, DoGetPromotion.class);
                        List<DoGetPromotion.PromotionListBean> promotionList = doGetPromotion.getPromotionList();
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(new HuodongAdapter(context, promotionList, R.layout.item_huodong));
                    }
                });


    }

}
