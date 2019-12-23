package com.wd.health_home_fragment.presenter;



import com.wd.common.base.BasePresenter;
import com.wd.common.bean.BannerBean;
import com.wd.common.bean.DivisionBean;
import com.wd.common.bean.InformationBean;
import com.wd.common.bean.InformationListBean;
import com.wd.common.constraint.Constraint;
import com.wd.common.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：首页 p 层 <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/5/005<p>
 * <p>更改时间：2019/12/5/005<p>
 */
public class HomePresenter extends BasePresenter<Constraint.IHomeView> {

    //banner
    public void banner() {
        HttpUtils.getInstance().getConstant().BANNER_BEAN()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerBean>() {
                    @Override
                    public void accept(BannerBean bannerBean) throws Exception {
                        getView().IHomeBannerSuccess(bannerBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().IHomeBannerError(throwable.getMessage());
                    }
                });
    }

    //科室
    public void division() {
        HttpUtils.getInstance().getConstant().DIVISION_BEAN()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DivisionBean>() {
                    @Override
                    public void accept(DivisionBean bannerBean) throws Exception {
                        getView().IDivisionSuccess(bannerBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().IDivisionError(throwable.getMessage());
                    }
                });
    }

    //健康资讯分类列表
    public void informationList() {
        HttpUtils.getInstance().getConstant().INFORMATION_LIST_BEAN()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InformationListBean>() {
                    @Override
                    public void accept(InformationListBean informationListBean) throws Exception {
                        getView().IInformationListSuccess(informationListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().IInformationListError(throwable.getMessage());
                    }
                });
    }

    //健康资讯列表

    int page;

    public void information(int plateId, boolean isRefresh) {

        if (isRefresh)
            page = 1;
        else
            page++;

        HttpUtils.getInstance().getConstant().INFORMATION_BEAN(plateId, page, 5)
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

}
