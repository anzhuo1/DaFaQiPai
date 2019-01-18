package com.dafa.qipai.dafaqipai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoDaili;
import com.dafa.qipai.dafaqipai.bean.DoWeixin;
import com.dafa.qipai.dafaqipai.dto.HomeItemDto;
import com.dafa.qipai.dafaqipai.glide.GlideUtil;
import com.dafa.qipai.dafaqipai.util.AutoUtils;

import java.util.List;

public class ChongzhiDailiAdapter extends RecyclerView.Adapter<ChongzhiDailiAdapter.MyViewHolder> {

    private Context context;
    private List<DoDaili.ListBean> dtos;
    private int layout;
    private String imgUrl;


    public ChongzhiDailiAdapter(Context context, List<DoDaili.ListBean> dtos, int layout) {
        this.context = context;
        this.dtos = dtos;
        this.layout = layout;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(v));

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String weixin = dtos.get(position).getWeixin();
        String account = dtos.get(position).getAccount();
        String qq = dtos.get(position).getQq();
        String telephone = dtos.get(position).getPhone();
        String payTreasure = dtos.get(position).getPayTreasure();


        if (!TextUtils.isEmpty(telephone)) {
//            holder.img.setBackgroundResource(R.mipmap.tg_phone);
            holder.num.setText(telephone);
            holder.copy.setText("复制手机号");
        }

        if (!TextUtils.isEmpty(qq)) {
//            holder.img.setBackgroundResource(R.mipmap.tg_qq);
            holder.num.setText(qq);
            holder.copy.setText("复制QQ号");
        }

        if (!TextUtils.isEmpty(weixin)) {
//            holder.img.setBackgroundResource(R.mipmap.tg_weixin);
            holder.num.setText(weixin);
            holder.copy.setText("复制微信号");
        }

        if (!TextUtils.isEmpty(payTreasure)) {
//            holder.img.setBackgroundResource(R.mipmap.tg_weixin);
            holder.num.setText(payTreasure);
            holder.copy.setText("复制支付宝");
        }

        holder.name.setText(dtos.get(position).getAccount());

        imgUrl = dtos.get(position).getImgUrl();

        if (!TextUtils.isEmpty(imgUrl)) {
            GlideUtil.loadUrlImage(imgUrl, holder.img, context);
        }


    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView img;
        TextView name;
        TextView num;
        TextView copy;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            img = (ImageView) view.findViewById(R.id.img);
            num = (TextView) view.findViewById(R.id.num);
            copy = (TextView) view.findViewById(R.id.copy);
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