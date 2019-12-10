package com.wd.health_home_fragment.presenter;

import com.wd.common.base.BasePresenter;
import com.wd.common.bean.CommonDrugBean;
import com.wd.common.bean.DiseaseBean;
import com.wd.common.bean.DivisionBean;
import com.wd.common.bean.DrugBean;
import com.wd.common.constraint.Constraint;
import com.wd.common.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/5/005<p>
 * <p>更改时间：2019/12/5/005<p>
 */
public class HomeTwoPresenter extends BasePresenter<Constraint.IHomeTow> {

    //查询科室列表
    public void Division() {
        HttpUtils.getInstance().getConstant().DIVISION_BEAN()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DivisionBean>() {
                    @Override
                    public void accept(DivisionBean divisionBean) throws Exception {
                        getView().IDivisionSuccess(divisionBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().IDivisionError(throwable.getMessage());
                    }
                });
    }

    //根据科室查询对应病症
    public void Disease(int departmentId) {
        HttpUtils.getInstance().getConstant().DISEASE_BEAN(departmentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DiseaseBean>() {
                    @Override
                    public void accept(DiseaseBean divisionBean) throws Exception {
                        getView().IDiseaseSuccess(divisionBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().IDiseaseError(throwable.getMessage());
                    }
                });
    }

    //药品科室分类列表
    public void Drug() {
        HttpUtils.getInstance().getConstant().DRUG_BEAN()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DrugBean>() {
                    @Override
                    public void accept(DrugBean divisionBean) throws Exception {
                        getView().IDrugSuccess(divisionBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().IDrugError(throwable.getMessage());
                    }
                });
    }

    private int page;

    //药品科室分类列表对应列表
    public void CommonDrug(int drugsCategoryId, boolean isRefresh) {

        if (isRefresh)
            page = 1;
        else
            page++;

        HttpUtils.getInstance().getConstant().COMMON_DRUG_BEAN(drugsCategoryId,page,20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommonDrugBean>() {
                    @Override
                    public void accept(CommonDrugBean divisionBean) throws Exception {
                        getView().ICommonDrugSuccess(divisionBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().ICommonDrugError(throwable.getMessage());
                    }
                });
    }

}
