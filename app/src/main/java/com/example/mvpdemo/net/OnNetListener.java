package com.example.mvpdemo.net;

/**
 * 网络请求成功或者失败回调的接口
 */
public interface OnNetListener {
    void onSuccess(String result);

    void onFailed(Exception e);
}
