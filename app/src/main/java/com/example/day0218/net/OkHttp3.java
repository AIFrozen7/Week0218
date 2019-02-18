package com.example.day0218.net;

import android.os.Handler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Auther: 李
 * @Date: 2019/2/18 08:49:50
 * @Description:
 */
public class OkHttp3 {
    private static OkHttpClient httpClient;
    private static volatile  OkHttp3 instance;
    private Handler handler = new Handler();
    //拦截器
    private Interceptor getAppInterceptor(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拦截前
                Request request = chain.request();
                //拦截后
                Response response = chain.proceed(request);
                return response;
            }
        };
        return interceptor;
    }
    private OkHttp3(){
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(getAppInterceptor())//添加拦截器
                .connectTimeout(3000, TimeUnit.SECONDS)//设置连接超时
                .readTimeout(3000,TimeUnit.SECONDS)//设置读取超时
                .build();
    }
    //单例模式
    public static OkHttp3 getInstance(){
        if (instance==null){
            synchronized (OkHttp3.class){//同步锁
                instance = new OkHttp3();
            }
        }
        return instance;
    }
    public interface NetCallBack{
        void onSuccess(String data);
        void onFailed(Exception e);
    }
    //Get
    public void doGet(String url, final NetCallBack netCallBack){
        //Request请求
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onSuccess(data);
                    }
                });
            }
        });
    }
    //Post
    public void doPost(String url, Map<String,String> map, final NetCallBack netCallBack){
        FormBody.Builder builder = new FormBody.Builder();
        for(String key:map.keySet()){
            builder.add(key,map.get(key));
        }
        Request request = new Request.Builder()
                .post(builder.build())
                .url(url)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onSuccess(data);
                    }
                });
            }
        });
    }
}
