package com.gooddays.zj.module_base.base;


import android.content.Intent;

/**
 * Created by Henry.zengj on 2018/11/7
 */
public interface BaseView {
    AbstractActivity getActivityContext();

    void showToastMessage(int msgResId);
    void showToastMessage(String msg);

    void startLoading();
    void stopLoading(int state, String msg) ;
    Boolean isLoading() ;

    Boolean checkLoginState();
    Boolean checkLoginStateAndLogin(int requestCode);

    void setResultAndFinish(int Result, Intent data);
    void finish();
}
