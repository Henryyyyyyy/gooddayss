package com.gooddays.zj.module_base.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Henry.zengj on 2018/11/7
 * Fragment抽象类
 */
public abstract class AbstractFragment extends Fragment {

    protected AbstractActivity mContext;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = (AbstractActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }


    public View mView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if (mView==null){
           mView=inflater.inflate(setContentView(), container, false);
           dealOnForemost(mView, savedInstanceState);
           bindViews(mView, savedInstanceState);
           initData();
       }
        return mView;
    }


    protected void toastMessage(int msgRes) {
        if (mContext == null) {
            return;
        }
        mContext.toastMessage(msgRes);
    }

    protected void toastMessage(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (mContext == null) {
            return;
        }
        mContext.toastMessage(msg);
    }

    public abstract int setContentView();


    protected abstract void bindViews(View view, Bundle savedInstanceState);

    protected abstract void initData();

    public void dealOnForemost(View view, Bundle savedInstanceState) {

    }
}
