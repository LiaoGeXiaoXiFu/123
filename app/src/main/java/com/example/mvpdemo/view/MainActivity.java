package com.example.mvpdemo.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.presenter.LoginPresenterImp;

/**
 * 以MVP的角度来看，Actiivty对应的就是V(view)
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,LoginView {
    Handler handler = new Handler();
    private LoginPresenterImp loginPresenterImp;
    /**
     * 请输入账号
     */
    private EditText mEtAccount;
    /**
     * 请输入密码
     */
    private EditText mEtPwd;
    /**
     * 登录
     */
    private Button mBtLogin;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loginPresenterImp = new LoginPresenterImp(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenterImp.detach();
    }

    private void initView() {
        mEtAccount = (EditText) findViewById(R.id.etAccount);
        mEtPwd = (EditText) findViewById(R.id.etPwd);
        mBtLogin = (Button) findViewById(R.id.btLogin);
        mBtLogin.setOnClickListener(this);
        mTv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btLogin:
                //登录
                loginPresenterImp.login();
                break;
        }
    }

    @Override
    public String getAccout() {
        return mEtAccount.getText().toString();
    }

    @Override
    public String getPwd() {
        return mEtPwd.getText().toString();
    }

    @Override
    public void showLoading() {
        //进度显示
        Toast.makeText(MainActivity.this,"加载中...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        //关闭进度显示
        Toast.makeText(MainActivity.this,"加载完成",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(final String phone) {
        //显示号码
       /* runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTv.setText(phone);
            }
        });*/
        mTv.setText(phone);
    }

    /**
     * 给用户提示一些信息，比如正确的，或者错误的
     * @param str
     */
    @Override
    public void showMsg(String str) {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
    }
}
