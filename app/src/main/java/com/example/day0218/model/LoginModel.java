package com.example.day0218.model;

import com.example.day0218.net.OkHttp3;

/**
 * @Auther: 李
 * @Date: 2019/2/18 09:06:18
 * @Description:
 */
public class LoginModel implements ILoginModel{
    @Override
    public void getData(String url, final ILoginCallBack iLoginCallBack) {
        OkHttp3.getInstance().doGet(url, new OkHttp3.NetCallBack() {
            @Override
            public void onSuccess(String data) {
                iLoginCallBack.onSuccess(data);
            }

            @Override
            public void onFailed(Exception e) {
                iLoginCallBack.onFailed(e);
            }
        });
    }
}
