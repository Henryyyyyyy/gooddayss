package com.gooddays.zj.gooddays.firstpagedemo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.gooddays.zj.gooddays.GoodDayApp;
import com.gooddays.zj.gooddays.R;
import com.gooddays.zj.gooddays.bannerdemo.GlideImageLoader;
import com.henry.base_lib.base.AbstractMvpFragment;
import com.henry.base_lib.base.BasePresent;
import com.henry.base_lib.utils.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

/**
 * Created by Henry.zengj on 2018/11/12.
 */

public class OneFragment extends AbstractMvpFragment implements OnBannerListener {
    Banner banner;
    @Override
    public int setContentView() {
        return R.layout.fragment_demo_one;
    }

    @Override
    protected void bindViews(View view, Bundle savedInstanceState) {
        banner =  mContext.findViewById(R.id.banner);


    }

    @Override
    protected void initData() {
        super.initData();
        ArrayList<String>urls=new ArrayList<>();
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        banner.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, GoodDayApp.H / 4));
        //简单使用
        banner.setImages(urls)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    @Override
    protected BasePresent newPresenter() {
        return null;
    }

    @Override
    public void OnBannerClick(int position) {
        ToastUtils.show(mContext,"position="+position);
    }
}
