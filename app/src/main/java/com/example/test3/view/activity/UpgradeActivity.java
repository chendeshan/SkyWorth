package com.example.test3.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.Environment.MEDIA_MOUNTED;

public class UpgradeActivity extends BaseActivity {
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
//        downloadPackages(getDownloadInfo());
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
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        showProgressDialog();
                        showNetUpgradingLayout();

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

    /*download*/
    private void downloadUpgradeInfo() {
        ServerApiFactory.getApi().getGradeInfo(Constant.UPGRADE_URL, createParam(), new IServerResultCallback() {
            @Override
            public void onFail(Exception e) {
                showNetUpgradeLayout();
                dismissProgressDialog();
                showNetFailDialog();

                Log.i(TAG, "download GradeInfo fail.");
//                downloadPackages(getDownloadInfo());
            }

            @Override
            public void onSuccess(BaseBean response) {
                List<UpgradeInfoBean.DataBean.CameraFwBean.FwBean> fwBeans = getFwBeans(response);

                if (!checkMemoryCache(fwBeans)) {
                    Log.i(TAG, "not enough memory.do not allow to download package.");
                    Toast.makeText(UpgradeActivity.this, "没有足够的空间", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<DownloadUpgradePackageManager.DownloadInfo> downloadInfos = getDownloadInfos(fwBeans);
                downloadPackages(downloadInfos);
            }
        });
    }

    private void downloadPackages(List<DownloadUpgradePackageManager.DownloadInfo> downloadInfos) {
        Log.i(TAG, "start to download package.");

        mDownloadUpgradePackageManager.downloadPackages(downloadInfos, getPath(), new DownloadUpgradePackageManager.IDownloadCallback() {
            @Override
            public void onFail(Exception e) {
                showNetUpgradeLayout();
                dismissProgressDialog();
                showNetFailDialog();
                Log.i(TAG, "download upgradePackage fail.");
            }

            @Override
            public void onSuccess(List<String> paths) {
                dismissProgressDialog();
                showNetUpgradeLayout();
                // TODO: 2020/3/20 upgrade
                Log.i(TAG, "download upgradePackage successfully.");
            }
        });

    }

    private String getPath() {
        String basePath;

        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            basePath = getExternalFilesDir("skyworth").getPath();
        } else {
            basePath = getFilesDir().getPath() + File.separator + "skyworth";
        }

//        String path = Environment.getExternalStorageDirectory().getPath() + "/skyworth";
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

    /*fake*/
    private void fakeDelay(TimerTask timerTask) {
        Timer timer = new Timer();
        timer.schedule(timerTask, 5000);
    }

    private Map<String, String> createParam() {
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

    private List<DownloadUpgradePackageManager.DownloadInfo> getDownloadInfo() {
        List<DownloadUpgradePackageManager.DownloadInfo> downloadInfos = new ArrayList<>();

        DownloadUpgradePackageManager.DownloadInfo info = new DownloadUpgradePackageManager.DownloadInfo();
        info.setMd5("5ea810395fc14f0ebc628e6e805de3a5");
        info.setUrl("http://soft.vpser.net/lnmp/lnmp1.6.tar.gz");
        downloadInfos.add(info);

        DownloadUpgradePackageManager.DownloadInfo info1 = new DownloadUpgradePackageManager.DownloadInfo();
        info1.setMd5("a58aaeaf0661a251d9f54b29c0d3d1ea");
        info1.setUrl("http://soft.vpser.net/lnmp/lnmp1.6-full.tar.gz");
        downloadInfos.add(info1);

        return downloadInfos;
    }

}
