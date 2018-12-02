package com.dafa.qipai.dafaqipai.chong;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.adapter.ChongzhiDailiAdapter;
import com.dafa.qipai.dafaqipai.bean.DoDaili;
import com.dafa.qipai.dafaqipai.bean.DoWeixin;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.fra.LazyLoadFragment;
import com.dafa.qipai.dafaqipai.net.OkGoCallBack;
import com.dafa.qipai.dafaqipai.util.AppUtils;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.GsonUtil;
import com.dafa.qipai.dafaqipai.util.UserUtil;
import com.dafa.qipai.dafaqipai.view.KefuActivity;
import com.lzy.okgo.OkGo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChongZhiDailiFragment extends LazyLoadFragment {


    @BindView(R.id.listView)
    RecyclerView listView;
    Unbinder unbinder;
    @BindView(R.id.tousu)
    View tousu;

    @Override
    public int getLayout() {
        return R.layout.fragment_chongzhi_daili;
    }

    @Override
    public void initViews(View view) {

        ButterKnife.bind(this, view);
        AutoUtils.auto(view);


        // listView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));

        listView.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL));


        OkGo.post(ApiConstant.API_DOMAIN + "/agentto/getAgentTopUpList.json")
                .tag(this)
                .params("token", UserUtil.getToken(getActivity()))
                .params("uid", UserUtil.getUserID(getActivity()))
                .execute(new OkGoCallBack(getActivity(), false) {
                    @Override
                    protected void _onNext(String json) {
                        Log.v("生产", json);

                        try {
                            DoDaili doDaili = GsonUtil.GsonToBean(json, DoDaili.class);
                            List<DoDaili.ListBean> list = doDaili.getList();

                            ChongzhiDailiAdapter adapter = new ChongzhiDailiAdapter(getActivity(), list, R.layout.item_daili);
                            listView.setAdapter(adapter);

                            adapter.setOnItemClickListener(new ChongzhiDailiAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view) {

                                    int position = listView.getChildAdapterPosition(view);
                                    TextView viewById = view.findViewById(R.id.copy);
                                    TextView num = view.findViewById(R.id.num);
                                    String numstr = num.getText().toString();
                                    String s = viewById.getText().toString();
                                    if (s.contains("支付宝")) {

                                        openZfb();
                                        AppUtils.copyToClipboard(getActivity(), list.get(position).getPayTreasure());
                                        getActivity().finish();
                                    }

                                    if (s.contains("微信")) {

                                        openWx();
                                        AppUtils.copyToClipboard(getActivity(), list.get(position).getWeixin());
                                        getActivity().finish();
                                    }
                                    if (s.contains("QQ")) {
                                        openQQ(numstr);
                                        AppUtils.copyToClipboard(getActivity(), list.get(position).getQq());

                                      getActivity().finish();
                                    }
                                    if (s.contains("手机")) {

                                        String action = Intent.ACTION_SENDTO;
                                        String phone = numstr;
                                        String sms_body = "你好，我想充值";
                                        Uri uri = Uri.parse("smsto:" + phone);
                                        Intent intent = new Intent(action, uri);
                                        intent.putExtra("sms_body", sms_body);
                                        startActivity(intent);

                                        AppUtils.copyToClipboard(getActivity(), list.get(position).getPhone());
                                        getActivity().finish();
                                    }


                                }

                                @Override
                                public void onItemLongClick(View view) {

                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });


    }

    @Override
    public void loadData() {


    }

    /**
     * 打开微信
     */
    private void openWx() {
        boolean avilible = AppUtils.isWeixinAvilible(context);
        if (avilible) {

            Intent intent = new Intent();
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            startActivity(intent);

        } else {
            Toast.makeText(context, "没有安装微信", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * QQ
     *
     * @param numstr
     */
    private void openQQ(String numstr) {
        boolean avilible = AppUtils.isQQAvilible(context);
        if (avilible) {

            try {
                //第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
                String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + numstr;//uin是发送过去的qq号码
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (Exception e) {
                e.printStackTrace();

            }


        } else {
            Toast.makeText(context, "没有安装QQ", Toast.LENGTH_LONG).show();
        }

    }


    /**
     * 打开支付宝
     */
    private void openZfb() {
        boolean avilible = AppUtils.isZfbAvilible(context);
        if (avilible) {
            PackageManager packageManager = context.getPackageManager();
            Intent it = packageManager.getLaunchIntentForPackage("com.eg.android.AlipayGphone");
            startActivity(it);


        } else {
            Toast.makeText(context, "没有安装支付宝", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tousu)
    public void onViewClicked() {
        gotoActivity(KefuActivity.class);
    }
}
