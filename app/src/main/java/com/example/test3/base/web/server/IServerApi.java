package com.example.test3.base.web.server;

import java.util.HashMap;

public interface IServerApi {
    void getGradeInfo(String url, HashMap<String, String> params, IResultCallback callback);

    void getGradeInfo(String url, IResultCallback callback);
}
