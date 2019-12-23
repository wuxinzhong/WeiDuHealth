package com.wd.health_home_fragment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import com.wd.common.base.BaseActivity;
import com.wd.common.bean.InformationBean;
import com.wd.common.bean.User;
import com.wd.common.constraint.Constraint;
import com.wd.common.dao.DaoMaster;
import com.wd.common.dao.DaoSession;
import com.wd.common.dao.UserDao;
import com.wd.health_home_fragment.R;
import com.wd.health_home_fragment.adapter.InformationAdapter;
import com.wd.health_home_fragment.presenter.InformationMorePresenter;
import com.wd.health_my.MyActivity;

import java.util.List;

public class InformationMoreActivity extends BaseActivity<InformationMorePresenter> implements Constraint.IInformationView, XRecyclerView.LoadingListener, View.OnClickListener {

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
        information_more_login.setOnClickListener(this);
        information_more_news.setOnClickListener(this);

        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        boolean isFirst = config.getBoolean("isFirst", false);
        if (isFirst) {

            DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
            UserDao userDao = daoSession.getUserDao();
            List<User> users = userDao.loadAll();

            String headPic = users.get(0).getHeadPic();

            Uri uri = Uri.parse(headPic);
            information_more_login.setImageURI(uri);

        } else {
            Uri uri = Uri.parse("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2060761043,284284863&fm=27&g=0.jpg");
            information_more_login.setImageURI(uri);
        }

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.information_more_login){

            startActivity(new Intent(this, MyActivity.class));

        }else if (v.getId() == R.id.information_more_news){

            Toast.makeText(this, "消息", Toast.LENGTH_SHORT).show();

        }
    }
}
