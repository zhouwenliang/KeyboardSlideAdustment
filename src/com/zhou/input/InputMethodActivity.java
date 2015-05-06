package com.zhou.input;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.*;

public class InputMethodActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final InputMethodRelativeLayout inputMethodRelativeLayout = (InputMethodRelativeLayout)findViewById(R.id.rl_main_container);
        LayoutInflater inflater = LayoutInflater.from(this);
        final RelativeLayout inputLayout = (RelativeLayout)inflater.inflate(R.layout.input,null);
        final EditText editText = (EditText)inputLayout.findViewById(R.id.edit_text);
        final RelativeLayout editTextLayout = (RelativeLayout)inputLayout.findViewById(R.id.rl_edit_text);
        final FrameLayout decorView = (FrameLayout)getWindow().getDecorView();
        decorView.addView(inputLayout);
        inputMethodRelativeLayout.setOnLayoutChangeListner(new InputMethodRelativeLayout.OnLayoutChangeListner() {
            @Override
            public void onLayoutChange(final int top, final int bottom) {
                inputLayout.post(new Runnable(){
                    @Override
                    public void run() {
                        int[] location = new int[2];
                        inputMethodRelativeLayout.getLocationInWindow(location);
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)editText.getLayoutParams();
                        layoutParams.topMargin = bottom - editText.getMeasuredHeight() + location[1];
                        editTextLayout.updateViewLayout(editText, layoutParams);
                    }
                });
            }
        });
    }
}
