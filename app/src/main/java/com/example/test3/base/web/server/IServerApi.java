package com.example.test3.base.web.server;

import com.example.test3.base.web.OkHttp3Util;
import com.example.test3.base.web.server.download.ProgressCallback;

import java.util.Map;

public interface IServerApi {
    void getGradeInfo(String url, Map<String, String> params, IServerResultCallback callback);

    void getGradeInfo(String url, IServerResultCallback callback);

    void downloadFile(String url, String desPath, IServerResultCallback callback);

    void downloadFileWithProgress(String url, String desPath, IServerProgressCallback callback);

}
