package com.wd.health_home_fragment.presenter;

import com.wd.common.base.BasePresenter;
import com.wd.common.bean.InformationBean;
import com.wd.common.constraint.Constraint;
import com.wd.common.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/9/009<p>
 * <p>更改时间：2019/12/9/009<p>
 */
public class InformationMorePresenter extends BasePresenter<Constraint.IInformationView> {

    //健康资讯列表

    int page;

    public void information(int plateId, boolean isRefresh) {

        if (isRefresh)
            page = 1;
        else
            page++;

        HttpUtils.getInstance().getConstant().INFORMATION_BEAN(plateId, page, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InformationBean>() {
                    @Override
                    public void accept(InformationBean informationBean) throws Exception {
                        getView().IInformationSuccess(informationBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().IInformationError(throwable.getMessage());
                    }
                });
    }

    public int getPage() {
        return page;
    }
}
