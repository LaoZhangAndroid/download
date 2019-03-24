package com.laozhang.download;

import android.os.Environment;
import android.util.Log;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class OkHttpUtil {

    private static volatile OkHttpClient client;

    public static OkHttpClient getClient(){
        if(client == null){
            synchronized (OkHttpUtil.class){
                client = new OkHttpClient.Builder()
                        .cache(getCache())
                        .build();
            }
        }
        return client;
    }

    private static Cache getCache(){
        int cacheSize = 10 * 1024 * 1024;
        File cacheFile = new File(Environment.getExternalStorageDirectory().getPath() + "/cache_test/");
        if(!cacheFile.exists()){
            boolean b = cacheFile.mkdirs();
            Log.i("TAG", "getCache: "+cacheFile.getAbsolutePath());
            Log.i("TAG", "mk cache: "+b);
        }
        return new Cache(cacheFile ,cacheSize);
    }

}
