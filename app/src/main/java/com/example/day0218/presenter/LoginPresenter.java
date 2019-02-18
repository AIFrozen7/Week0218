package com.example.day0218.presenter;

import com.example.day0218.MainActivity;
import com.example.day0218.api.Api;
import com.example.day0218.model.ILoginModel;
import com.example.day0218.model.LoginModel;

/**
 * @Auther: 李
 * @Date: 2019/2/18 08:49:19
 * @Description:
 */
public class LoginPresenter implements ILoginPresenter{
    LoginModel loginModel;
    MainActivity mainActivity;

    public LoginPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        loginModel = new LoginModel();
    }

    //防止内存泄露
    public void destroy(){
        if (mainActivity!=null){
            mainActivity = null;
        }
    }
    @Override
    public void getData() {
        loginModel.getData(Api.SHOP_LIST, new ILoginModel.ILoginCallBack() {
            @Override
            public void onSuccess(String data) {
                mainActivity.getData(data);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
