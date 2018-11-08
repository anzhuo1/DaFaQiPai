package com.dafa.qipai.dafaqipai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.ChongZHiJiLuDo;
import com.dafa.qipai.dafaqipai.bean.ZhanNeiXinDo;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ZhanNeiXinAdapter extends RecyclerView.Adapter<ZhanNeiXinAdapter.MyViewHolder> {


    private Context context;

    private List<ZhanNeiXinDo.UserInboxBean> dtos;

    private int layout;


    public ZhanNeiXinAdapter(Context context, List<ZhanNeiXinDo.UserInboxBean> dtos, int layout) {
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
                onItemClickListener.onItemClick(v);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.time.setText(DateUtil.format(new Date(dtos.get(position).getCreateTime())));

        holder.check.setChecked(dtos.get(position).isTast());

        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dtos.get(position).setTast(isChecked);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView time;
        CheckBox check;


        public MyViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.time);
            check = (CheckBox) view.findViewById(R.id.checkbox);

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