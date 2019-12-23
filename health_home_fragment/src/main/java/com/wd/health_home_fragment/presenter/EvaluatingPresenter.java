package com.wd.health_home_fragment.presenter;



import com.wd.common.base.BasePresenter;
import com.wd.common.bean.EvaluatingBean;
import com.wd.common.constraint.Constraint;
import com.wd.common.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/12/012<p>
 * <p>更改时间：2019/12/12/012<p>
 */
public class EvaluatingPresenter extends BasePresenter<Constraint.IEvaluatingView> {

    public void evaluating(int userId,String sessionId){

        HttpUtils.getInstance().getConstant().EVALUATING_BEAN(userId, sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EvaluatingBean>() {
                    @Override
                    public void accept(EvaluatingBean evaluatingBean) throws Exception {
                        getView().evaluatingSuccess(evaluatingBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().evaluatingError(throwable.getMessage());
                    }
                });

    }

}
