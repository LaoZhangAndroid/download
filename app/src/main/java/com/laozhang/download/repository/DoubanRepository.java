package com.laozhang.download.repository;

import android.util.Log;

import com.laozhang.download.OkHttpUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class DoubanRepository {

    private static final String URL_TOP250 = "https://api.douban.com/v2/movie/top250?start=0&count=10";

    public String synchronizedGet() throws IOException {
        Request request = new Request.Builder()
                .url(URL_TOP250)
                .build();

        Response response = OkHttpUtil.getClient().newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }else {
            return "失败，错误码："+response.code();
        }
    }

    public void AsynchronizedGet(Callback callBack){
        Request request = new Request.Builder()
                .url(URL_TOP250)
                .build();

        OkHttpUtil.getClient().newCall(request).enqueue(callBack);
    }

    public void headerTest(){
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent","OkHttp header.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        OkHttpUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Headers headers = response.headers();
                for(int i=0; i<headers.size(); i++){
                    Log.i("TAG", headers.name(i)+": "+headers.value(i));
                }
            }
        });
    }

    public void postString() throws IOException {
        String body = "post提交的字符文本";

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), body))
                .build();

        Response response = OkHttpUtil.getClient().newCall(request).execute();
        if(response.isSuccessful()){
            Log.i("TAG", "success: "+response.body().string());
        }else {
            Log.i("TAG", "failure: "+response.message());
        }
    }

    public void postStream() throws IOException {
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse("text/x-markdown; charset=utf-8");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("hello\n");
                sink.writeUtf8("nihao\n");
                sink.writeUtf8("你好");
            }
        };

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();
        Response response = OkHttpUtil.getClient().newCall(request).execute();
        if(response.isSuccessful()){
            Log.i("TAG", "success: "+response.body().string());
        }else {
            Log.i("TAG", "failure: "+response.message());
        }
    }

    public void postFile() throws IOException {
        File file = new File("readMe.md");
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), file))
                .build();

        Response response = OkHttpUtil.getClient().newCall(request).execute();
        if(response.isSuccessful()){
            Log.i("TAG", "success: "+response.body().string());
        }else {
            Log.i("TAG", "failure: "+response.message());
        }
    }

    public void postFormParams() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "123")
                .build();

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(formBody)
                .build();
        Response response = OkHttpUtil.getClient().newCall(request).execute();
        if(response.isSuccessful()){
            Log.i("TAG", "success: "+response.body().string());
        }else {
            Log.i("TAG", "failure: "+response.message());
        }
    }

    public void postMultiPartRequest() throws IOException {
        RequestBody imgBody = RequestBody.create(MediaType.parse("image/png"), new File("hello/logo.png"));
        RequestBody multiPartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "logo")
                .addFormDataPart("file", "logo", imgBody)
                .build();

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(multiPartBody)
                .build();
        Response response = OkHttpUtil.getClient().newCall(request).execute();
        if(response.isSuccessful()){
            Log.i("TAG", "success: "+response.body().string());
        }else {
            Log.i("TAG", "failure: "+response.message());
        }
    }

    public void cacheGet() throws IOException {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build();

        Response response = OkHttpUtil.getClient().newCall(request).execute();

        Headers headers = response.headers();
        for(int i=0; i<headers.size(); i++){
            Log.i("TAG", headers.name(i)+": "+headers.value(i));
        }
        String body = response.body().string();
        if(response.isSuccessful()){
            Log.i("TAG", "response: "+response);
            Log.i("TAG", "cache response: "+response.cacheResponse());
            Log.i("TAG", "network response: "+response.networkResponse());
        }

        Response response1 = OkHttpUtil.getClient().newCall(request).execute();
        String body1 = response1.body().string();
        if(response1.isSuccessful()){
            Log.i("TAG", "response: "+response1);
            Log.i("TAG", "cache response: "+response1.cacheResponse());
            Log.i("TAG", "network response: "+response1.networkResponse());
        }
    }


}
