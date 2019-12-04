package com.wd.health_home.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health_home.R;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/4/004<p>
 * <p>更改时间：2019/12/4/004<p>
 */
public class BootViewPageAdapter extends PagerAdapter {

    private List<Integer> mList;

    public BootViewPageAdapter(List<Integer> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //添加图片到布局中
        View view = View.inflate(container.getContext(), R.layout.boot_page_adapter_layout, null);
//        SimpleDraweeView img = view.findViewById(R.id.boot_img);
//        Uri uri = Uri.parse("res/drawable"+mList.get(position));
//        img.setImageURI(uri);

        ImageView img = view.findViewById(R.id.boot_img);
        Glide.with(container.getContext()).load(mList.get(position)).into(img);
        container.addView(view);
        return view;
    }
}
