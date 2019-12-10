package com.wd.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wd.common.R;
import com.wd.common.constraint.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/4/004<p>
 * <p>更改时间：2019/12/4/004<p>
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    public P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(initLayout(), container, false);
        initView(view);
        initListener();
        presenter = getPresenter();
        presenter.attachView(this);
        initData();
        return view;
    }

    protected abstract void initData();

    protected abstract P getPresenter();

    protected abstract void initListener();

    protected abstract void initView(View view);

    protected abstract int initLayout();


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
