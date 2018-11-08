package com.dafa.qipai.dafaqipai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DOgetAppCzInfoResult;
import com.dafa.qipai.dafaqipai.dto.HomeItemDto;
import com.dafa.qipai.dafaqipai.util.AutoUtils;

import java.util.List;

public class ChongZhiLeftAdapter extends RecyclerView.Adapter<ChongZhiLeftAdapter.MyViewHolder> {

    private Context context;

    private List<DOgetAppCzInfoResult.CzTypeListBean> dtos;

    private int layout;


    public ChongZhiLeftAdapter(Context context, List<DOgetAppCzInfoResult.CzTypeListBean> dtos, int layout) {
        this.context = context;
        this.dtos = dtos;
        this.layout = layout;

        DOgetAppCzInfoResult.CzTypeListBean czTypeListBean = new DOgetAppCzInfoResult.CzTypeListBean();
        czTypeListBean.setName("代理");
        dtos.add(czTypeListBean);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v);

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        boolean select = dtos.get(position).isSelect();

        if (dtos.get(position).getName().equals("银行卡支付")) {
            if (dtos.get(position).isSelect()) {
                holder.iv.setBackgroundResource(R.mipmap.wyzf_press);
            }else {
                holder.iv.setBackgroundResource(R.mipmap.wycz);
            }
        }
        if (dtos.get(position).getName().equals("微信")) {
            if (dtos.get(position).isSelect()) {
                holder.iv.setBackgroundResource(R.mipmap.wxzf_press);
            }else {
                holder.iv.setBackgroundResource(R.mipmap.weixincz);
            }
        }
        if (dtos.get(position).getName().equals("支付宝")) {
            if (dtos.get(position).isSelect()) {
                holder.iv.setBackgroundResource(R.mipmap.zfbcz_press);
            }else {
                holder.iv.setBackgroundResource(R.mipmap.zfbcz);
            }
        }

        if (dtos.get(position).getName().equals("代理")) {
            if (dtos.get(position).isSelect()) {
                holder.iv.setBackgroundResource(R.mipmap.dlcz_press);
            }else {
                holder.iv.setBackgroundResource(R.mipmap.dailicz);
            }
        }



    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.iv);
            AutoUtils.auto(view);
        }
    }


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }
}