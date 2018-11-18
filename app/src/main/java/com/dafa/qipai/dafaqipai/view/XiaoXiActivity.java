package com.dafa.qipai.dafaqipai.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.GonggaoAdapter;
import com.dafa.qipai.dafaqipai.adapter.ZhanNeiXinAdapter;
import com.dafa.qipai.dafaqipai.bean.BaseDo;
import com.dafa.qipai.dafaqipai.bean.PublicMsgDo;
import com.dafa.qipai.dafaqipai.bean.ZhanNeiXinDo;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.lzy.okgo.OkGo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class XiaoXiActivity extends BaseActivity {


    @BindView(R.id.qipai)
    RadioButton qipai;
    @BindView(R.id.zhenren)
    RadioButton zhenren;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.finish)
    TextView finish;
    @BindView(R.id.bg)
    RelativeLayout bg;
    @BindView(R.id.quanxuan)
    TextView quanxuan;
    @BindView(R.id.chuanchu)
    TextView chuanchu;
    @BindView(R.id.llbb)
    LinearLayoutCompat llbb;
    @BindView(R.id.gonggao)
    RecyclerView gonggao;
    private List<ZhanNeiXinDo.UserInboxBean> inboxList;
    private ZhanNeiXinAdapter zhanNeiXinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AutoUtils.setSize(this, false, 1920, 1080);
        setContentView(R.layout.activity_xiaoxi);
        ButterKnife.bind(this);
        AutoUtils.auto(this);


        loadDate();


        recyclerview.setVisibility(View.GONE);
        llbb.setVisibility(View.GONE);

    }

    private void loadDate() {
        OkGo.post(ApiConstant.API_DOMAIN + "/member/getUserInboxList.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .params("pageIndex", 1)
                .params("pageSize", 200)
                .execute(new OkGoCallBack(this, false) {


                    @Override
                    protected void _onNext(String json) {
                        try {
                            ZhanNeiXinDo baseDo = GsonUtil.GsonToBean(json, ZhanNeiXinDo.class);
                            if (baseDo.getResult() == 1) {

                                inboxList = baseDo.getUserInboxList();
                                recyclerview.setLayoutManager(new LinearLayoutManager(context));

                                if (inboxList != null && inboxList.size() > 0) {
//                                    llbb.setVisibility(View.VISIBLE);
                                }

                                zhanNeiXinAdapter = new ZhanNeiXinAdapter(context, inboxList, R.layout.item_zhanneixin);

                                recyclerview.setAdapter(zhanNeiXinAdapter);

                                zhanNeiXinAdapter.setOnItemClickListener(new ZhanNeiXinAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view) {

                                        int position = recyclerview.getChildAdapterPosition(view);

                                        String content = inboxList.get(position).getContent();
                                        Long createTime = inboxList.get(position).getCreateTime();

                                        Bundle bundle = new Bundle();
                                        bundle.putString("content", content);
                                        bundle.putLong("createTime", createTime);

                                        gotoActivity(XiaoXiDetailActivity.class, false, bundle);

                                    }

                                    @Override
                                    public void onItemLongClick(View view) {

                                    }
                                });

                            } else {
                                Toast.makeText(context, baseDo.getDescription(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

                        }
                    }
                });


        OkGo.post(ApiConstant.API_DOMAIN + "/notice/getPopupNoticeList.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .execute(new OkGoCallBack(this, false) {
                    @Override
                    protected void _onNext(String json) {
                        PublicMsgDo baseDo = GsonUtil.GsonToBean(json, PublicMsgDo.class);
                        List<PublicMsgDo.WebNoticeListBean> noticeList = baseDo.getWebNoticeList();

                        gonggao.setLayoutManager(new LinearLayoutManager(context));

                        if (noticeList != null && noticeList.size() > 0) {

                            gonggao.setAdapter(new GonggaoAdapter(context, noticeList, R.layout.item_gonggao));

                        }

                    }
                });


    }


    @OnClick({R.id.qipai, R.id.zhenren, R.id.finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qipai:
                gonggao.setVisibility(View.VISIBLE);
                recyclerview.setVisibility(View.GONE);
                llbb.setVisibility(View.GONE);
                break;
            case R.id.zhenren:
                gonggao.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
                llbb.setVisibility(View.VISIBLE);
                break;
            case R.id.finish:
                finish();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.quanxuan, R.id.chuanchu})
    public void onViewClicked2(View view) {
        switch (view.getId()) {
            case R.id.quanxuan:


                for (ZhanNeiXinDo.UserInboxBean z : inboxList) {
                    z.setTast(true);
                }
                zhanNeiXinAdapter.notifyDataSetChanged();

                break;
            case R.id.chuanchu:


                for (ZhanNeiXinDo.UserInboxBean z : inboxList) {
                    if (z.isTast()) {
                        del(z.getId().intValue());

                    }
                }

                loadDate();
                zhanNeiXinAdapter.notifyDataSetChanged();

                break;
        }
    }


    /**
     * 递归删除
     */
    private void del(int id) {


        //服务器删除
        OkGo.post(ApiConstant.API_DOMAIN + "/member/delUserInboxNotice.json")
                .tag(this)
                .params("token", UserUtil.getToken(this))
                .params("uid", UserUtil.getUserID(this))
                .params("idList", id)
                .execute(new OkGoCallBack(this, false) {

                    @Override
                    protected void _onNext(String json) {
                        BaseDo baseDo = GsonUtil.GsonToBean(json, BaseDo.class);
                        if (baseDo.getResult() == 1) {

                        }
                    }
                });

    }


}
