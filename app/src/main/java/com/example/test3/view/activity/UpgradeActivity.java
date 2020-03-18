package com.example.test3.view.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.test3.R;
import com.example.test3.view.widget.BaseAlertDialog;

public class UpgradeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
    }

    public void netUpgradeClick(View view) {
        showFailDialog();
    }

    public void manualUpgradeClick(View view) {

    }

    public void showFailDialog() {
        BaseAlertDialog dialog = new BaseAlertDialog.Builder(this)
                .setTitle("升级失败了")
                .setMessage("是否重新升级")
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("再想想", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelEnable(false)
                .build();

        dialog.show();
    }
}
