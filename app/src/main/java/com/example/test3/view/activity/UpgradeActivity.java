package com.example.test3.view.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.test3.R;
import com.example.test3.base.web.bean.BaseBean;
import com.example.test3.base.web.bean.UpgradeInfoBean;
import com.example.test3.base.web.server.DownloadUpgradePackageManager;
import com.example.test3.base.web.server.IServerResultCallback;
import com.example.test3.base.web.server.ServerApiFactory;
import com.example.test3.urils.CommonUtil;
import com.example.test3.urils.Constant;
import com.example.test3.view.widget.BaseAlertDialog;
import com.example.test3.view.widget.ProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class UpgradeActivity extends Activity {
    private static final String TAG = "UpgradeActivity";
    private DownloadUpgradePackageManager mDownloadUpgradePackageManager = new DownloadUpgradePackageManager();

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
        downloadUpgradeInfo();
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
                        downloadUpgradeInfo();
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

    private void downloadUpgradeInfo() {
        ServerApiFactory.getApi().getGradeInfo(Constant.UPGRADE_URL, createParam(), new IServerResultCallback() {
            @Override
            public void onFail(Exception e) {
                showNetFailDialog();
            }

            @Override
            public void onSuccess(BaseBean response) {
                List<UpgradeInfoBean.DataBean.CameraFwBean.FwBean> fwBeans = getFwBeans(response);

                if (!checkMemoryCache(fwBeans)) {
                    Toast.makeText(UpgradeActivity.this, "没有足够的空间", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<DownloadUpgradePackageManager.DownloadInfo> downloadInfos = getDownloadInfos(fwBeans);
                mDownloadUpgradePackageManager.downloadPackages(downloadInfos, getPath(), new DownloadUpgradePackageManager.IDownloadCallback() {
                    @Override
                    public void onFail(Exception e) {
                        showNetFailDialog();
                    }

                    @Override
                    public void onSuccess(List<String> paths) {
                        // TODO: 2020/3/20 upgrade
                    }
                });
            }
        });
    }

    private String getPath() {
        String basePath = getExternalFilesDir("skyworth").getPath();

        return basePath;
    }


    private List<DownloadUpgradePackageManager.DownloadInfo> getDownloadInfos(List<UpgradeInfoBean.DataBean.CameraFwBean.FwBean> fwBeans) {
        List<DownloadUpgradePackageManager.DownloadInfo> infos = new ArrayList<>();

        for (UpgradeInfoBean.DataBean.CameraFwBean.FwBean fwBean : fwBeans) {
            UpgradeInfoBean.DataBean.CameraFwBean.FwBean.FwInfoBean fwInfo = fwBean.getFwInfo();
            String url = fwInfo.getUrl();
            String md5 = fwInfo.getMd5();

            DownloadUpgradePackageManager.DownloadInfo info = new DownloadUpgradePackageManager.DownloadInfo();
            info.setUrl(url);
            info.setMd5(md5);

            infos.add(info);
        }

        return infos;
    }

    private boolean checkMemoryCache(List<UpgradeInfoBean.DataBean.CameraFwBean.FwBean> fwBeans) {
        long neededCache = 0;

        for (UpgradeInfoBean.DataBean.CameraFwBean.FwBean fwBean : fwBeans) {
            UpgradeInfoBean.DataBean.CameraFwBean.FwBean.FwInfoBean fwInfo = fwBean.getFwInfo();
            int size = fwInfo.getSize();

            neededCache += size;
        }

        long availMemory = CommonUtil.getAvailMemory(this);

        if (neededCache * 2 > availMemory) {
            return false;
        }

        return true;
    }

    private List<UpgradeInfoBean.DataBean.CameraFwBean.FwBean> getFwBeans(BaseBean response) {
        UpgradeInfoBean upgradeInfoBean = (UpgradeInfoBean) response;
        UpgradeInfoBean.DataBean data = upgradeInfoBean.getData();
        UpgradeInfoBean.DataBean.CameraFwBean camera_fw = data.getCamera_fw();
        List<UpgradeInfoBean.DataBean.CameraFwBean.FwBean> fw = camera_fw.getFw();

        return fw;
    }

    private Map<String,String> createParam() {
        Map<String, String> params = new HashMap<>();

        params.put("cameraModel", "SWC1");
        params.put("cameraHwVersion", "cm400pro");
        params.put("tvModel", "G31");
        params.put("tvChip", "6H70");
        params.put("cameraFwLVerson", "001");
        params.put("cameraFwSVersion", "01");
        params.put("motorModel", "mt1");

        params.put("mtVersion", "001");
        params.put("screenVersion", "001");
        params.put("ts", "1584338851");
        params.put("token", "b888c2205d6cce32af3e39dd158b4f41");

        return params;
    }

}
