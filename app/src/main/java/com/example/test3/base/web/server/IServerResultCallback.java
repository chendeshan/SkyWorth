package com.example.test3.base.web.server;

import com.example.test3.base.web.bean.BaseBean;

public interface IServerResultCallback {
    void onFail(Exception e);

    void onSuccess(BaseBean response);
}
