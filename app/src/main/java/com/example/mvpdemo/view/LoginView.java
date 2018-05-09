package com.example.mvpdemo.view;

public interface LoginView {
    String getAccout();
    String getPwd();
    void showLoading();
    void dismissLoading();
    void showData(String phone);
    void showMsg(String str);
}
