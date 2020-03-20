package com.example.test3.urils;

import android.os.Environment;

public class Constant {
    /*url*/
    public static final String BASE_URL = "http://172.22.201.90:8000";
    public static final String UPGRADE_URL = BASE_URL + "/tvhallapi/tvcamerafw/upgrade";

    /*filePath*/
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath();
    public static final String APP_PATH = BASE_PATH + "/skyworth";

}
