package com.dafa.qipai.dafaqipai.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.TiXIanJiLuDo;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;

import java.math.BigDecimal;
import java.util.List;

public class TiXianAdapter extends RecyclerView.Adapter<TiXianAdapter.MyViewHolder> {


    private Context context;

    private List<TiXIanJiLuDo.UserWithdrawListBean> dtos;

    private int layout;


    public TiXianAdapter(Context context, List<TiXIanJiLuDo.UserWithdrawListBean> dtos, int layout) {
        this.context = context;
        this.dtos = dtos;
        this.layout = layout;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClick(v);
//            }
//        });

        return holder;
    }

    @Override
    public void onBindViewHolder(TiXianAdapter.MyViewHolder holder, int position) {
        long time = dtos.get(position).getTime();
        BigDecimal money = dtos.get(position).getMoney();
        int status = dtos.get(position).getStatus();
        String remarks = dtos.get(position).getRemarks();
        int type = dtos.get(position).getType();

        String orderNo = dtos.get(position).getOrderNo();


        holder.text4.setText(DateUtil.getString2Y2MD(time) + "\n" + DateUtil.getString2HMS(time));
        holder.text3.setText(money.toPlainString());
        holder.text.setText(orderNo);

        switch (status) {
            case 0:
                holder.text5.setText("正在出款...");

                break;
            case 1:
                holder.text5.setText("出款成功");

                break;
            case 2:
                holder.text5.setText("出款拒绝");

                break;
        }
        holder.text2.setText("提现到银行卡");

        if (position % 2 == 0) {
            holder.bg.setBackgroundColor(Color.parseColor("#282A36"));
        }else {
            holder.bg.setBackgroundColor(Color.parseColor("#2B2F3A"));

        }

    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        TextView text2;
        TextView text3;
        TextView text4;
        TextView text5;
        LinearLayout bg;


        public MyViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.text);
            text2 = (TextView) view.findViewById(R.id.text2);
            text3 = (TextView) view.findViewById(R.id.text3);
            text4 = (TextView) view.findViewById(R.id.text4);
            text5 = (TextView) view.findViewById(R.id.text5);
            bg = (LinearLayout) view.findViewById(R.id.bg);
            AutoUtils.auto(view);
        }
    }


    private ChongZhiAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(ChongZhiAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }
}