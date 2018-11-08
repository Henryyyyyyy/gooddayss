package com.henry.base_lib.widge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.henry.base_lib.R;


/**
 * Created by Henry.zengj on 2018/11/7
 */
public class ObservableScrollView extends ScrollView {
    private OnScrollChangedListener scrollViewListener = null;

    public interface OnScrollChangedListener {

        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);

    }

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public void setObservaleToolbar(final AppToolbar toolbar) {
        setOnScrollChangedListener(new OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                float point = getResources().getDimension(R.dimen.h_40) * 3;
                if (y > point) {
                    toolbar.setBackGroundAlpha(0xFF22AC39);
                } else {
                    float alpha = 0xFF / point * (y < 0 ? 0 : y / 2);
                    toolbar.setBackGroundAlpha(Color.argb((int) alpha, 0x22, 0xAC, 0x39));
                }
            }
        });
    }

}
