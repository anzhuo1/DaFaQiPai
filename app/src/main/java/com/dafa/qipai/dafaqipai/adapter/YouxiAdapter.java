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
import com.dafa.qipai.dafaqipai.bean.DoMingXi;
import com.dafa.qipai.dafaqipai.bean.DoYuxoi;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;

import java.util.Date;
import java.util.List;

public class YouxiAdapter extends RecyclerView.Adapter<YouxiAdapter.MyViewHolder> {


    private Context context;

    List<DoYuxoi.ListBean> dtos;

    private int layout;


    public YouxiAdapter(Context context, List<DoYuxoi.ListBean> dtos, int layout) {
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


        holder.text.setText(dtos.get(position).getOrderNumber());
        holder.text2.setText("无");
        holder.text3.setText("无");

        long createTime = dtos.get(position).getCreateTime();
        String format = DateUtil.format(new Date(createTime));
        holder.text4.setText(format);

        holder.text5.setText("无");
        holder.text6.setText(dtos.get(position).getZjMoney() + "");


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
        TextView text6;
        LinearLayout bg;

        public MyViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.text);
            text2 = (TextView) view.findViewById(R.id.text2);
            text3 = (TextView) view.findViewById(R.id.text3);
            text4 = (TextView) view.findViewById(R.id.text4);
            text5 = (TextView) view.findViewById(R.id.text5);
            text6 = (TextView) view.findViewById(R.id.text6);
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