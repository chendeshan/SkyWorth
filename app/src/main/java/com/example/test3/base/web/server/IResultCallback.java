package com.example.test3.base.web.server;

import com.example.test3.base.web.bean.BaseResponse;

public interface IResultCallback {
    void onFail(Exception e);

    void onSuccess(BaseResponse response);
}
