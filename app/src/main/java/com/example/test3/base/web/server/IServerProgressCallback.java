package com.example.test3.base.web.server;

public interface IServerProgressCallback extends IServerResultCallback {
    void onProgress(long numBytes, long totalBytes, float percent, float speed);
}
