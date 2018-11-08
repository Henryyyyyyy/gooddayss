package com.henry.base_lib.base;

import android.content.Intent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Henry.zengj on 2018/11/7
 */
public abstract class BasePresent<V extends BaseView> {

    private V targetView;
    protected V view;  // view的代理

    public BasePresent(V view) {
        this.targetView = view;
        this.view = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new NotNullHandler());
    }

    public abstract void onStart();


    /**
     * 用来处理onActivityResult,onFragmentResult
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void handViewResult(int requestCode, int resultCode, Intent data) {

    }

    /**
     * 释放view。方法在AbstractMvpActivity的onDestroy方法里面被调用。用弱引用也可以得到同样的效果。
     */
    public void onDestroy() {
        targetView = null;
    }

    public class NotNullHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            if (targetView == null) {  // 防止view为空，用动态代理在每个方法执行前增加判断
                return null;
            }
            try {
                return method.invoke(targetView, args);
            } catch (Exception e) {
                // 发布的时候不让错误影响程序稳定性

                return null;
            }
        }
    }
}
