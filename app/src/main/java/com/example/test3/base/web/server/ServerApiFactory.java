package com.example.test3.base.web.server;

import android.util.Log;

public class ServerApiFactory {
    private static final String TAG = "ServerApiFactory";

    public static IServerApi getApi() {
        return getApi(ServerApiType.DEFAULT);
    }

    public static IServerApi getApi(ServerApiType type) {
        IServerApi serverApi = null;

        switch (type) {
            case DEFAULT:
                serverApi = ServerApi.getInstance();
                break;
                default:
                    Log.e(TAG, "no this ServerApiType. ");
        }

        return serverApi;
    }


}
