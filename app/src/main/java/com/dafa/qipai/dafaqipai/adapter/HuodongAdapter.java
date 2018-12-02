package com.dafa.qipai.dafaqipai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoGetPromotion;
import com.dafa.qipai.dafaqipai.core.ApiConstant;
import com.dafa.qipai.dafaqipai.dto.HomeItemDto;
import com.dafa.qipai.dafaqipai.glide.GlideUtil;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.view.HuoDongActivity;

import java.util.List;

public class HuodongAdapter extends RecyclerView.Adapter<HuodongAdapter.MyViewHolder> {


    private Context context;

    private List<DoGetPromotion.PromotionListBean> dtos;

    private int layout;


    public HuodongAdapter(Context context, List<DoGetPromotion.PromotionListBean> dtos, int layout) {
        this.context = context;
        this.dtos = dtos;
        this.layout = layout;
    }

    @Override
    public HuodongAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        HuodongAdapter.MyViewHolder holder = new HuodongAdapter.MyViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));

        holder.itemView.setOnClickListener(v ->
                onItemClickListener.onItemClick(v));

        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.tv.setText(dtos.get(position).getName());

        if(dtos.get(position).isIschecked()){
            holder.tv.setBackgroundResource(R.mipmap.ansebg_press);
            holder.tv.setTextColor(context.getResources().getColor(R.color.bg_line));

        }else {
            holder.tv.setBackgroundResource(R.mipmap.ansebg);
            holder.tv.setTextColor(context.getResources().getColor(R.color.colorTextWhite));
        }


    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);


            AutoUtils.auto(view);
        }
    }


    private HuodongAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(HuodongAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public  interface OnItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }
}