package com.wd.health_home_fragment.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.bean.InformationBean;
import com.wd.health_home_fragment.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/7/007<p>
 * <p>更改时间：2019/12/7/007<p>
 */
public class InformationAdapter extends RecyclerView.Adapter {

    private List<InformationBean.ResultBean> mList = new ArrayList<>();

    public void addAll(List<InformationBean.ResultBean> mList1) {
        if (mList1 != null)
            this.mList.addAll(mList1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == 0) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_img_null_layout, viewGroup, false);
            return new ImgNullViewHolder(view);

        } else if (i == 2) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_img_three_layout, viewGroup, false);
            return new ImgThreeViewHolder(view);

        } else if (i == 3) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_img_one_layout, viewGroup, false);
            return new ImgOneViewHolder(view);

        } else {
            return null;
        }
    }


    @Override
    public int getItemViewType(int position) {
        InformationBean.ResultBean resultBean = mList.get(position);
        String[] split = resultBean.thumbnail.split(";");
        int imgSize = split.length;
        if (imgSize == 0) {
            return 0;
        } else if (imgSize == 3) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        int viewType = viewHolder.getItemViewType();
        InformationBean.ResultBean resultBean = mList.get(i);

        String[] split = resultBean.thumbnail.split(";");


        Date date = new Date(resultBean.releaseTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (viewType == 0) {

            ImgNullViewHolder imgNullViewHolder = (ImgNullViewHolder) viewHolder;
            imgNullViewHolder.information_img_null_title.setText(resultBean.title);
            imgNullViewHolder.information_img_null_source.setText(resultBean.source);

            imgNullViewHolder.information_img_null_releaseTime.setText(simpleDateFormat.format(date));

            imgNullViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(mList.get(i).id);
                }
            });

        } else if (viewType == 2) {

            ImgThreeViewHolder imgThreeViewHolder = (ImgThreeViewHolder) viewHolder;
            imgThreeViewHolder.information_img_three_title.setText(resultBean.title);
            imgThreeViewHolder.information_img_three_source.setText(resultBean.source);
            imgThreeViewHolder.information_img_three_releaseTime.setText(simpleDateFormat.format(date));

            Uri uri = Uri.parse(split[0]);
            imgThreeViewHolder.information_img_three_img1.setImageURI(uri);

            Uri uri1 = Uri.parse(split[1]);
            imgThreeViewHolder.information_img_three_img2.setImageURI(uri1);

            Uri uri2 = Uri.parse(split[2]);
            imgThreeViewHolder.information_img_three_img3.setImageURI(uri2);


            imgThreeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(mList.get(i).id);
                }
            });


        } else if (viewType == 3) {

            ImgOneViewHolder imgOneViewHolder = (ImgOneViewHolder) viewHolder;
            imgOneViewHolder.information_img_one_title.setText(resultBean.title);
            imgOneViewHolder.information_img_one_source.setText(resultBean.source);
            imgOneViewHolder.information_img_one_releaseTime.setText(simpleDateFormat.format(date));

            Uri uri = Uri.parse(split[0]);
            imgOneViewHolder.information_img_one_img.setImageURI(uri);

            imgOneViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(mList.get(i).id);
                }
            });
        }
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

    public void clear() {
        mList.clear();
    }

    public class ImgNullViewHolder extends RecyclerView.ViewHolder {

        private final TextView information_img_null_title;
        private final TextView information_img_null_source;
        private final TextView information_img_null_releaseTime;

        public ImgNullViewHolder(@NonNull View itemView) {
            super(itemView);
            information_img_null_title = itemView.findViewById(R.id.information_img_null_title);
            information_img_null_source = itemView.findViewById(R.id.information_img_null_source);
            information_img_null_releaseTime = itemView.findViewById(R.id.information_img_null_releaseTime);
        }
    }

    public class ImgOneViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView information_img_one_img;
        private final TextView information_img_one_title;
        private final TextView information_img_one_releaseTime;
        private final TextView information_img_one_source;

        public ImgOneViewHolder(@NonNull View itemView) {
            super(itemView);
            information_img_one_title = itemView.findViewById(R.id.information_img_one_title);
            information_img_one_img = itemView.findViewById(R.id.information_img_one_img);
            information_img_one_releaseTime = itemView.findViewById(R.id.information_img_one_releaseTime);
            information_img_one_source = itemView.findViewById(R.id.information_img_one_source);
        }
    }

    public class ImgThreeViewHolder extends RecyclerView.ViewHolder {

        private final TextView information_img_three_title;
        private final TextView information_img_three_source;
        private final TextView information_img_three_releaseTime;
        private final SimpleDraweeView information_img_three_img1;
        private final SimpleDraweeView information_img_three_img2;
        private final SimpleDraweeView information_img_three_img3;

        public ImgThreeViewHolder(@NonNull View itemView) {
            super(itemView);
            information_img_three_title = itemView.findViewById(R.id.information_img_three_title);
            information_img_three_source = itemView.findViewById(R.id.information_img_three_source);
            information_img_three_releaseTime = itemView.findViewById(R.id.information_img_three_releaseTime);
            information_img_three_img1 = itemView.findViewById(R.id.information_img_three_img1);
            information_img_three_img2 = itemView.findViewById(R.id.information_img_three_img2);
            information_img_three_img3 = itemView.findViewById(R.id.information_img_three_img3);
        }
    }
}
