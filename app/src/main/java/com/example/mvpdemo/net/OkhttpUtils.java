package com.example.mvpdemo.net;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求的工具类
 */
public class OkhttpUtils {
    private static OkhttpUtils instance;
    private final OkHttpClient okHttpClient;
    private final Handler handler;

    private OkhttpUtils() {
        //创建OkHttpClient对象
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }

    public static OkhttpUtils getInstance() {
        if (instance == null) {
            instance = new OkhttpUtils();
        }
        return instance;
    }

    /**
     * 登录的方法
     *
     * @param url           地址
     * @param params        接口的参数
     * @param onNetListener 接口回调，把数据返给调用改方法的Activity
     */
    public void login(String url, Map<String, String> params, final OnNetListener onNetListener) {
        if (params == null) {
            return;
        }
        //创建Requeset对象
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onNetListener.onFailed(e);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //把回调放到主线程里
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //已经在主线程运行了
                        final String string;
                        try {
                            //拿到服务器返回的json字符串
                            string = response.body().string();
                            onNetListener.onSuccess(string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

//                onNetListener.onSuccess(string);
            }
        });
    }

}
