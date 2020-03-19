package com.example.test3.base.web.mapper;

import com.example.test3.base.web.bean.BaseResponse;
import com.example.test3.base.web.bean.UpgradeInfoBean;
import com.google.gson.Gson;

public class Mapper {
    public static BaseResponse<UpgradeInfoBean> upgradeInfoJsonToBean(String json) {
        return ((BaseResponse<UpgradeInfoBean>) new Gson().fromJson(json, BaseResponse.class));
    }
}
