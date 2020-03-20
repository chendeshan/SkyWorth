package com.example.test3.base.web.server;

import com.example.test3.base.web.bean.BaseBean;
import com.example.test3.base.web.bean.DownloadFileBean;

import java.util.ArrayList;
import java.util.List;

public class DownloadUpgradePackageManager {

    private DownloadState mCurrentState;
    private List<String> mDownloadSuccessPaths;

    public DownloadUpgradePackageManager() {
        mCurrentState = DownloadState.IDLE;
        mDownloadSuccessPaths = new ArrayList<>();
    }

    public void downloadPackages(final List<DownloadInfo> downloadInfos, final String destPath, final IDownloadCallback callback) {
        if (isDownloading()) {
            return;
        }

        mDownloadSuccessPaths.clear();
        mCurrentState = DownloadState.DOWNLOADING;

        download(downloadInfos, destPath, callback);
    }

    private void download(final List<DownloadInfo> downloadInfos, final String destPath, final IDownloadCallback callback) {
        if (downloadInfos.size() > 0) {
            String url = downloadInfos.get(0).getUrl();
            ServerApiFactory.getApi().downloadFile(url, destPath, new IServerResultCallback() {
                @Override
                public void onFail(Exception e) {
                    mCurrentState = DownloadState.ERROR;
                    callback.onFail(e);
                }

                @Override
                public void onSuccess(BaseBean response) {
                    DownloadFileBean downloadFileBean = (DownloadFileBean) response;
                    String filePath = downloadFileBean.getFilePath();
                    mDownloadSuccessPaths.add(filePath);
                    downloadInfos.remove(0);

                    download(downloadInfos, destPath, callback);
                }
            });
        } else {
            callback.onSuccess(mDownloadSuccessPaths);
            mCurrentState = DownloadState.IDLE;
        }
    }

    public List<String> getDownloadSuccessPaths() {
        return mDownloadSuccessPaths;
    }

    private boolean isDownloading() {
        return mCurrentState == DownloadState.DOWNLOADING;
    }

    private boolean isError() {
        return mCurrentState == DownloadState.ERROR;
    }

    public interface IDownloadCallback {
        void onFail(Exception e);

        void onSuccess(List<String> paths);
    }

    enum DownloadState {
        IDLE,
        DOWNLOADING,
        ERROR,
    }

    public static class DownloadInfo {
        private String md5;
        private String url;

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }


}
