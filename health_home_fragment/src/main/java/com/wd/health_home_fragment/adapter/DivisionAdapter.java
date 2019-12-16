package com.wd.health_home_fragment.adapter;

import android.net.Uri;
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
public class DivisionAdapter extends RecyclerView.Adapter<DivisionAdapter.DivisionViewHolder> {

    private List<DivisionBean.ResultBean> mList = new ArrayList<>();

    public void addAll(List<DivisionBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public DivisionAdapter.DivisionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_division_layout, viewGroup, false);
        return new DivisionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DivisionAdapter.DivisionViewHolder divisionViewHolder, final int i) {

        Uri uri = Uri.parse(mList.get(i).pic);
        divisionViewHolder.division_img.setImageURI(uri);
        divisionViewHolder.division_name.setText(mList.get(i).departmentName);

        divisionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(mList.get(i).id);
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

        private final SimpleDraweeView division_img;
        private final TextView division_name;

        public DivisionViewHolder(@NonNull View itemView) {
            super(itemView);
            division_img = itemView.findViewById(R.id.division_img);
            division_name = itemView.findViewById(R.id.division_name);

        }
    }
}
