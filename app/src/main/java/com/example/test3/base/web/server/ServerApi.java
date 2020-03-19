package com.example.test3.base.web.server;

import android.text.TextUtils;

import com.example.test3.base.web.OkHttp3Util;
import com.example.test3.base.web.bean.BaseResponse;
import com.example.test3.base.web.bean.UpgradeInfoBean;
import com.example.test3.base.web.mapper.Mapper;

import java.util.HashMap;

import okhttp3.Request;

class ServerApi implements IServerApi {

    private static ServerApi mServerApi;

    private ServerApi() {

    }

    public static ServerApi getInstance() {
        if (mServerApi == null) {
            mServerApi = new ServerApi();
        }

        return mServerApi;
    }

    @Override
    public void getGradeInfo(String url, HashMap<String, String> params, IResultCallback callback) {
        if (callback == null || TextUtils.isEmpty(url)) {
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
        if (callback == null || TextUtils.isEmpty(url)) {
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

                BaseResponse<UpgradeInfoBean> upgradeInfoBeanBaseResponse = Mapper.upgradeInfoJsonToBean(responseString);
                callback.onSuccess(upgradeInfoBeanBaseResponse);
            }
        });

    }
}
