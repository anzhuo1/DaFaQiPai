package com.dafa.qipai.dafaqipai.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.TuiGuangAdapter;
import com.dafa.qipai.dafaqipai.bean.DoTuiGuang;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.lzy.okgo.OkGo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DaiLiActivity extends BaseActivity {


    @BindView(R.id.ll_bg)
    LinearLayout llBg;
    @BindView(R.id.cheng)
    TextView cheng;
    @BindView(R.id.jiaocheng)
    TextView jiaocheng;
    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.bg)
    RelativeLayout bg;
    private List<DoTuiGuang.AgentContactBean> agentContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_daili);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


        OkGo.post(ApiConstant.API_DOMAIN + "/agent/getAgentContact.json")
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {
                        DoTuiGuang doTuiGuang = GsonUtil.GsonToBean(json, DoTuiGuang.class);
                        if (doTuiGuang.getResult() == 1) {

                            agentContact = doTuiGuang.getAgentContact();

                            recyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,
                                    StaggeredGridLayoutManager.HORIZONTAL));


                            TuiGuangAdapter adapter = new TuiGuangAdapter(context, agentContact, R.layout.item_tuiguang_daili);
                            recyclerview.setAdapter(adapter);


                            adapter.setOnItemClickListener(new TuiGuangAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view) {
                                    int position = recyclerview.getChildAdapterPosition(view);
                                    TextView id = view.findViewById(R.id.num);
                                    String s = id.getText().toString();

                                    AppUtils.copyToClipboard(DaiLiActivity.this,s);

                                }

                                @Override
                                public void onItemLongClick(View view) {

                                }
                            });

                        }

                    }


                });

    }


    @OnClick({R.id.close, R.id.cheng, R.id.jiaocheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cheng:
                llBg.setBackgroundResource(R.mipmap.tg1);
                if (agentContact.isEmpty()) {
                    recyclerview.setVisibility(View.GONE);
                } else {

                    recyclerview.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.jiaocheng:
                llBg.setBackgroundResource(R.mipmap.bg_daili2);

                recyclerview.setVisibility(View.GONE);

                break;
            case R.id.close:
                finish();

                break;
        }
    }


}