package com.dmeos.test.androidart.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeos.test.androidart.R;

/**
 * Created by ufenqi on 16/4/12.
 */
public class TitleBar extends RelativeLayout implements View.OnClickListener {
    private Activity mCurrentActvity;
    private TextView mBack;

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setmCurrentActvity(Activity actvity) {
        this.mCurrentActvity = actvity;
    }


    private void init(Context context) {
        inflate(context, R.layout.layout_titlebar, this);
        mBack = (TextView) findViewById(R.id.titlebar_back);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_back:
                if (mCurrentActvity != null) {
                    mCurrentActvity.finish();
                }
                break;
            default:
                break;
        }
    }


}
