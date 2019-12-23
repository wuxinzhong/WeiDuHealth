package com.wd.health_login_and_regress.presenter;



import com.wd.common.base.BasePresenter;
import com.wd.common.bean.LoginBean;
import com.wd.common.constraint.Constraint;
import com.wd.common.http.HttpUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述： login_eye  <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/11/011<p>
 * <p>更改时间：2019/12/11/011<p>
 */
public class LoginPresenter extends BasePresenter<Constraint.ILoginView> {

    public void login(String email, String pwd) {

        HttpUtils.getInstance().getConstant().LOGIN_BEAN(email, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        getView().loginSuccess(loginBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().loginError(throwable.getMessage());
                    }
                });

    }

}
