package com.wd.health_home_fragment.presenter;

import com.wd.common.base.BasePresenter;
import com.wd.common.bean.DrugDetailsBean;
import com.wd.common.constraint.Constraint;
import com.wd.common.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/6/006<p>
 * <p>更改时间：2019/12/6/006<p>
 */
public class DrugDetailsPresenter extends BasePresenter<Constraint.IDrugThree> {

    public void drugDetails(int id) {
        HttpUtils.getInstance().getConstant().DRUG_DETAILS_BEAN(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DrugDetailsBean>() {
                    @Override
                    public void accept(DrugDetailsBean drugDetailsBean) throws Exception {
                        getView().IDrugSuccess(drugDetailsBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().IDrugError(throwable.getMessage());
                    }
                });
    }

}
