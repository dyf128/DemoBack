package com.dmeos.test.androidart.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.dmeos.test.androidart.R;
import com.dmeos.test.androidart.base.BaseActivity;
import com.dmeos.test.androidart.module.User;
import com.dmeos.test.androidart.widget.TitleBar;


public class LoginActivity extends BaseActivity implements IUserView {

    private static final String TAG = LoginActivity.class.getSimpleName();

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mEmailSignInButton;
    private TitleBar mTitleBar;
    private IUserPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initBaseView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mTitleBar = getTitlebar();
        mTitleBar.setVisibility(View.VISIBLE);
        mLoginPresenter = new UserPresenterImpl(this);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canSubmmit()) {
                    mLoginPresenter.doLogin(mEmailView.getText().toString().trim(), mPasswordView.getText().toString().trim(), TAG);
                }
            }
        });
    }

    /**
     * 表单相关校验
     */
    private boolean canSubmmit() {
        String eml = mEmailView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();
        if (TextUtils.isEmpty(eml)) {
            showToast(getResStringById(R.string.tip_login_eml_empty));
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            showToast(getResStringById(R.string.tip_login_pwd_empty));
            return false;
        }
        return true;
    }

    @Override
    public void showProgress() {
        showBlockLoadingDailogView();
    }

    @Override
    public void hideProgress() {
        dismissBlockLoadingDailogView();
    }

    @Override
    public void onLoadDataFailure(int code, String message) {
        showToast(message);
    }

    @Override
    public void onLoadUserDataSuccess(int code, String message, User user) {
        // TODO 登录成功回调 保存用户信息 跳转页面相关操作
        Intent intent = new Intent(LoginActivity.this, RegActivity.class);
        startActivity(intent);
        mLoginPresenter.saveLoginUserInfo(user);
    }


}

