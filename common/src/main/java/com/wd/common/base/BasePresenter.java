package com.wd.common.base;

import com.wd.common.constraint.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/4/004<p>
 * <p>更改时间：2019/12/4/004<p>
 */
public class BasePresenter<V extends IBaseView> {

    public V mV;

    public void attachView(V v) {
        mV = v;
    }

    public void detachView() {
        mV = null;
    }

    public V getView() {
        return mV;
    }

}
