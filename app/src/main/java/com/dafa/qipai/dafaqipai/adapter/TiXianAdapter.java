package com.dafa.qipai.dafaqipai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        long time = dtos.get(position).getTime();
        BigDecimal money = dtos.get(position).getMoney();
        int status = dtos.get(position).getStatus();
        String remarks = dtos.get(position).getRemarks();
        holder.tv.setText(DateUtil.getString2Y2MD(time) + "\n" + DateUtil.getString2HMS(time));
        holder.money.setText(money.toPlainString());

        switch (status) {
            case 0:
                holder.status.setText("正在出款...");
                break;
            case 1:
                holder.status.setText("出款成功");
                break;
            case 2:
                holder.status.setText("出款拒绝");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        TextView money;
        TextView status;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.text);
            money = (TextView) view.findViewById(R.id.money);
            status = (TextView) view.findViewById(R.id.status);
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