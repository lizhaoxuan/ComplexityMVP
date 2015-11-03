package com.example.zhaoxuanli.mvpdemo.presenter;

import com.example.zhaoxuanli.mvpdemo.dto.ServerData;
import com.example.zhaoxuanli.mvpdemo.model.GetDataListener;
import com.example.zhaoxuanli.mvpdemo.model.INetWork;
import com.example.zhaoxuanli.mvpdemo.model.NetWork;
import com.example.zhaoxuanli.mvpdemo.ui.fragment.IOneFView;

/**
 * Created by zhaoxuan.li on 2015/10/9.
 */
public class OnePresenter  implements IOneFPresenter{

    private INetWork netWork;
    private IOneFView oneFragment;

    public OnePresenter(IOneFView oneFragment){
        this.oneFragment = oneFragment;
        netWork = new NetWork();
    }

    /*view 调用 P方法
    * 回调接口
    * m.get
    * */
    @Override
    public void getSQLiteData(){
        oneFragment.showToast(netWork.getOneData());

    }


}
