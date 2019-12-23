package com.wd.health_home_fragment.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.wd.common.bean.DrugBean;
import com.wd.health_home_fragment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/5/005<p>
 * <p>更改时间：2019/12/5/005<p>
 */
public class LeftDrugAdapter extends RecyclerView.Adapter<LeftDrugAdapter.LeftViewHolder> {

    private List<DrugBean.ResultBean> mList = new ArrayList<>();

    public void addAll(List<DrugBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public LeftDrugAdapter.LeftViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.left_adapter_layout, viewGroup, false);
        return new LeftViewHolder(view);
    }

    int posison;

    @Override
    public void onBindViewHolder(@NonNull final LeftDrugAdapter.LeftViewHolder leftViewHolder, final int i) {
        leftViewHolder.left_text.setText(mList.get(i).name);
        leftViewHolder.itemView.setTag(i);

        if (i == posison) {

            leftViewHolder.view_blue.setVisibility(View.VISIBLE);
            leftViewHolder.left_lin.setBackgroundColor(Color.WHITE);
            leftViewHolder.left_text.setTextColor(Color.parseColor("#3087ea"));

            listener.onClick(mList.get(posison).id);

        } else {
            leftViewHolder.view_blue.setVisibility(View.GONE);
            leftViewHolder.left_lin.setBackgroundColor(Color.parseColor("#f2f2f2"));
            leftViewHolder.left_text.setTextColor(Color.parseColor("#000000"));
        }
        leftViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posison = (int) v.getTag();
                listener.onClick(mList.get(posison).id);
                notifyDataSetChanged();
            }
        });
    }

    public void clear() {
        mList.clear();
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder {

        private final TextView left_text;
        private final RelativeLayout left_lin;
        private final View view_blue;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            left_text = itemView.findViewById(R.id.left_text);
            left_lin = itemView.findViewById(R.id.left_lin);
            view_blue = itemView.findViewById(R.id.view_blue);
        }
    }
}
