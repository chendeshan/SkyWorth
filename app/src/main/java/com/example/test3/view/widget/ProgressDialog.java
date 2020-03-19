package com.example.test3.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test3.R;

public class ProgressDialog extends Dialog {
    private TextView mMessageView;
    private ImageView spaceshipImage;
    private Context mContext;
    private TextView mButton;

    public ProgressDialog(Context context, int message, View.OnClickListener listener) {
        super(context, R.style.BorgDialog);

        String messageString = context.getString(message);
        init(context, messageString, listener);
    }

    public ProgressDialog(Context context,String message,View.OnClickListener listener) {
        super(context, R.style.BorgDialog);

        init(context, message, listener);
    }

    private void init(Context context,String message,View.OnClickListener listener) {
        mContext = context;

        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.base_alert_dialog_login, null);

        spaceshipImage = (ImageView) inflate.findViewById(R.id.dialog_image);

        mMessageView = (TextView) inflate.findViewById(R.id.dialog_message);// 提示文字
        mMessageView.setText(message);

        // 取消button
        mButton = (TextView) inflate.findViewById(R.id.dialog_cancle);

        if (listener == null) {
            mButton.setVisibility(View.GONE);
        } else {
            mButton.setOnClickListener(listener);
        }

        setContentView(inflate);
        setCancelable(false);
    }

    public void setMessage(String message) {
        if (mMessageView != null) {
            mMessageView.setText(message);
        }
    }

    public void setMessage(int message) {
        if (mMessageView != null) {
            mMessageView.setText(message);
        }
    }

    public void setButtonMessage(String message) {
        mButton.setText(message);
    }

    @Override
    public void show() {
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(mContext, R.anim.loading_animation);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);

        super.show();
    }

    @Override
    public void dismiss() {
        spaceshipImage.clearAnimation();

        super.dismiss();
    }

}
