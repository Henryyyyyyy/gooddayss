package com.gooddays.zj.module_ui.firstpageUI;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * Created by Henry.zengj on 2018/11/12.
 */

public class AaaHomePager extends ViewPager implements IHomeBottomLayoutListener {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private PagerAdapter adapter;
    private IHomePagerListener listner;

    public AaaHomePager(@NonNull Context context) {
        this(context, null);
    }

    public AaaHomePager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void initHomePager(ArrayList<Fragment> fragments, AppCompatActivity activity, IHomePagerListener indicator) {
        mFragments = fragments;
        adapter = new PagerAdapter(activity.getSupportFragmentManager());
        listner = indicator;
        setAdapter(adapter);
        setPageListener();

    }

    private void setPageListener() {
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                listner.onHomePageSelect(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 外部接口
     *
     * @param tab
     * @param position
     */
    @Override
    public void onTabClick(AaaBottomIndicatorLayout.Tab tab, int position) {
        setCurrentItem(position, false);
    }




    class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
