package com.example.zhaoxuanli.mvpdemo.ui.activity;

import com.example.zhaoxuanli.mvpdemo.dto.ServerData;

/**
 * Created by zhaoxuan.li on 2015/10/9.
 */
public interface IMainAView {

    void setServerData(ServerData data);

    boolean showLoading();

    void hideLoading();
}
