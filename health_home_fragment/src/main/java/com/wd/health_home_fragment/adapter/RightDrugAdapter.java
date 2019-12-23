package com.wd.health_home_fragment.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import com.wd.common.bean.CommonDrugBean;
import com.wd.health_home_fragment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/6/006<p>
 * <p>更改时间：2019/12/6/006<p>
 */
public class RightDrugAdapter extends RecyclerView.Adapter<RightDrugAdapter.RightViewHolder> {

    private List<CommonDrugBean.ResultBean> mList = new ArrayList<>();

    public void addAll(List<CommonDrugBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public RightDrugAdapter.RightViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.right_adapter_drug_layout, viewGroup, false);
        return new RightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RightDrugAdapter.RightViewHolder rightViewHolder, final int i) {
        rightViewHolder.right_text.setText(mList.get(i).name);

        Uri uri = Uri.parse(mList.get(i).picture);
        rightViewHolder.right_img.setImageURI(uri);

        rightViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(mList.get(i).id,mList.get(i).name);
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

    public class RightViewHolder extends RecyclerView.ViewHolder {

        private final TextView right_text;
        private final SimpleDraweeView right_img;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            right_text = itemView.findViewById(R.id.right_text);
            right_img = itemView.findViewById(R.id.right_img);
        }
    }
}
