package com.example.test3.base.web.server;

import android.text.TextUtils;
import android.view.TextureView;

import java.util.HashMap;
import java.util.PropertyResourceBundle;

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

        }
    }

    @Override
    public void getGradeInfo(String url, IResultCallback callback) {
        if (callback == null || TextUtils.isEmpty(url)) {
            return;
        }

    }
}
