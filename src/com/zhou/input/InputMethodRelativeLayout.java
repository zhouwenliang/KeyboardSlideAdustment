package com.zhou.input;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by cc on 14/12/12.
 */
public class InputMethodRelativeLayout extends RelativeLayout {

    public int mTop,mBottom;

    public OnLayoutChangeListner mOnLayoutChangeListner;

    public InputMethodRelativeLayout(Context context) {
        super(context);
    }
    public InputMethodRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InputMethodRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnLayoutChangeListner(OnLayoutChangeListner onLayoutChangeListner){
        mOnLayoutChangeListner = onLayoutChangeListner;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(t != mTop || b != mBottom){
            mTop = t;
            mBottom = b;
            if(mOnLayoutChangeListner != null){
                mOnLayoutChangeListner.onLayoutChange(mTop , mBottom);
            }
        }
        super.onLayout(changed, l, t, r, b);
    }

    public interface OnLayoutChangeListner{
        public void onLayoutChange(int top, int bottom);
    }
}