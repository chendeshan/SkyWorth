package com.example.test3.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3.R;
import com.example.test3.base.web.bean.BaseBean;
import com.example.test3.base.web.bean.UpgradeInfoBean;
import com.example.test3.base.web.server.IServerResultCallback;
import com.example.test3.base.web.server.ServerApiFactory;
import com.example.test3.urils.CommonUtil;
import com.example.test3.urils.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDownloadActivity extends BaseActivity {

    private TextView mCurrentProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_download);
        initView();
    }

    private void initView() {
        mCurrentProgressView = (TextView) findViewById(R.id.activity_test_download_current_progress);
    }

    public void downloadUpgradeInfoClick(View view) {
        downloadGradeInfo();
    }

    private void downloadGradeInfo() {
        ServerApiFactory.getApi().getGradeInfo(Constant.UPGRADE_URL, createParam(), new IServerResultCallback() {
            @Override
            public void onFail(Exception e) {
                updateCurrentProgress("下载失败：" + e.getMessage());
            }

            @Override
            public void onSuccess(BaseBean response) {
                List<UpgradeInfoBean.DataBean.CameraFwBean.FwBean> fwBeans = getFwBeans(response);

                if (!checkMemoryCache(fwBeans)) {
                    Toast.makeText(TestDownloadActivity.this, "没有足够的空间", Toast.LENGTH_SHORT).show();
                    return;
                }

                downloadFiles(fwBeans);
            }
        });
    }

    private void downloadFiles(final List<UpgradeInfoBean.DataBean.CameraFwBean.FwBean> fwBeans) {
        String path = getPath();

        if (fwBeans.size() > 0) {
            final UpgradeInfoBean.DataBean.CameraFwBean.FwBean fwBean = fwBeans.get(0);
            String url = fwBean.getFwInfo().getUrl();

            ServerApiFactory.getApi().downloadFile(url, path, new IServerResultCallback() {
                @Override
                public void onFail(Exception e) {

                }

                @Override
                public void onSuccess(BaseBean response) {
                    fwBeans.remove(0);
                    downloadFiles(fwBeans);
                }
            });

        }

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

    private String getFileUrl(BaseBean response) {
        UpgradeInfoBean upgradeInfoBean = (UpgradeInfoBean) response;
        UpgradeInfoBean.DataBean data = upgradeInfoBean.getData();
        UpgradeInfoBean.DataBean.CameraFwBean camera_fw = data.getCamera_fw();
        List<UpgradeInfoBean.DataBean.CameraFwBean.FwBean> fw = camera_fw.getFw();
        UpgradeInfoBean.DataBean.CameraFwBean.FwBean fwBean = fw.get(0);
        UpgradeInfoBean.DataBean.CameraFwBean.FwBean.FwInfoBean fwInfo = fwBean.getFwInfo();
        String url = fwInfo.getUrl();

        return url;
    }

    private void downloadFile(String url, String destPath) {
        updateCurrentProgress("下载文件");

        ServerApiFactory.getApi().downloadFile(url, destPath, new IServerResultCallback() {
            @Override
            public void onFail(Exception e) {
                updateCurrentProgress("下载文件失败");
            }

            @Override
            public void onSuccess(BaseBean response) {
                updateCurrentProgress("下载文件成功");
            }
        });
    }

    private String getPath() {
        String basePath = getExternalFilesDir("skyworth").getPath();

        return basePath;
    }

    private void updateCurrentProgress(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCurrentProgressView.setText(message);
            }
        });
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
}
