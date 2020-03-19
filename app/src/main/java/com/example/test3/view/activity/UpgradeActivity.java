package com.example.test3.view.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
    private RelativeLayout mNetButton;
    private RelativeLayout mManualButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        initView();
    }

    private void initView() {
        mNetUpgradeLayout = (LinearLayout) findViewById(R.id.activity_upgrade_net_layout);
        mNetUpgradingLayout = (LinearLayout) findViewById(R.id.activity_upgrade_loading_net_layout);
        mNetButton = (RelativeLayout) findViewById(R.id.activity_upgrade_net_button);

        mManualUpgradeLayout = (LinearLayout) findViewById(R.id.activity_upgrade_manual_layout);
        mManualUpgradingLayout = (LinearLayout) findViewById(R.id.activity_upgrade_loading_manual_layout);
        mManualButton = (RelativeLayout) findViewById(R.id.activity_upgrade_manual_button);
    }

    /*click*/
    public void netUpgradeClick(View view) {
        showProgressDialog();
        showNetUpgradingLayout();

        fakeDelay(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showNetFailDialog();
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
        mNetButton.setEnabled(true);
    }

    private void showNetUpgradingLayout() {
        mNetUpgradingLayout.setVisibility(View.VISIBLE);
        mNetUpgradeLayout.setVisibility(View.GONE);
        mNetButton.setEnabled(false);
    }

    private void showManualUpgradeLayout() {
        mManualUpgradeLayout.setVisibility(View.VISIBLE);
        mManualUpgradingLayout.setVisibility(View.GONE);
        mManualButton.setEnabled(true);
    }

    private void showManualUpgradingLayout() {
        mManualUpgradingLayout.setVisibility(View.VISIBLE);
        mManualUpgradeLayout.setVisibility(View.GONE);
        mManualButton.setEnabled(false);
    }

    /*dialog*/
    private void showNetFailDialog() {
        BaseAlertDialog dialog = new BaseAlertDialog.Builder(this)
                .setTitle(R.string.activity_upgrade_net_dialog_title)
                .setMessage(R.string.activity_upugrade_net_dialog_message)
                .setNegativeButton(R.string.text_see_see, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showNetUpgradeLayout();
                        dismissProgressDialog();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showNetUpgradeLayout();
                        dismissProgressDialog();
                        dialog.dismiss();
                    }
                })
                .setCancelEnable(false)
                .build();

        dialog.show();
    }

    private void showManualDialog() {
        BaseAlertDialog dialog = new BaseAlertDialog.Builder(this)
                .setTitle(R.string.activity_upgrade_manual_dialog_title)
                .setMessage(R.string.activity_upgrade_dialog_manual_message)
                .setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showManualUpgradingLayout();

                        fakeDelay(new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        showManualUpgradeLayout();
                                    }
                                });
                            }
                        });

                    }
                })
                .setNegativeButton(R.string.text_see_see, new DialogInterface.OnClickListener() {
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
        mProgressDialog = new ProgressDialog(this, getString(R.string.activity_upgrade_progress_dialog_message), null);

        mProgressDialog.show();
    }

    private void dismissProgressDialog() {
        mProgressDialog.dismiss();
    }

    /*fake*/
    private void fakeDelay(TimerTask timerTask) {
        Timer timer = new Timer();
        timer.schedule(timerTask, 5000);
    }

}
