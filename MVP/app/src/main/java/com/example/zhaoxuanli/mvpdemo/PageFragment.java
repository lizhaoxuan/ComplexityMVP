package com.example.zhaoxuanli.mvpdemo;

import com.example.zhaoxuanli.mvpdemo.dto.ServerData;

/**
 * Created by zhaoxuan.li on 2015/10/11.
 */
public abstract class PageFragment extends BaseFragment {

    /*通常需要载入*/
    @Override
    public void onResume() {
        super.onResume();
        refreshView();
    }

    public abstract void refreshView();


    /**
     * Activity 供Fragment回调的接口
     */
    public interface IMainCallBack{
        /*取得Fragment共用的数据*/
        ServerData getCommonData();
        /*要求Fragment刷新数据*/
        void refreshCommonData();

    }
}
