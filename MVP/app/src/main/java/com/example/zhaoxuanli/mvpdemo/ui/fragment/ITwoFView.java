package com.example.zhaoxuanli.mvpdemo.ui.fragment;

import com.example.zhaoxuanli.mvpdemo.dto.ServerData;
import com.example.zhaoxuanli.mvpdemo.ui.activity.MainActivity;

/**
 * Created by zhaoxuan.li on 2015/10/9.
 */
public interface ITwoFView {

    /**
     * OneFragment 自己的V方法
     * **/

    void showToast(String str);



    /***
     *
     * F_v  ----->  F_p --->  A_v -----> A_p  ---> m ---> A_v  ---->F_p --->F_v
     *
     */
}
