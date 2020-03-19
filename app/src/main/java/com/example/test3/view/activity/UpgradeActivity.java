package com.example.test3.view.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.test3.R;
import com.example.test3.view.widget.BaseAlertDialog;
import com.example.test3.view.widget.ProgressDialog;

import java.util.Timer;
import java.util.TimerTask;

public class UpgradeActivity extends Activity {

    private ProgressDialog mProgressDialog;

    private LinearLayout mNetUpgradeLayout;
    private LinearLayout mNetUpgradingLayout;
    private LinearLayout mManualUpgradeLayout;
    private LinearLayout mManualUpgradingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        initView();
    }

    private void initView() {
        mNetUpgradeLayout = (LinearLayout) findViewById(R.id.activity_upgrade_net_layout);
        mNetUpgradingLayout = (LinearLayout) findViewById(R.id.activity_upgrade_loading_net_layout);

        mManualUpgradeLayout = (LinearLayout) findViewById(R.id.activity_upgrade_manual_layout);
        mManualUpgradingLayout = (LinearLayout) findViewById(R.id.activity_upgrade_loading_manual_layout);
    }

    /*click*/
    public void netUpgradeClick(View view) {
        showProgressDialog();
        showNetUpgradingLayout();

        netFakeDelay(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showNetUpgradeLayout();
                        dismissProgressDialog();
                    }
                });
            }
        });
    }



    public void manualUpgradeClick(View view) {
        showManualDialog();
    }

    /*view*/
    private void showNetUpgradeLayout() {
        mNetUpgradeLayout.setVisibility(View.VISIBLE);
        mNetUpgradingLayout.setVisibility(View.GONE);
    }

    private void showNetUpgradingLayout() {
        mNetUpgradingLayout.setVisibility(View.VISIBLE);
        mNetUpgradeLayout.setVisibility(View.GONE);
    }

    private void showManualUpgradeLayout() {
        mManualUpgradeLayout.setVisibility(View.VISIBLE);
        mManualUpgradingLayout.setVisibility(View.GONE);
    }

    private void showManualUpgradingLayout() {
        mManualUpgradingLayout.setVisibility(View.VISIBLE);
        mManualUpgradeLayout.setVisibility(View.GONE);
    }

    /*dialog*/
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

    /*fake*/

    private void netFakeDelay(TimerTask timerTask) {
        Timer timer = new Timer();
        timer.schedule(timerTask, 5000);
    }


}
