package com.wd.health_home_fragment.presenter;



import com.wd.common.base.BasePresenter;
import com.wd.common.bean.DivisionBean;
import com.wd.common.bean.DoctorBean;
import com.wd.common.constraint.Constraint;
import com.wd.common.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Query;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/13/013<p>
 * <p>更改时间：2019/12/13/013<p>
 */
public class DivisionPresenter extends BasePresenter<Constraint.IDivisionView> {

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

    //医生列表

    int page;

    public void doctor(int deptId, int condition, int sortBy, boolean isRefresh, int count) {

        if (isRefresh)
            page = 1;
        else
            page++;

        HttpUtils.getInstance().getConstant().DOCTOR_BEAN(deptId, condition, sortBy, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DoctorBean>() {
                    @Override
                    public void accept(DoctorBean doctorBean) throws Exception {
                        getView().IDoctorSuccess(doctorBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().IDoctorError(throwable.getMessage());
                    }
                });
    }

}
