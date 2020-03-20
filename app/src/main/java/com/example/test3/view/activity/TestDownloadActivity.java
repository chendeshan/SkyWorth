package com.example.test3.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.test3.R;
import com.example.test3.base.web.bean.BaseBean;
import com.example.test3.base.web.bean.BaseResponse;
import com.example.test3.base.web.bean.UpgradeInfoBean;
import com.example.test3.base.web.server.IResultCallback;
import com.example.test3.base.web.server.ServerApiFactory;
import com.example.test3.urils.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDownloadActivity extends AppCompatActivity {

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

    public void downloadUpgradeInfo(View view) {
        ServerApiFactory.getApi().getGradeInfo(Constant.UPGRADE_URL, createParam(), new IResultCallback() {
            @Override
            public void onFail(Exception e) {
                updateCurrentProgress("下载失败：" + e.getMessage());
            }

            @Override
            public void onSuccess(BaseBean response) {
                updateCurrentProgress("下载成功");

                String fileUrl = getFileUrl(response);
                downloadFile(fileUrl, Constant.APP_PATH);
            }
        });
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

        ServerApiFactory.getApi().downloadFile(url, destPath, new IResultCallback() {
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
