package com.example.test3.base.web.mapper;

import com.example.test3.base.web.bean.UpgradeInfoBean;
import com.google.gson.Gson;

public class Mapper {
    public static UpgradeInfoBean upgradeInfoJsonToBean(String json) {
        return new Gson().fromJson(json, UpgradeInfoBean.class);
    }
}
