package com.dafa.qipai.dafaqipai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.ChongZHiJiLuDo;
import com.dafa.qipai.dafaqipai.bean.DoMingXi;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CunQuAdapter extends RecyclerView.Adapter<CunQuAdapter.MyViewHolder> {


    private Context context;

    private List<DoMingXi.ListBean> dtos;

    private int layout;


    public CunQuAdapter(Context context, List<DoMingXi.ListBean> dtos, int layout) {
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

        String format = DateUtil.format(new Date(dtos.get(position).getCreateTime()));

        holder.shijian.setText(format);

        if (dtos.get(position).getType().equals("deposit")) {
            holder.leixing.setText("存入");

        } else {
            holder.leixing.setText("取出");

        }
        holder.jine.setText(dtos.get(position).getOperationMoney().toPlainString());
        holder.yue.setText(dtos.get(position).getBalance().toPlainString());


    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView shijian;
        TextView leixing;
        TextView jine;
        TextView yue;

        public MyViewHolder(View view) {
            super(view);
            shijian = (TextView) view.findViewById(R.id.shijian);
            leixing = (TextView) view.findViewById(R.id.leixing);
            jine = (TextView) view.findViewById(R.id.jine);
            yue = (TextView) view.findViewById(R.id.yue);
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