package com.wd.health_home_fragment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.BaseActivity;
import com.wd.common.bean.InformationDetailsBean;
import com.wd.common.bean.User;
import com.wd.common.constraint.Constraint;
import com.wd.common.dao.DaoMaster;
import com.wd.common.dao.DaoSession;
import com.wd.common.dao.UserDao;
import com.wd.health_home_fragment.R;
import com.wd.health_home_fragment.presenter.InformationDetailsPresenter;
import com.wd.health_my.MyActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InformationDetailsActivity extends BaseActivity<InformationDetailsPresenter> implements Constraint.IInformationDetailView, View.OnClickListener {

    private SimpleDraweeView information_details_login;
    private CheckBox information_details_news;
    private TextView information_details_title;
    private TextView information_details_name;
    private TextView information_details_time;
    private WebView information_details_web;

    @Override
    protected void initData() {


        Intent intent = getIntent();
        int infoId = intent.getIntExtra("infoId", 0);
        presenter.informationDetails(0, "", infoId);

    }

    @Override
    protected InformationDetailsPresenter getpresenter() {
        return new InformationDetailsPresenter();
    }

    protected void initView() {
        information_details_login = (SimpleDraweeView) findViewById(R.id.information_details_login);
        information_details_news = (CheckBox) findViewById(R.id.information_details_news);
        information_details_title = (TextView) findViewById(R.id.information_details_title);
        information_details_name = (TextView) findViewById(R.id.information_details_name);
        information_details_time = (TextView) findViewById(R.id.information_details_time);
        information_details_web = (WebView) findViewById(R.id.information_details_web);
    }

    @Override
    protected void initListener() {

        information_details_login.setOnClickListener(this);
        information_details_news.setOnClickListener(this);

        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirst = config.getBoolean("isFirst", false);
        if (isFirst) {

            DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
            UserDao userDao = daoSession.getUserDao();
            List<User> users = userDao.loadAll();

            String headPic = users.get(0).getHeadPic();

            Uri uri = Uri.parse(headPic);
            information_details_login.setImageURI(uri);

        } else {
            Uri uri = Uri.parse("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2060761043,284284863&fm=27&g=0.jpg");
            information_details_login.setImageURI(uri);
        }

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_information_details;
    }

    @Override
    public void IInformationDetailsSuccess(InformationDetailsBean informationDetailsBean) {
        if (informationDetailsBean.status.equals("0000")) {

            InformationDetailsBean.ResultBean result = informationDetailsBean.result;
            information_details_title.setText(result.title);
            information_details_name.setText(result.source);

            Date date = new Date(result.releaseTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd   HH:mm");

            information_details_time.setText(simpleDateFormat.format(date));

            information_details_web.getSettings().setJavaScriptEnabled(true);

            information_details_web.setWebViewClient(new WebViewClient() {
                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();//接受所有网站的证书
                }
            });

            //设置图片显示 宽度 100%  高度自适应
            String varjs = "<script type='text/javascript'> \nwindow.onload = function()\n{var $img = document.getElementsByTagName('img');for(var p in  $img){$img[p].style.width = '100%'; $img[p].style.height ='auto'}}</script>";
            information_details_web.loadData(varjs + result.content, "text/html", "UTF-8");

        }
    }


    @Override
    public void IInformationDetailsError(String s) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.information_details_login){

            startActivity(new Intent(this, MyActivity.class));

        }else if (v.getId() ==  R.id.information_details_news){

            Toast.makeText(this, "消息", Toast.LENGTH_SHORT).show();

        }
    }
}
