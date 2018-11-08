package com.dafa.qipai.dafaqipai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DOgetAppCzInfoResult;
import com.dafa.qipai.dafaqipai.dto.HomeItemDto;
import com.dafa.qipai.dafaqipai.util.AutoUtils;

import java.util.List;

public class ChongZhiTongDaoAdapter extends RecyclerView.Adapter<ChongZhiTongDaoAdapter.MyViewHolder> {

    private Context context;

    private List<DOgetAppCzInfoResult.CzTypeListBean.CzListBean> dtos;

    private int layout;


    public ChongZhiTongDaoAdapter(Context context, List<DOgetAppCzInfoResult.CzTypeListBean.CzListBean> dtos, int layout) {
        this.context = context;
        this.dtos = dtos;
        this.layout = layout;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v);

                }

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int payType = dtos.get(position).getPayType();
        if (payType == 1 || payType == 9) {  //微信
            holder.t1.setText("微信");

        }

        if (payType == 2 || payType == 11) {  //微信
            holder.t1.setText("支付宝");

        }

        if (payType == 4) {  //微信
            holder.t1.setText("网银");

        }

        if (position == 0) {
            holder.t2.setText("充值通道一");
        }
        if (position == 1) {
            holder.t2.setText("充值通道二");
        }
        if (position == 2) {
            holder.t2.setText("充值通道三");
        }
        if (position == 3) {
            holder.t2.setText("充值通道四");
        }
        if (position == 4) {
            holder.t2.setText("充值通道五");
        }
        if (position == 5) {
            holder.t2.setText("充值通道六");
        }


        boolean check = dtos.get(position).isCheck();
        if (check) {
            holder.bg.setBackgroundResource(R.mipmap.zfbtd_press);
            holder.t1.setTextColor(context.getResources().getColor(R.color.black));
            holder.t2.setTextColor(context.getResources().getColor(R.color.black));
        } else {
            holder.bg.setBackgroundResource(R.mipmap.zfbtd);
            holder.t1.setTextColor(context.getResources().getColor(R.color.text_hint_color));
            holder.t2.setTextColor(context.getResources().getColor(R.color.text_hint_color));

        }



    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView t1;
        TextView t2;
        LinearLayout bg;

        public MyViewHolder(View view) {
            super(view);
            t1 = (TextView) view.findViewById(R.id.t1);
            t2 = (TextView) view.findViewById(R.id.t2);
            bg = (LinearLayout) view.findViewById(R.id.bg);
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