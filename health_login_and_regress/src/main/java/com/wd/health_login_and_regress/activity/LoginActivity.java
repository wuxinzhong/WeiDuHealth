package com.wd.health_login_and_regress.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.common.base.BaseActivity;
import com.wd.common.bean.LoginBean;
import com.wd.common.bean.User;
import com.wd.common.constraint.Constraint;
import com.wd.common.dao.DaoMaster;
import com.wd.common.dao.DaoSession;
import com.wd.common.dao.UserDao;
import com.wd.health_login_and_regress.R;
import com.wd.health_login_and_regress.RsaCoder;
import com.wd.health_login_and_regress.presenter.LoginPresenter;
import com.wd.health_login_and_regress.toast.CustomToast;
import com.wd.health_login_and_regress.util.RecordClickSpan;

//import cn.jpush.im.android.api.JMessageClient;
//import cn.jpush.im.api.BasicCallback;

public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, Constraint.ILoginView {

    private EditText my_login_email;
    private CheckBox my_login_unlock;
    private EditText my_login_pwd;
    private CheckBox my_login_hide_eye;
    private Button my_login_btn;
    private TextView my_login_reset_pwd;
    private TextView my_login_regress;
    private ImageView my_login_wei;
    private String mEmail;
    private String mPwd;
    private CustomToast toast;


    private static final String TAG = "LoginActivity";
    private String mS;

    @Override
    protected void initData() {


        my_login_hide_eye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    my_login_unlock.setChecked(isChecked);
                    my_login_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    my_login_unlock.setChecked(isChecked);
                    my_login_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });

    }

    @Override
    protected LoginPresenter getpresenter() {
        return new LoginPresenter();
    }

    protected void initView() {
        my_login_email = (EditText) findViewById(R.id.my_login_email);
        my_login_unlock = (CheckBox) findViewById(R.id.my_login_unlock);
        my_login_pwd = (EditText) findViewById(R.id.my_login_pwd);
        my_login_hide_eye = (CheckBox) findViewById(R.id.my_login_hide_eye);
        my_login_btn = (Button) findViewById(R.id.my_login_btn);
        my_login_reset_pwd = (TextView) findViewById(R.id.my_login_reset_pwd);
        my_login_regress = (TextView) findViewById(R.id.my_login_regress);
        my_login_wei = (ImageView) findViewById(R.id.my_login_wei);

        my_login_btn.setOnClickListener(this);
        my_login_reset_pwd.setOnClickListener(this);
        my_login_wei.setOnClickListener(this);
    }

    @Override
    protected void initListener() {


        final SpannableStringBuilder style = new SpannableStringBuilder();

        //设置文字
        style.append("暂无账号？立即注册");

        //设置部分文字点击事件

        RecordClickSpan courseSpan = new RecordClickSpan() {
            @Override
            public void onClick(View widget) {
                super.onClick(widget);

                //注册   点击事件
                startActivity(new Intent(LoginActivity.this, RegressActivity.class));
            }
        };

        style.setSpan(courseSpan, 5, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        my_login_regress.setText(style);

        //设置部分文字颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#ffffff"));
        style.setSpan(foregroundColorSpan, 5, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置部分文字大小
        style.setSpan(new AbsoluteSizeSpan(36), 5, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //配置给TextView
        my_login_regress.setMovementMethod(LinkMovementMethod.getInstance());
        my_login_regress.setText(style);
        my_login_regress.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明


    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    //邮箱验证
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.my_login_btn) {
            if (submit()) {

                try {
                    mS = RsaCoder.encryptByPublicKey(mPwd);

                    Log.i(TAG, "onClick: " + mPwd);

                    isEmail(mEmail);
                    presenter.login(mEmail, mS);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (v.getId() == R.id.my_login_reset_pwd) {

            //忘记密码
            startActivity(new Intent(LoginActivity.this, ResetPwdActivity.class));

        } else if (v.getId() == R.id.my_login_wei) {

            //微信登录


        }
    }


    public void toastMessage(String content) {
        if (toast != null) {
            toast.hide();
        }
        toast = new CustomToast(LoginActivity.this,
                (ViewGroup) this.findViewById(R.id.toast_custom_parent));
        toast.show(content, 500);
    }


    private boolean submit() {
        // validate
        mEmail = my_login_email.getText().toString().trim();
        if (TextUtils.isEmpty(mEmail)) {
            toastMessage("请输入邮箱");
//            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return false;
        }

        mPwd = my_login_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(mPwd)) {
            toastMessage("请输入密码");
//            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    @Override
    public void loginSuccess(LoginBean loginBean) {

        if (loginBean.status.equals("0000")) {

            LoginBean.ResultBean result = loginBean.result;

            toastMessage(loginBean.message);
//            Toast.makeText(this, loginBean.message, Toast.LENGTH_SHORT).show();

//            JMessageClient.login(mEmail, mS, new BasicCallback() {
//                @Override
//                public void gotResult(int i, String s) {
//                    if (i == 0){
//                        presenter.login(mEmail, mS);
//                    }
//                }
//            });

            SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
            SharedPreferences.Editor edit = config.edit();
            edit.putBoolean("isFirst", true);
            edit.commit();

            DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
            UserDao userDao = daoSession.getUserDao();

            User user = new User();
            user.setAge(result.age);
            user.setEmail(result.email);
            user.setHeadPic(result.headPic);
            user.setHeight(result.height);
            user.setNickName(result.nickName);
            user.setSessionId(result.sessionId);
            user.setSex(result.sex);
            user.setUserId(result.id);
            user.setUserName(result.userName);
            user.setWeight(result.weight);
            user.setWhetherBingWeChat(result.whetherBingWeChat);

            userDao.insertOrReplace(user);


            finish();

        } else {
            toastMessage(loginBean.message);
//            Toast.makeText(this, loginBean.message, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void loginError(String s) {

    }
}
