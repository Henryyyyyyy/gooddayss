package com.gooddays.zj.gooddays;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gooddays.zj.gooddays.firstpagedemo.FourFragment;
import com.gooddays.zj.gooddays.firstpagedemo.OneFragment;
import com.gooddays.zj.gooddays.firstpagedemo.ThreeFragment;
import com.gooddays.zj.gooddays.firstpagedemo.TwoFragment;
import com.henry.base_lib.base.AbstractMvpActivity;
import com.henry.base_lib.base.BasePresent;
import com.zj.henry_ui_library.firstpageUI.AaaBottomIndicatorLayout;
import com.zj.henry_ui_library.firstpageUI.AaaHomePager;

import java.util.ArrayList;

public class MainActivity extends AbstractMvpActivity {
    private AaaBottomIndicatorLayout aaa_indicator;
    private AaaHomePager aaa_vp;


    @Override
    protected BasePresent newPresent() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindViews(Bundle savedInstanceState) {
        aaa_indicator = findViewById(R.id.aaa_indicator);
        aaa_vp = findViewById(R.id.aaa_vp);
        initTabsAndViewPager();
//        aaa_indicator.setCurrentTab(3);

    }

    private void initTabsAndViewPager() {
        ArrayList<AaaBottomIndicatorLayout.Tab> tabs = new ArrayList<>();
        //设置selector
        tabs.add(new AaaBottomIndicatorLayout.Tab(R.drawable.selector_draw, R.drawable.selector_text,R.string.it1));
        tabs.add(new AaaBottomIndicatorLayout.Tab(R.drawable.selector_draw, R.drawable.selector_text,R.string.it2));
        tabs.add(new AaaBottomIndicatorLayout.Tab(R.drawable.selector_draw, R.drawable.selector_text,R.string.it3));
        tabs.add(new AaaBottomIndicatorLayout.Tab(R.drawable.selector_draw, R.drawable.selector_text,R.string.it4));
        aaa_indicator.setUpData(tabs, aaa_vp);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());
        aaa_vp.initHomePager(fragments,this,aaa_indicator);
    }


}
