package com.example.test3.base.web.server;

import java.util.Map;

public interface IServerApi {
    void getGradeInfo(String url, Map<String, String> params, IResultCallback callback);

    void getGradeInfo(String url, IResultCallback callback);

    void downloadFile(String url, String desPath, IResultCallback callback);
}
