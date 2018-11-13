package com.zj.henry_ui_library.firstpageUI;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zj.henry_ui_library.R;

import java.util.ArrayList;

/**
 * Created by Henry.zengj on 2018/11/12.
 */

public class AaaBottomIndicatorLayout extends LinearLayout implements View.OnClickListener,IHomePagerListener {
    private ArrayList<Tab> tabs;
    private IHomeBottomLayoutListener listener;
    private TabView selectTabView;
    private ArrayList<TabView> mTabViews = new ArrayList<>();
    private int tabCount;
    private Context mContext;

    public AaaBottomIndicatorLayout(Context context) {
        this(context, null);
    }

    public AaaBottomIndicatorLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AaaBottomIndicatorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);

    }

    public void setUpData(ArrayList<Tab> tabs, IHomeBottomLayoutListener listener) {
        this.tabs = tabs;
        this.listener = listener;
        mTabViews.clear();
        if (tabs != null && tabs.size() > 0) {
            tabCount = tabs.size();
            TabView tabView;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            params.weight = 1;
            for (int i = 0; i < tabs.size(); i++) {
                tabView = new TabView(getContext());
                tabView.setTag(i);
                tabView.setUpData(tabs.get(i));
                tabView.setOnClickListener(this);
                mTabViews.add(tabView);
                addView(tabView, params);
            }
        } else {
            throw new IllegalArgumentException("tabs can't be empty");
        }
    }


    public void setCurrentTab(int i) {
        if (i < tabCount && i >= 0) {
            View view = getChildAt(i);
            onClick(view);
        }
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        TabView currentTabView = mTabViews.get(position);
        if (selectTabView != currentTabView) {
            selectTabView = currentTabView;
            listener.onTabClick(tabs.get(position), position);
            selectTabView.setSelected(true);
            for (int i = 0; i < mTabViews.size(); i++) {
                if (i != position) {
                    mTabViews.get(i).setSelected(false);
                }
            }
        }
    }

    @Override
    public void onHomePageSelect(int position) {
        onClick(mTabViews.get(position));
    }

    public class TabView extends LinearLayout {
        private ImageView mTabImg;
        private TextView mTabLabel;

        public TabView(Context context) {
            this(context, null);
        }

        public TabView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            setUpView();
        }

        private void setUpView() {
            LayoutInflater.from(getContext()).inflate(R.layout.widget_tab_view, this, true);
            setOrientation(VERTICAL);
            setGravity(Gravity.CENTER);
            mTabImg = findViewById(R.id.mTabImg);
            mTabLabel = findViewById(R.id.mTabLabel);
        }

        public void setUpData(Tab tab) {
            mTabImg.setBackgroundResource(tab.imgResId);
            mTabLabel.setText(tab.labelResId);
            mTabLabel.setTextColor(mContext.getResources().getColorStateList(tab.labelTextColor)
            );
        }

        public ImageView getTabImg() {
            return mTabImg;
        }

        public void setTabImg(ImageView mTabImg) {
            this.mTabImg = mTabImg;
        }

        public TextView getTabLabel() {
            return mTabLabel;
        }

        public void setTabLabel(TextView mTabLabel) {
            this.mTabLabel = mTabLabel;
        }
    }

    public static class Tab {
        public int imgResId;
        public int labelResId;
        public int labelTextColor;



        public Tab(int imgResId, int labelTextColor,int labelResId) {
            this.imgResId = imgResId;
            this.labelTextColor = labelTextColor;
            this.labelResId = labelResId;
        }



    }

}
