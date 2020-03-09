package com.cloudcreativity.peoplepass_zy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cloudcreativity.peoplepass_zy.base.BaseApp;

/**
 * 网络状态，操作的工具
 */
public class NetworkUtils {
    /**
     * @return 网络是否可用
     */
    public static boolean isAvailable(){
        ConnectivityManager manager = (ConnectivityManager) BaseApp.app.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return null!=activeNetworkInfo&&activeNetworkInfo.isAvailable();

    }
}
