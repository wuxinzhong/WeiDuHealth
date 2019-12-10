package com.wd.health_home_fragment.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.common.bean.InformationListBean;
import com.wd.health_home_fragment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/7/007<p>
 * <p>更改时间：2019/12/7/007<p>
 */
public class InformationListAdapter extends RecyclerView.Adapter<InformationListAdapter.InformationViewHolder> {

    private List<InformationListBean.ResultBean> mList = new ArrayList<>();

    public void addAll(List<InformationListBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public InformationListAdapter.InformationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_information_recycle_item_layout, viewGroup, false);
        return new InformationViewHolder(view);
    }

    int position;

    @Override
    public void onBindViewHolder(@NonNull InformationListAdapter.InformationViewHolder informationViewHolder, final int i) {
        informationViewHolder.information_list_name.setText(mList.get(i).name);

        informationViewHolder.itemView.setTag(i);

        if (i == position) {

            informationViewHolder.information_list_name.setTextColor(Color.parseColor("#3087ea"));
            listener.onClick(mList.get(position).id,mList.get(i).name);

        } else {

            informationViewHolder.information_list_name.setTextColor(Color.parseColor("#333333"));

        }

        informationViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = (int) v.getTag();
                listener.onClick(mList.get(position).id,mList.get(i).name);
                notifyDataSetChanged();
            }
        });

    }

    public void clear() {
        mList.clear();
    }

    public interface OnItemClickListener {
        void onClick(int position,String name);
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

    public class InformationViewHolder extends RecyclerView.ViewHolder {

        private final TextView information_list_name;

        public InformationViewHolder(@NonNull View itemView) {
            super(itemView);
            information_list_name = itemView.findViewById(R.id.information_list_name);
        }
    }
}
