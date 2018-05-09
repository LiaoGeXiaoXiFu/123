package com.example.mvpdemo.modle;

import com.example.mvpdemo.net.OnNetListener;

public interface LoginModel {
    void login(String accout,String pwd, OnNetListener onNetListener);
}
