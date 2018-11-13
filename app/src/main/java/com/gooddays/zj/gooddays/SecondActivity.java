package com.gooddays.zj.gooddays;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.henry.base_lib.base.AbstractMvpActivity;
import com.henry.base_lib.base.AbstractMvpFragment;
import com.henry.base_lib.base.BasePresent;

public class SecondActivity extends AbstractMvpFragment {


    @Override
    public int setContentView() {
        return 0;
    }

    @Override
    protected void bindViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected BasePresent newPresenter() {
        return null;
    }
}
