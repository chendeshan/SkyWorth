package com.example.test3.base.web.server;

import android.os.strictmode.IntentReceiverLeakedViolation;
import android.text.TextUtils;

import com.example.test3.base.web.OkHttp3Util;
import com.example.test3.base.web.bean.BaseResponse;
import com.example.test3.base.web.bean.DownloadFileBean;
import com.example.test3.base.web.bean.UpgradeInfoBean;
import com.example.test3.base.web.mapper.Mapper;

import java.util.Map;

import okhttp3.Request;

class ServerApi implements IServerApi {

    private static ServerApi mServerApi;

    private ServerApi() {

    }

    static ServerApi getInstance() {
        if (mServerApi == null) {
            mServerApi = new ServerApi();
        }

        return mServerApi;
    }

    @Override
    public void getGradeInfo(String url, Map<String, String> params, IResultCallback callback) {
        if (!checkParamsUrlAndCallback(url, callback)) {
            return;
        }

        if (params == null || params.isEmpty()) {
            getGradeInfo(url, callback);
        } else {
            String tempUrl = OkHttp3Util.spliceParam(url, params);
            getGradeInfo(tempUrl, callback);
        }
    }

    @Override
    public void getGradeInfo(String url, final IResultCallback callback) {
        if (!checkParamsUrlAndCallback(url, callback)) {
            return;
        }

        OkHttp3Util.getDataAsync(url, new OkHttp3Util.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                callback.onFail(e);
            }

            @Override
            public void onResponse(Object response) {
                String responseString = (String) response;
                UpgradeInfoBean upgradeInfoBeanBaseResponse = Mapper.upgradeInfoJsonToBean(responseString);
                callback.onSuccess(upgradeInfoBeanBaseResponse);
            }
        });

    }

    @Override
    public void downloadFile(String url, String destPath, final IResultCallback callback) {
        if (!checkParamsUrlAndCallback(url, callback)) {
            return;
        }

        OkHttp3Util.downloadFileAsync(url, destPath, new OkHttp3Util.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                callback.onFail(e);
            }

            @Override
            public void onResponse(Object response) {
                DownloadFileBean downloadFileBean = new DownloadFileBean();
                downloadFileBean.setFilePath(((String) response));

                callback.onSuccess(downloadFileBean);

            }
        });


    }


    private boolean checkParamsUrlAndCallback(String url, IResultCallback callback) {
        return callback != null && !TextUtils.isEmpty(url);
    }
}
