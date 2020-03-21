package com.example.test3.base.web.server;

import com.example.test3.base.web.bean.BaseBean;
import com.example.test3.base.web.bean.DownloadFileBean;
import com.example.test3.urils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadUpgradePackageManager {

    private DownloadState mCurrentState;
    private List<String> mDownloadSuccessPaths;
    private int mReTryCount;

    public DownloadUpgradePackageManager() {
        mCurrentState = DownloadState.IDLE;
        mDownloadSuccessPaths = new ArrayList<>();
    }

    public void downloadPackages(final List<DownloadInfo> downloadInfos, final String destPath, final IDownloadCallback callback) {
        if (isDownloading()) {
            return;
        }

        mReTryCount = 0;
        mDownloadSuccessPaths.clear();
        mCurrentState = DownloadState.DOWNLOADING;

        download(downloadInfos, destPath, callback);
    }

    private void download(final List<DownloadInfo> downloadInfos, final String destPath, final IDownloadCallback callback) {
        if (downloadInfos.size() > 0) {
            final DownloadInfo info = downloadInfos.get(0);
            String url = info.getUrl();
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

                    if (checkFileMd5(filePath, info.getMd5())) {
                        downloadInfos.remove(0);
                        mDownloadSuccessPaths.add(filePath);
                        download(downloadInfos, destPath, callback);
                    } else {
                        if (mReTryCount <= 3) {
                            mReTryCount++;
                            download(downloadInfos, destPath, callback);
                        } else {
                            callback.onFail(new Exception("download is more then three times."));
                        }

                    }

                }
            });
        } else {
            callback.onSuccess(mDownloadSuccessPaths);
            mCurrentState = DownloadState.IDLE;
        }
    }

    private boolean checkFileMd5(String path, String srcMd5) {
        String fileMD5 = CommonUtil.getFileMD5(new File(path));

        return srcMd5.equals(fileMD5);
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
