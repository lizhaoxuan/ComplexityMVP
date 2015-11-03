package com.example.zhaoxuanli.mvpdemo.ui.activity;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.zhaoxuanli.mvpdemo.PageFragment;
import com.example.zhaoxuanli.mvpdemo.R;
import com.example.zhaoxuanli.mvpdemo.dto.ServerData;
import com.example.zhaoxuanli.mvpdemo.presenter.IMainAPresenter;
import com.example.zhaoxuanli.mvpdemo.presenter.MainPresenter;
import com.example.zhaoxuanli.mvpdemo.ui.adapter.MyFragmentPagerAdapter;
import com.example.zhaoxuanli.mvpdemo.ui.fragment.FourFragment;
import com.example.zhaoxuanli.mvpdemo.ui.fragment.OneFragment;
import com.example.zhaoxuanli.mvpdemo.ui.fragment.ThreeFragment;
import com.example.zhaoxuanli.mvpdemo.ui.fragment.TwoFragment;

import java.util.ArrayList;


/**
 * 1.activity启动后，从后台获取数据，并弹出toast通知
 * 2.TwoFragment 可以点击sendBut，通知activity再从后台获取数据，并弹出通知
 * 3.OneFragment 通过点击按钮从后台获取数据并显示
 * 4.TwoFragment 加载后自动从后台获取一次数据并显示
 * 5.TwoFrament 点击按钮从后台获取数据设置OneFragment的TextView
 *
 *
 */
public class MainActivity extends AppCompatActivity implements IMainAView,PageFragment.IMainCallBack {


    private IMainAPresenter mainPresenter;
    private PageFragment oneFragment;
    private PageFragment twoFragment;
    private PageFragment threeFragment;
    private PageFragment fourFragment;

    private ViewPager viewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private ActionBar mActionBar;

    private ServerData serverData;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);
        initView();  //初始化View

    }

    private void initView(){
        ArrayList<PageFragment> listFragment = new ArrayList<PageFragment>();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();

        listFragment.add(oneFragment);
        listFragment.add(twoFragment);
        listFragment.add(threeFragment);
        listFragment.add(fourFragment);

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), listFragment);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setCurrentItem(0);

        // 监听viewPager的变化，ViewPager的变化会同步到ActionBar的tab页
        MyOnPageChangeListener myOnPageChangeListener = new MyOnPageChangeListener();
        viewPager.setOnPageChangeListener(myOnPageChangeListener);

        // 设置ActionBar的tab页，并且ViewPager会随着Tab的选择变化
        mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        MyTabListener myTabListener = new MyTabListener(listFragment);
        ActionBar.Tab tab1 = mActionBar.newTab().setText("OneFragment");
        ActionBar.Tab tab2 = mActionBar.newTab().setText("TwoFragment");
        ActionBar.Tab tab3 = mActionBar.newTab().setText("ThreeFragment");
        ActionBar.Tab tab4 = mActionBar.newTab().setText("FourFragment");
        tab1.setTabListener(myTabListener);
        tab2.setTabListener(myTabListener);
        tab3.setTabListener(myTabListener);
        tab4.setTabListener(myTabListener);
        mActionBar.addTab(tab1);
        mActionBar.addTab(tab2);
        mActionBar.addTab(tab3);
        mActionBar.addTab(tab4);


        /**V调用P的进行数据获取
         * refreshData();
         * **/
        mainPresenter.getServerData();
    }


    /**--------------P 返回到 v 的 回调接口 --------------------***/
    /**
     * 返回最新数据
     * @param data
     */
    @Override
    public void setServerData(ServerData data) {
        if(data!=null){
            serverData = data;
            //通知Adapter刷新view
            myFragmentPagerAdapter.refreshData();
        }
    }

    @Override
    public boolean showLoading() {
        if(pd!=null && pd.isShowing()){
            return false;
        }else{
            pd = ProgressDialog.show(MainActivity.this, "Loading", "加载中，请稍后……");
            return true;
        }
    }

    @Override
    public void hideLoading() {
        if(pd!=null&&pd.isShowing())
            pd.dismiss();
    }
    /**---------------------------------------------------------------------------**/
    /**
     * Fragment索取数据包
     * @return
     */
    @Override
    public ServerData getCommonData() {
        if(serverData!=null)
            return serverData;
        else
            return null;
    }
    /**
     * 刷新Data操作
     * Fragment发现数据包为空时，也通过这个方法请求Activity重新尝试获取数据
     */
    @Override
    public void refreshCommonData(){
        /**V调用P的进行数据获取**/
        mainPresenter.getServerData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //通知 P 获取数据
            refreshCommonData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    class MyTabListener implements ActionBar.TabListener {

        ArrayList<PageFragment> mFragments;

        public MyTabListener(ArrayList<PageFragment> mFragments) {
            super();
            this.mFragments = mFragments;
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        }
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            mActionBar.setSelectedNavigationItem(arg0);
        }
    }



}
