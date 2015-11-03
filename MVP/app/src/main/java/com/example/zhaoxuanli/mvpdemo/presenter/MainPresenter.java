package com.example.zhaoxuanli.mvpdemo.presenter;

import com.example.zhaoxuanli.mvpdemo.dto.ServerData;
import com.example.zhaoxuanli.mvpdemo.model.GetDataListener;
import com.example.zhaoxuanli.mvpdemo.model.INetWork;
import com.example.zhaoxuanli.mvpdemo.model.NetWork;
import com.example.zhaoxuanli.mvpdemo.ui.activity.IMainAView;

/**
 * Created by zhaoxuan.li on 2015/10/9.
 */
public class MainPresenter implements IMainAPresenter{

    private INetWork netWork;
    private IMainAView mainActivity;

    public MainPresenter(IMainAView mainActivity){
        this.mainActivity = mainActivity;
        netWork = new NetWork();
    }

    /**
     * view 调用 presenter 方法
     */
    @Override
    public void getServerData(){
        //启动Loding效果，如果Loading效果以启动，就不再重复请求服务器了
        //这里仅只做简单的重复请求判断，最终网络模块应该做更全面的重复请求判断（考虑后台数据轮询情况）
        if(mainActivity.showLoading())
            netWork.getMainData(new GetDataListener() {
                @Override
                public void getDataForNetWork(ServerData data) {
                    mainActivity.hideLoading();
                    mainActivity.setServerData(data);
                }
            }); //从服务器获取数据
    }






}
