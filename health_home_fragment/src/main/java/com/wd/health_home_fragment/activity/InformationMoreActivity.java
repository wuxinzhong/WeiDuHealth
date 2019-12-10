package com.wd.health_home_fragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.common.base.BaseActivity;
import com.wd.common.bean.InformationBean;
import com.wd.common.constraint.Constraint;
import com.wd.health_home_fragment.R;
import com.wd.health_home_fragment.adapter.InformationAdapter;
import com.wd.health_home_fragment.presenter.InformationMorePresenter;

public class InformationMoreActivity extends BaseActivity<InformationMorePresenter> implements Constraint.IInformationView, XRecyclerView.LoadingListener {

    private SimpleDraweeView information_more_login;
    private TextView information_more_title;
    private CheckBox information_more_news;
    private XRecyclerView information_more_xRecycle;
    private InformationAdapter mInformationAdapter;
    private String mName;
    private int mId;

    @Override
    protected void initData() {


        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        information_more_xRecycle.setLayoutManager(linearLayoutManager1);


        mInformationAdapter = new InformationAdapter();
        information_more_xRecycle.setAdapter(mInformationAdapter);
        mInformationAdapter.setOnItemClickListener(new InformationAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(InformationMoreActivity.this, InformationDetailsActivity.class);
                intent.putExtra("infoId", position);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);
        mName = intent.getStringExtra("name");

        information_more_xRecycle.setLoadingListener(this);
        onRefresh();
//        presenter.information(mId, true);
    }

    @Override
    protected InformationMorePresenter getpresenter() {
        return new InformationMorePresenter();
    }

    protected void initView() {
        information_more_login = (SimpleDraweeView) findViewById(R.id.information_more_login);
        information_more_title = (TextView) findViewById(R.id.information_more_title);
        information_more_news = (CheckBox) findViewById(R.id.information_more_news);
        information_more_xRecycle = (XRecyclerView) findViewById(R.id.information_more_xRecycle);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_information_more;
    }

    @Override
    public void IInformationSuccess(InformationBean informationBean) {
        if (informationBean.status.equals("0000")) {

            if (presenter.getPage() == 1)
                mInformationAdapter.clear();

            information_more_title.setText(mName);
            mInformationAdapter.addAll(informationBean.result);
            mInformationAdapter.notifyDataSetChanged();

            information_more_xRecycle.loadMoreComplete();
            information_more_xRecycle.refreshComplete();

        }
    }

    @Override
    public void IInformationError(String s) {

    }

    @Override
    public void onRefresh() {
        presenter.information(mId, true);
    }

    @Override
    public void onLoadMore() {
        presenter.information(mId, false);
    }
}
