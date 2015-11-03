package com.example.zhaoxuanli.mvpdemo.presenter;

import com.example.zhaoxuanli.mvpdemo.dto.ServerData;
import com.example.zhaoxuanli.mvpdemo.model.GetDataListener;
import com.example.zhaoxuanli.mvpdemo.model.INetWork;
import com.example.zhaoxuanli.mvpdemo.model.NetWork;
import com.example.zhaoxuanli.mvpdemo.ui.fragment.ITwoFView;

/**
 * Created by zhaoxuan.li on 2015/10/9.
 */
public class TwoPresenter  implements ITwoFPresenter{

    private INetWork netWork;
    private ITwoFView twoFragment;

    public TwoPresenter(ITwoFView twoFragment){
        this.twoFragment = twoFragment;
        netWork = new NetWork();
    }

    /*view 调用 P方法
    * 模拟从数据库获取数据
    * */
    @Override
    public void getSQLiteData(){
        twoFragment.showToast(netWork.getTwoData());
    }



}
