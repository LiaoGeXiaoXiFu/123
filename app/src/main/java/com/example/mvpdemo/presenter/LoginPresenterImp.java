package com.example.mvpdemo.presenter;

import android.text.TextUtils;

import com.example.mvpdemo.bean.UserBean;
import com.example.mvpdemo.modle.LoginModelImp;
import com.example.mvpdemo.net.OnNetListener;
import com.example.mvpdemo.view.LoginView;
import com.google.gson.Gson;

/**
 * 以MVP的角度来看，该类对应的就是P(presenter)
 * 作用：1、进行业务逻辑处理前的各种条件判断
 * 2、连接View层和Model层
 */
public class LoginPresenterImp implements LoginPresenter {
    private LoginView loginView;
    private final LoginModelImp loginModelImp;

    public LoginPresenterImp(LoginView loginView) {
        //绑定
        this.loginView = loginView;
        //初始化model层的对象
        loginModelImp = new LoginModelImp();
    }

    //登录
    @Override
    public void login() {
        //该方法不做真正的业务逻辑即不写请求网络的方法，交给Model层去处理

        //获取用户名和密码
        String accout = loginView.getAccout();
        String pwd = loginView.getPwd();
        if (TextUtils.isEmpty(accout) || TextUtils.isEmpty(pwd)) {
            //提示用户输入账号密码不正确
            loginView.showMsg("输入账号密码不正确");
            return;
        }
        //进行网络请求
        loginView.showLoading();
        loginModelImp.login(accout, pwd, new OnNetListener() {
            @Override
            public void onSuccess(String result) {
                if (loginView == null) {
                    return;
                }
                loginView.dismissLoading();
                UserBean userBean = new Gson().fromJson(result, UserBean.class);
                loginView.showData(userBean.getData().getMobile());
            }

            @Override
            public void onFailed(Exception e) {
                if (loginView == null) {
                    return;
                }
                loginView.dismissLoading();
            }
        });
    }

    //解绑
    @Override
    public void detach() {
        if (loginView != null) {
            loginView = null;
        }
    }


}
