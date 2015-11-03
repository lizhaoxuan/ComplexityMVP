package com.example.zhaoxuanli.mvpdemo.model;

/**
 * Created by zhaoxuan.li on 2015/10/9.
 */
public interface INetWork {

    void getMainData(GetDataListener listener);

    String getOneData();

    String getTwoData();

}
