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
import com.dafa.qipai.dafaqipai.bean.ChongZHiJiLuDo;
import com.dafa.qipai.dafaqipai.bean.TiXIanJiLuDo;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;

import java.math.BigDecimal;
import java.util.List;

public class ChongZhiAdapter extends RecyclerView.Adapter<ChongZhiAdapter.MyViewHolder> {


    private Context context;

    private List<ChongZHiJiLuDo.UserDepositListBean> dtos;

    private int layout;


    public ChongZhiAdapter(Context context, List<ChongZHiJiLuDo.UserDepositListBean> dtos, int layout) {
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        long time = dtos.get(position).getTime();
        BigDecimal money = dtos.get(position).getMoney();
        int status = dtos.get(position).getStatus();
        String remarks = dtos.get(position).getRemarks();
        int type = dtos.get(position).getType();

        String orderNo = dtos.get(position).getOrderNo();


        holder.text4.setText(DateUtil.getString2Y2MD(time) + "\n" + DateUtil.getString2HMS(time));
        holder.text3.setText(money.toPlainString());
        holder.text.setText(orderNo);


        switch (type) {

            case 1:
                holder.text2.setText("银行转账");
                break;
            case 2:
                holder.text2.setText("支付宝转账");
                break;
            case 3:
                holder.text2.setText("微信转账");
                break;
            case 4:
                holder.text2.setText("财付通转账");
                break;
            case 5:
                holder.text2.setText("在线支付");
                break;
            case 6:
                holder.text2.setText("QQ转账");
                break;
            case 7:
                holder.text2.setText("京东转账");
                break;
            case 8:
                holder.text2.setText("百度钱包转账");
                break;
            case 9:
                holder.text2.setText("微信转银行卡");
                break;
            case 10:
                holder.text2.setText("云闪付");
                break;
            case 11:
                holder.text2.setText("支付宝转银行卡");
                break;
        }

        System.out.println(type);

        switch (status) {
            case 0:
                holder.text5.setText("等待支付...");
                break;
            case 1:
                holder.text5.setText("充值成功");
                break;
            case 2:
                holder.text5.setText("充值失败");
                break;
        }


        if (position % 2 == 0) {
            holder.bg.setBackgroundColor(Color.parseColor("#282A36"));
        } else {
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


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {

        void onItemClick(View view);

        void onItemLongClick(View view);
    }
}