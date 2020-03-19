package com.example.test3.view.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.test3.R;
import com.example.test3.view.widget.BaseAlertDialog;
import com.example.test3.view.widget.ProgressDialog;

public class UpgradeActivity extends Activity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
    }

    public void netUpgradeClick(View view) {
        showFailDialog();
    }

    public void manualUpgradeClick(View view) {
        showManualDialog();
    }

    public void showFailDialog() {
        BaseAlertDialog dialog = new BaseAlertDialog.Builder(this)
                .setTitle("升级失败了")
                .setMessage("是否重新升级")
                .setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelEnable(false)
                .build();

        dialog.show();
    }

    public void showManualDialog() {
        BaseAlertDialog dialog = new BaseAlertDialog.Builder(this)
                .setTitle("立即手动U盘安装升级")
                .setMessage("请确认已获取升级包并插入U盘")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelEnable(false)
                .build();

        dialog.show();
    }

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this, "升级时间较长，请保持网络正常，耐心等待", null);

        mProgressDialog.show();
    }

    public void dismissProgressDialog() {
        mProgressDialog.dismiss();
    }
}
