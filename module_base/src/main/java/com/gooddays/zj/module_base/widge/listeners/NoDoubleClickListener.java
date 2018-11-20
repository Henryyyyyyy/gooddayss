package com.gooddays.zj.module_base.widge.listeners;

import android.view.View;


/**
 * Created by Henry.zengj on 2018/11/7
 * 防止过快多次点击
 */
//todo ConstantValue.CLICK_SPACE?
public abstract class NoDoubleClickListener implements View.OnClickListener {

    private long lastClickTime = 0;

    @Override
    public synchronized void onClick(View v) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 300)
            return;
        lastClickTime = time;
        noDoubleClick(v);
    }

    public abstract void noDoubleClick(View v);
}
