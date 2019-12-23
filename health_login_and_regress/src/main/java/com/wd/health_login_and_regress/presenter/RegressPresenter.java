package com.wd.health_login_and_regress.presenter;

import com.wd.common.constraint.Constraint;
import com.wd.common.base.BasePresenter;
import com.wd.common.bean.RegressBean;
import com.wd.common.bean.SendEmailBean;
import com.wd.common.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述： 注册   <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/11/011<p>
 * <p>更改时间：2019/12/11/011<p>
 */
public class RegressPresenter extends BasePresenter<Constraint.IRegressView> {

    //注册
    public void regress(String email, String code, String pwd1, String pwd2, String invitationCode) {
        HttpUtils.getInstance().getConstant().REGRESS_BEAN(email, code, pwd1, pwd2, invitationCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegressBean>() {
                    @Override
                    public void accept(RegressBean regressBean) throws Exception {
                        getView().regressSuccess(regressBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().regressError(throwable.getMessage());
                    }
                });
    }

    //发送邮箱验证码
    public void sendEmail(String email) {
        HttpUtils.getInstance().getConstant().SEND_EMAIL_BEAN(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SendEmailBean>() {
                    @Override
                    public void accept(SendEmailBean regressBean) throws Exception {
                        getView().sendEmailSuccess(regressBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().sendEmailError(throwable.getMessage());
                    }
                });
    }

}
