package com.henry.base_lib.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.henry.base_lib.R;
import com.henry.base_lib.widge.LoadingView;

import java.lang.ref.WeakReference;

/**
 * Created by Henry.zengj on 2018/11/7
 */
public abstract class AbstractMvpFragment<T extends BasePresent> extends AbstractFragment implements BaseView {
    public T presenter;
    private BaseView baseViewImpl;
    private LoadingView loadingView;
    protected Handler mHandler = new LeakHandler(this);

    private boolean initSuccess = false;
    private boolean resumeFlag = false;

    @CallSuper
    @Override
    protected void initData() {
        presenter = newPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        resumeFlag = true;

        if (presenter != null && getUserVisibleHint() && !initSuccess) {
            presenter.onStart();
            initSuccess = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!initSuccess && isVisibleToUser && presenter != null && resumeFlag) {
            presenter.onStart();
            initSuccess = true;
        }
    }

    /**
     * 当Activity和Fragment关联时，会回调此方法，将Activity的实例传递到此类中。
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseView) {
            baseViewImpl = (BaseView) context;
        } else {
            throw new RuntimeException("宿主Activity必须使用AbstractMvpActivity");
        }
    }

    protected abstract T newPresenter();

    /**
     * 防止内存泄露的handler
     */
    static class LeakHandler extends Handler {
        WeakReference<AbstractMvpFragment> reference;

        LeakHandler(AbstractMvpFragment fragment) {
            reference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            if (reference == null) return;
            AbstractMvpFragment fragment = reference.get();
            if (fragment != null)
                fragment.handleMessage(msg);
        }
    }

    protected void handleMessage(Message message) {
    }

    @Override
    public void onDetach() {
        super.onDetach();
        baseViewImpl = null;
        if (presenter != null) {
            presenter.onDestroy();
        }
    }


    @Override
    public void setResultAndFinish(int Result, Intent data) {
        if (baseViewImpl != null) {
            baseViewImpl.setResultAndFinish(Result, data);
        }
    }


    @Override
    public void showToastMessage(String msg) {
        if (baseViewImpl != null) {
            baseViewImpl.showToastMessage(msg);
        }
    }

    @Override
    public void showToastMessage(int msgResId) {
        if (baseViewImpl != null) {
            baseViewImpl.showToastMessage(msgResId);
        }
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading(final int state, final String msg) {

    }

    @Override
    public Boolean isLoading() {
        return true;
      //  return ((loadingView != null) && loadingView.isLoading());
    }



    @Override
    public AbstractActivity getActivityContext() {
        return mContext;
    }

    @Override
    public void finish() {
        getActivity().finish();
    }



    @Override
    public Boolean checkLoginState() {
        return true;
       // return LoginActionImpl.getInstance(getApplicationContext()).isLogined();
    }

    @Override
    public Boolean checkLoginStateAndLogin(int requestCode) {
        return true;
//        boolean logined = LoginActionImpl.getInstance(getApplicationContext()).isLogined();
//        if (!logined) {
//            Intent intent = Router.prepare(getApplicationContext(), Router.getHost() + "/Login").getForwardIntent();
//            if (intent != null) {
//                startActivityForResult(intent, requestCode);
//            }
//        }
//        return logined;
    }
}
