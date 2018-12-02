package com.dafa.qipai.dafaqipai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafa.qipai.dafaqipai.R;
import com.dafa.qipai.dafaqipai.bean.DoSysCard;
import com.dafa.qipai.dafaqipai.dto.HomeItemDto;
import com.dafa.qipai.dafaqipai.util.AutoUtils;
import com.dafa.qipai.dafaqipai.util.BankUtil;

import java.util.List;

public class WangYinAdapter extends RecyclerView.Adapter<WangYinAdapter.MyViewHolder> {


    private Context context;

    private List<DoSysCard.BankcardListBean> dtos;

    private int layout;


    public WangYinAdapter(Context context, List<DoSysCard.BankcardListBean> dtos, int layout) {
        this.context = context;
        this.dtos = dtos;
        this.layout = layout;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));

         holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(v));

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String bankName = dtos.get(position).getBankName();
        holder.iv.setImageResource(BankUtil.Bank.getResIdByName(bankName));

        holder.name.setText(dtos.get(position).getBankName());
        String bankAccount = dtos.get(position).getBankAccount();

        holder.kahao.setText(bankAccount.replaceAll("\\d{4}(?!$)", "$0 "));

       // holder.chongzhi.setOnClickListener(v -> onItemClickListener.onItemClick(v));

        holder.copy.setOnClickListener(v -> onItemClickListener.onItemLongClick(v));

        System.out.println(bankName);

    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView name;
        TextView kahao;
        TextView copy;
        TextView chongzhi;

        public MyViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.iv);
            name = (TextView) view.findViewById(R.id.name);
            kahao = (TextView) view.findViewById(R.id.kahao);
            chongzhi = (TextView) view.findViewById(R.id.chongzhi);
            copy = (TextView) view.findViewById(R.id.copy);
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