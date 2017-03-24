package com.example.guweihua.databinding.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by guweihua on 2017/3/23.
 */

public class NetUtils {
    public static boolean isConnected(Context ctx){
       try {
           ConnectivityManager systemService = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
           NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
           boolean connected = activeNetworkInfo.isConnected();
           return connected;
       }catch (Exception e){
           return false;
       }
    }
}
