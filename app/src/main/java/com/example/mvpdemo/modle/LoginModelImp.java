package com.example.mvpdemo.modle;

import com.example.mvpdemo.net.Api;
import com.example.mvpdemo.net.OkhttpUtils;
import com.example.mvpdemo.net.OnNetListener;

import java.util.HashMap;
import java.util.Map;

public class LoginModelImp implements LoginModel {
    @Override
    public void login(String accoutm, String pwd, OnNetListener onNetListener) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", accoutm);
        params.put("password", pwd);
        OkhttpUtils.getInstance().login(Api.LGOIN_URL, params, onNetListener);
    }
}
