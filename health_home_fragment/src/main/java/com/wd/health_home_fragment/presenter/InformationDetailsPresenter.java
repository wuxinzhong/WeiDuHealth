package com.wd.health_home_fragment.presenter;


import com.wd.common.base.BasePresenter;
import com.wd.common.bean.InformationDetailsBean;
import com.wd.common.constraint.Constraint;
import com.wd.common.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：  健康资讯详情P层  <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/9/009<p>
 * <p>更改时间：2019/12/9/009<p>
 */
public class InformationDetailsPresenter extends BasePresenter<Constraint.IInformationDetailView> {

    public void informationDetails(int userId, String sessionId, int infoId) {

        HttpUtils.getInstance().getConstant().INFORMATION_DETAILS_BEAN(userId, sessionId, infoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InformationDetailsBean>() {
                    @Override
                    public void accept(InformationDetailsBean informationDetailsBean) throws Exception {
                        if (informationDetailsBean == null)
                            return;
                        getView().IInformationDetailsSuccess(informationDetailsBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().IInformationDetailsError(throwable.getMessage());
                    }
                });

    }

}
