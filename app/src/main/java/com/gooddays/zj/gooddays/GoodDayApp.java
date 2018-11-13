package com.gooddays.zj.gooddays;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Henry.zengj on 2018/11/13.
 */

public class GoodDayApp extends Application{
    public static int H,W;
    public static GoodDayApp app;
    @Override
    public void onCreate() {
        super.onCreate();
        getScreen(this);
    }
    public void getScreen(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        H=dm.heightPixels;
        W=dm.widthPixels;
    }
}
