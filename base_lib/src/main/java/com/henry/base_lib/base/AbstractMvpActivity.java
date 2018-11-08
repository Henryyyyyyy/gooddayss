package com.henry.base_lib.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.henry.base_lib.widge.LoadingView;
import com.henry.base_lib.widge.statusbar.StatusBarUtil;

import java.lang.ref.WeakReference;

/**
 * Created by Henry.zengj on 2018/11/7
 */
public abstract class AbstractMvpActivity<T extends BasePresent> extends AbstractActivity implements BaseView {


    protected T presenter;
    protected Handler mHandler = new LeakHandler(this);
    private LoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = newPresent();
        if (setWhiteStatusBar() && Build.VERSION.SDK_INT >= 23) {
            StatusBarUtil.setWhiteStatusBar(this);
        }
    }

    /**
     * 控制是否需要设置白色标题栏
     *
     * @return 是否需要设置白色标题栏
     */
    protected boolean setWhiteStatusBar() {
        return true;
    }

    @Override
    protected void initData() {
        if (presenter != null) {
            presenter.onStart();
        }
    }

    /**
     * 如果页面简单没什么业务逻辑不需要，可以返回null
     */
    protected abstract T newPresent();


    @Override
    public AbstractActivity getActivityContext() {
        return mContext;
    }
    @Override
    public void showToastMessage(String msg) {

    }
    @Override
    public void showToastMessage(int msgResId) {

    }

    @Override
    public void setResultAndFinish(int Result, Intent data) {
        setResult(Result, data);
        finish();
    }


    @Override
    public void startLoading() {
        //todo 设置loading 状态
       // startLoading(R.id.loading_view, false);
    }

    @Override
    public void stopLoading(final int state, final String msg) {
    }

    @Override
    public Boolean isLoading() {
        //todo 返回loadingview的loading状态
        return true;
    }

    @Override
    public Boolean checkLoginState() {
//        return LoginActionImpl.getInstance(getApplicationContext()).isLogined();
        return true;
    }

    @Override
    public Boolean checkLoginStateAndLogin(int requestCode) {

        return true;
//        boolean logined = LoginActionImpl.getInstance(getApplicationContext()).isLogined();
//        if (!logined) {
//            Intent intent = Router.prepare(this, Router.getHost() + "/Login").getForwardIntent();
//            if (intent != null) {
//                startActivityForResult(intent, requestCode);
//            }
//        }
//        return logined;
    }
    /**
     * 防止内存泄露的handler
     */
    static class LeakHandler extends Handler {
        WeakReference<AbstractMvpActivity> reference;

        LeakHandler(AbstractMvpActivity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (reference == null) return;
            AbstractMvpActivity activity = reference.get();
            if (activity != null)
                activity.handleMessage(msg);
        }
    }

    protected void handleMessage(Message message) {}
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}
