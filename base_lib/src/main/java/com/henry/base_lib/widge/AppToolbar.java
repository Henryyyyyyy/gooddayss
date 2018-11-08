package com.henry.base_lib.widge;

import android.content.Context;
import android.content.res.TypedArray;

import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.henry.base_lib.R;

/**
 * Created by Henry.zengj on 2018/11/7
 */
public class AppToolbar extends LinearLayout {

    private String mTitleString = null;    // 标题


    private boolean isTitleShow = true;     // 标题显示,自定义标题互斥最多二选一
    private boolean isCustomTitleViewShow = false; // 自定义标题


    private OnClickListener mBackClickListener;     //  返回键被点击

    private AdapterView.OnItemClickListener mDropdownItemListener;  // 更多按钮
    private Context context;
    private boolean isLastTitleStyleCustom = false;



    public AppToolbar(Context context) {
        super(context);
        init(context, null);
    }

    public AppToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AppToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;

        View rootView = LayoutInflater.from(context).inflate(R.layout.actionbar, null);
        addView(rootView);
//        ButterKnife.bind(this, rootView);
        initView(rootView);
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AppToolbar, 0, 0);
        CharSequence mTitleSequence = a.getText(R.styleable.AppToolbar_tb_title);
        if (!TextUtils.isEmpty(mTitleSequence)) {
            mTitleString = mTitleSequence.toString();
        }
        a.recycle();
        setTitle(mTitleString);
    }

    RelativeLayout mToolbarLayout;
    TextView mTitleView;
    LinearLayout mCustomViewLayout;
    ImageView mElevation;
    private void initView(View rootView) {
        mTitleView = (TextView) rootView.findViewById(R.id.tv_toolbar_title);
        mElevation = (ImageView) rootView.findViewById(R.id.elevation);
    }

    /**
     * 设置标题
     * <p>create by luokj on 2015/11/16</p>
     *
     * @param mTitleString 标题
     */
    public void setTitle(String mTitleString) {
        if (mTitleString == null) mTitleString = "";
        this.mTitleString = mTitleString;
        mTitleView.setText(mTitleString);
        if (!isTitleShow)
            mTitleView.setVisibility(VISIBLE);
        isTitleShow = true;
        // 隐藏另一个
        if (isCustomTitleViewShow) {
            mCustomViewLayout.setVisibility(GONE);
            isCustomTitleViewShow = false;
        }
    }







    /**
     * 设置回退动作
     */
    public void setOnBackAction(OnClickListener listener) {
        this.mBackClickListener = listener;
    }


    public void setBackGroundTransparent() {
        mToolbarLayout.setBackgroundColor(0x0033C774);
        mElevation.setVisibility(GONE);
    }

    public void setBackGroundAlpha(int color) {
        mToolbarLayout.setBackgroundColor(color);
        if (color == 0xFF33C774) {
            mElevation.setVisibility(VISIBLE);
        } else {
            mElevation.setVisibility(GONE);
        }
    }



}
