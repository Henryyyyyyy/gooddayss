package com.henry.base_lib.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.henry.base_lib.R;
import com.henry.base_lib.utils.ScreenUtils;
import com.henry.base_lib.utils.SoftInputUtils;
import com.henry.base_lib.widge.AppToolbar;


/**
 * Created by Henry.zengj on 2018/11/7
 * Activity抽象父类,新页面请使用AbstractMvpActivity，如果没有编写Present的需要，newPresent方法返回null即可。
 */
@Deprecated
public abstract class AbstractActivity extends AppCompatActivity {


    protected AbstractActivity mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dealOnForemost(savedInstanceState);
        baseSetContentView();
        ActivityStack.getInstance().addActivity(this);
        ScreenUtils.init(this);
        mContext = this;
        bindViews(savedInstanceState);

    }

    /**
     * 设置contentView方法
     */
    protected void baseSetContentView() {
        setContentView(setContentView());
    }
    public void dealOnForemost(Bundle savedInstanceState) {}
    public abstract int setContentView();
    protected abstract void bindViews(Bundle savedInstanceState);
    protected abstract void initData();


    boolean isFirstEntry = true;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && isFirstEntry) {
            initData();
            isFirstEntry = false;
        }
    }




    /**
     * 设置返回键的默认动作（关闭页面）
     */
    public void setToolbarDefaultBackAction(AppToolbar toolbar) {
        toolbar.setOnBackAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
    }

    public void onBack() {
        if (this.getCurrentFocus() != null) {
            SoftInputUtils.closeSoftInput(getApplicationContext(), this.getCurrentFocus());
        }
        ActivityCompat.finishAfterTransition(this);
    }

    /**
     * Toast.show消息
     */
    public void toastMessage(String msg) {
        //todo 回去搜搜
     //   AlertToast.make(getWindow().getDecorView(), msg).show();
    }

    /**
     * Toast.show消息
     */
    public void toastMessage(int msgResId) {
        //todo 回去搜搜
      //  AlertToast.make(getWindow().getDecorView(), getString(msgResId)).show();
    }


    @Override
    public final void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_old);
    }
    /**
     * 延迟某个时间执行某个任务
     *
     * @param action        Runnable对象
     * @param delayMillis   延迟的时间
     */
    public boolean postDelayed(Runnable action, long delayMillis) {
        return getWindow().getDecorView().postDelayed(action, delayMillis);
    }

    /**
     * 删除某个延迟任务
     * @param action        Runnable对象
     */
    public boolean removeCallbacks(Runnable action) {
        if(getWindow().getDecorView() != null) {
            return getWindow().getDecorView().removeCallbacks(action);
        }else {
            return true;
        }
    }
    @Override
    public final void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_old, R.anim.slide_left_to_right);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getInstance().removeActivity(this);
    }
}
