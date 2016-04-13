package com.dmeos.test.androidart.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeos.test.androidart.R;


public class BottomTab extends RelativeLayout implements BottomTabImpl {

    public BottomTab(Context context) {
        this(context, null);
    }

    public BottomTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    CharSequence tabText;

    ColorStateList tabTextColors;

    Drawable tabDrawable;

    public BottomTab(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs == null) {
            // Preserve behavior prior to removal of this API.
            throw new NullPointerException();
        }
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BottomTab);
        tabTextColors = a.getColorStateList(R.styleable.BottomTab_android_textColor);
        tabText = a.getText(R.styleable.BottomTab_android_text);
        tabDrawable = a.getDrawable(R.styleable.BottomTab_android_drawableTop);
        a.recycle();
        initViews();
    }

    private TextView msgTv;

    private RadioButton tabBtn;

    private OnCheckedChangeListener mOnCheckedChangeWidgetListener;

    private OnCheckedChangeListener mOnCheckedChangeListener;

    private void initViews() {
        inflate(getContext(), getLayoutRes(), this);
        msgTv = (TextView) findViewById(R.id.tab_hint);
        tabBtn = (RadioButton) findViewById(R.id.tab_btn);

        tabBtn.setCompoundDrawablesWithIntrinsicBounds(null, tabDrawable, null, null);
        tabBtn.setText(tabText);
        if (tabTextColors != null) {
            tabBtn.setTextColor(tabTextColors);
        }

        tabBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mChecked = isChecked;
                msgTv.refreshDrawableState();
                mOnCheckedChangeWidgetListener.onCheckedChanged(BottomTab.this, isChecked);
                if (mOnCheckedChangeListener != null) {
                    mOnCheckedChangeListener.onCheckedChanged(BottomTab.this, isChecked);
                }
            }
        });
    }

    
    public int getLayoutRes() {
        return R.layout.bottom_tab_main;
    }

    public TextView getTabButton() {
        return tabBtn;
    }

    public TextView getHint() {
        return msgTv;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    public BottomTab setTabText(String text) {
        tabBtn.setText(text);
        return this;
    }

    public BottomTab setTabDrawable(int drawableResId) {
        tabBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(drawableResId), null, null);
        return this;
    }

    public BottomTab setTabDrawable(Drawable drawable) {
        tabBtn.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        return this;
    }

    public BottomTab setHint(String msg) {
        msgTv.setText(msg);
        return this;
    }

    public BottomTab setHint(int msg) {
        msgTv.setText(String.valueOf(msg));
        return this;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    @Override
    public void setOnCheckedChangeWidgetListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeWidgetListener = listener;
    }

    private boolean mChecked = false;

    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            tabBtn.setChecked(mChecked);
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}
