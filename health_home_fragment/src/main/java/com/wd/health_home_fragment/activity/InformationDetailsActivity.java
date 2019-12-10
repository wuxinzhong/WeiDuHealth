package com.wd.health_home_fragment.activity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.BaseActivity;
import com.wd.common.bean.InformationDetailsBean;
import com.wd.common.constraint.Constraint;
import com.wd.health_home_fragment.R;
import com.wd.health_home_fragment.presenter.InformationDetailsPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InformationDetailsActivity extends BaseActivity<InformationDetailsPresenter> implements Constraint.IInformationDetailView {

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
}
