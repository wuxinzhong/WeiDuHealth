package com.wd.health_home_fragment.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wd.health_home_fragment.R;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/5/005<p>
 * <p>更改时间：2019/12/5/005<p>
 */
public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_layout, container, false);
        return view;
    }
}
