package com.dafa.qipai.dafaqipai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));

//        holder.itemView.setOnClickListener(v ->
//                onItemClickListener.onItemClick(v));

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImageView iv = holder.iv;

        String url = ApiConstant.API_DOMAIN + "/image/" + dtos.get(position).getBigImageId() + ".png?companyShortName=600w";
        GlideUtil.loadUrlImage(url, iv, context);

        holder.wv.loadData(dtos.get(position).getContent(), "text/html; charset=UTF-8", null);

        if (dtos.get(position).isShow()) {

            holder.wv.setVisibility(View.VISIBLE);
        } else {
            holder.wv.setVisibility(View.GONE);
        }

        iv.setOnClickListener(v -> {

            if (dtos.get(position).isShow()) {
                dtos.get(position).setShow(false);
            } else {
                for (DoGetPromotion.PromotionListBean d : dtos) {
                    d.setShow(false);
                }

                dtos.get(position).setShow(true);
            }

            HuodongAdapter.this.notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        WebView wv;

        public MyViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.iv);
            wv = (WebView) view.findViewById(R.id.content);
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