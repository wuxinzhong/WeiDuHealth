package com.wd.health_home_fragment.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.bean.DoctorBean;
import com.wd.health_home_fragment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/19/019<p>
 * <p>更改时间：2019/12/19/019<p>
 */
public class DoctorRecycleAdapter extends RecyclerView.Adapter<DoctorRecycleAdapter.DoctorViewHolder> {

    private List<DoctorBean.ResultBean> mList = new ArrayList<>();

    public void addAll(List<DoctorBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public DoctorRecycleAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doctor_bottom_item_layout, viewGroup, false);
        return new DoctorViewHolder(view);
    }

    private int position;

    @Override
    public void onBindViewHolder(@NonNull DoctorRecycleAdapter.DoctorViewHolder doctorViewHolder, int i) {
        doctorViewHolder.doctor_list_check.setText(mList.get(i).doctorName);
        Uri uri = Uri.parse(mList.get(i).imagePic);
        doctorViewHolder.doctor_list_img.setImageURI(uri);
        doctorViewHolder.itemView.setTag(i);

        if (i == position) {

            doctorViewHolder.doctor_list_check.setChecked(true);
            listener.onClick(mList.get(position).doctorId);

        } else {
            doctorViewHolder.doctor_list_check.setChecked(false);
        }


        doctorViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = (int) v.getTag();
                listener.onClick(mList.get(position).doctorId);
                notifyDataSetChanged();
            }
        });
    }

    public void clean() {
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

    public class DoctorViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox doctor_list_check;
        private final SimpleDraweeView doctor_list_img;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            doctor_list_check = itemView.findViewById(R.id.doctor_list_check);
            doctor_list_img = itemView.findViewById(R.id.doctor_list_img);

        }
    }
}
