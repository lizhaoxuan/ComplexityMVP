package com.example.zhaoxuanli.mvpdemo.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhaoxuanli.mvpdemo.PageFragment;
import com.example.zhaoxuanli.mvpdemo.R;
import com.example.zhaoxuanli.mvpdemo.dto.ServerData;
import com.example.zhaoxuanli.mvpdemo.presenter.TwoPresenter;
import com.example.zhaoxuanli.mvpdemo.ui.activity.MainActivity;

/**
 * Created by zhaoxuan.li on 2015/10/9.
 */
public class ThreeFragment extends PageFragment {

    private TextView threeText;
    private ServerData serverData;

    private IMainCallBack activityCallBack;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activityCallBack = (IMainCallBack)getActivity();

        initView();
    }


    private void initView(){
        threeText = (TextView)getActivity().findViewById(R.id.threeText);

    }

    @Override
     public void refreshView() {
        serverData=activityCallBack.getCommonData();
        if(serverData==null){
            activityCallBack.refreshCommonData(); //数据为空，通知Activity重新尝试获取数据
        }else{
            threeText.setText(serverData.getTwoData());
        }
    }

}
