package com.example.zhaoxuanli.mvpdemo.model;

import android.os.Handler;

import com.example.zhaoxuanli.mvpdemo.dto.ServerData;

/**
 * Created by zhaoxuan.li on 2015/10/9.
 */
public class NetWork implements INetWork {
    private static int MAIN = 0;
    private static int ONE = 0;
    private static int TWO = 0;

    private Handler mHandler = new Handler();

    @Override
    public void getMainData(final GetDataListener listener) {

        new Thread(){
            @Override
            public void run() {
                try{
                    sleep(2000);
                }catch (Exception e){}

                final String oneStr = "OneFragment 第"+ ONE++ +"次获取数据";
                final String twoStr = "TwoFragment 第"+ TWO++ +"次获取数据";

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.getDataForNetWork(new ServerData(oneStr, twoStr));
                    }
                });
            }
        }.start();
    }
    @Override
    public String getOneData() {
        return "OneFragment 第"+ ONE++ +"次访问数据库";
    }

    @Override
    public String getTwoData() {
        return "TwoFragment 第"+ TWO++ +"次访问数据库";
    }
}
