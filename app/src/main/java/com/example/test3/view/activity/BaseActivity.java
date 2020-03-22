package com.example.test3.view.activity;

import android.app.Activity;

import com.example.test3.urils.ToastUtil;

public class BaseActivity extends Activity {
    public void showToast(int msgId) {
        showToast(getString(msgId));
    }

    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }

}
