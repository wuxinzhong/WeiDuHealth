package com.wd.health_home_fragment.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.bean.DivisionBean;
import com.wd.health_home_fragment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/6/006<p>
 * <p>更改时间：2019/12/6/006<p>
 */
public class InterrogationAdapter extends RecyclerView.Adapter<InterrogationAdapter.DivisionViewHolder> {

    private List<DivisionBean.ResultBean> mList = new ArrayList<>();

    public void addAll(List<DivisionBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public InterrogationAdapter.DivisionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_information_recycle_item_layout, viewGroup, false);
        return new DivisionViewHolder(view);
    }

    private int position;
    private int j;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onBindViewHolder(@NonNull final InterrogationAdapter.DivisionViewHolder divisionViewHolder, final int i) {

        divisionViewHolder.information_list_name.setText(mList.get(i).departmentName);
        divisionViewHolder.itemView.setTag(i);

        if (mList.get(i).id == getPosition()) {
            divisionViewHolder.information_list_name.setTextColor(Color.parseColor("#3087ea"));
            listener.onClick(mList.get(i).id);
        } else {
            divisionViewHolder.information_list_name.setTextColor(Color.parseColor("#333333"));
        }

        if (i == j) {
            divisionViewHolder.information_list_name.setTextColor(Color.parseColor("#3087ea"));
            listener.onClick(mList.get(j).id);
        } else {
            divisionViewHolder.information_list_name.setTextColor(Color.parseColor("#333333"));
        }



        divisionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j = (int) v.getTag();
                listener.onClick(mList.get(j).id);
                notifyDataSetChanged();
            }
        });

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

    public class DivisionViewHolder extends RecyclerView.ViewHolder {

        private final TextView information_list_name;

        public DivisionViewHolder(@NonNull View itemView) {
            super(itemView);
            information_list_name = itemView.findViewById(R.id.information_list_name);

        }
    }
}
