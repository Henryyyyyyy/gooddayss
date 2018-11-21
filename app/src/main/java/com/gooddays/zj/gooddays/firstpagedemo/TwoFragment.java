package com.gooddays.zj.gooddays.firstpagedemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gooddays.zj.gooddays.R;
import com.gooddays.zj.module_base.base.AbstractMvpFragment;
import com.gooddays.zj.module_base.base.BasePresent;


/**
 * Created by Henry.zengj on 2018/11/12.
 */

public class TwoFragment extends AbstractMvpFragment {
    @Override
    public int setContentView() {
        return R.layout.fragment_demo_two;
    }

    @Override
    protected void bindViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected BasePresent newPresenter() {
        return null;
    }

    @Override
    public void firstLoadData() {
        super.firstLoadData();
        Log.e("henry","twofragment:firstload");
    }
}
