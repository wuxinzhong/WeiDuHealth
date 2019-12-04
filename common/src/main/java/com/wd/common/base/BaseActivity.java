package com.wd.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wd.common.constraint.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/4/004<p>
 * <p>更改时间：2019/12/4/004<p>
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView();
        initListener();
        presenter = getpresenter();
        presenter.attachView(this);
        initData();

    }

    protected abstract void initData();

    protected abstract P getpresenter();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract int initLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
