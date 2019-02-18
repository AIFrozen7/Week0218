package com.example.day0218.model;

/**
 * @Auther: 李
 * @Date: 2019/2/18 09:06:30
 * @Description:
 */
public interface ILoginModel {
    void getData(String url,ILoginCallBack iLoginCallBack);
    interface ILoginCallBack{
        void onSuccess(String data);
        void onFailed(Exception e);
    }
}
