package com.dafa.qipai.dafaqipai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.PublicMsgDo;
import com.dafa.qipai.dafaqipai.bean.TiXIanJiLuDo;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GonggaoAdapter extends RecyclerView.Adapter<GonggaoAdapter.MyViewHolder> {


    private Context context;

    private List<PublicMsgDo.WebNoticeListBean> dtos;

    private int layout;


    public GonggaoAdapter(Context context, List<PublicMsgDo.WebNoticeListBean> dtos, int layout) {
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


        holder.neirong.setText(dtos.get(position).getContent());
        holder.title.setText(dtos.get(position).getTitle());

        holder.shijian.setText(DateUtil.format(new Date(dtos.get(position).getCreateTime())));
    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView neirong;
        TextView shijian;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            neirong = (TextView) view.findViewById(R.id.neirong);
            shijian = (TextView) view.findViewById(R.id.shijian);
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