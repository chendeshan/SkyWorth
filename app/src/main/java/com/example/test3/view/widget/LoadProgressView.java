package com.example.test3.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.test3.R;

public class LoadProgressView extends ImageView {
    private Drawable mDrawable;

    public LoadProgressView(Context context) {
        this(context, null);
    }

    public LoadProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.progressBarStyle);
    }

    public LoadProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mDrawable = getDrawable();

        if (mDrawable == null) {
            mDrawable = getResources().getDrawable(R.mipmap.tv_dialog_loading);
        }

        if (mDrawable == null) {
            return;
        }

        mDrawable.setBounds(0, 0, (int)(mDrawable.getIntrinsicWidth() * 0.6), (int)(mDrawable.getIntrinsicHeight() * 0.6));

        setImageDrawable(mDrawable);

        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        startAnimation(hyperspaceJumpAnimation);
    }
}
