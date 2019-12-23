package com.wd.health_home_fragment.activity;

import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.wd.common.base.BaseActivity;
import com.wd.common.bean.EvaluatingBean;
import com.wd.common.bean.User;
import com.wd.common.constraint.Constraint;
import com.wd.common.dao.DaoMaster;
import com.wd.common.dao.DaoSession;
import com.wd.common.dao.UserDao;
import com.wd.health_home_fragment.R;
import com.wd.health_home_fragment.presenter.EvaluatingPresenter;

import java.util.List;

public class EvaluatingActivity extends BaseActivity<EvaluatingPresenter> implements Constraint.IEvaluatingView {

    private WebView home_evaluating;
    private String mSessionId;
    private int mUserId;

    @Override
    protected void initData() {

        WebSettings settings = home_evaluating.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        home_evaluating.setWebViewClient(new WebViewClient());

        presenter.evaluating(mUserId, mSessionId);

    }

    @Override
    protected EvaluatingPresenter getpresenter() {
        return new EvaluatingPresenter();
    }

    protected void initView() {
        home_evaluating = (WebView) findViewById(R.id.home_evaluating);
    }

    @Override
    protected void initListener() {

        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        UserDao userDao = daoSession.getUserDao();
        List<User> users = userDao.loadAll();
        mSessionId = users.get(0).getSessionId();
        mUserId = users.get(0).getUserId();

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_evaluating;
    }

    @Override
    public void evaluatingSuccess(EvaluatingBean evaluatingBean) {
        if (evaluatingBean.status.equals("0000")) {

            home_evaluating.loadUrl(evaluatingBean.result);

        }
    }

    @Override
    public void evaluatingError(String s) {

    }
}
