package com.example.day0218;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.day0218.adapter.RecyclerViewAdapter;
import com.example.day0218.bean.JsonBean;
import com.example.day0218.presenter.LoginPresenter;
import com.example.day0218.view.ILoginView;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ILoginView {

    private RecyclerView recyclerview;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = findViewById(R.id.recyclerview);
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(linearLayoutManager);
        loginPresenter = new LoginPresenter(this);
        loginPresenter.getData();
    }

    @Override
    public void getData(String data) {
        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(data, JsonBean.class);
        final List<JsonBean.DataBeanX.DataBean> list = jsonBean.getData().getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list,MainActivity.this);
                recyclerview.setAdapter(recyclerViewAdapter);
            }
        });
    }

    //防止内存泄露

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.destroy();
    }
}
