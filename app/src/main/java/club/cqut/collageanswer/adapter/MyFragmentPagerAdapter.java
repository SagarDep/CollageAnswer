package club.cqut.collageanswer.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;


import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fenghao on 2015/6/28.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

  /*  private ArrayList<Fragment> list;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }*/


    private final List<CharSequence> titles;
    private final PagerSlidingTabStrip mTabStrip;
    private final ViewPager mViewPager;
    private final ArrayList<Fragment> mFragments;
    private final Context context;

    public MyFragmentPagerAdapter(FragmentActivity activity, PagerSlidingTabStrip tabStrip, ViewPager pager) {
        super(activity.getSupportFragmentManager());
        titles = new ArrayList<CharSequence>();
        mFragments = new ArrayList<Fragment>();
        mTabStrip = tabStrip;
        mViewPager = pager;
        context = activity;
    }

    /**
     * 创建
     */
    public void build(){
        mViewPager.setAdapter(this);
        mTabStrip.setViewPager(mViewPager);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    /**
     * 设置ViewPage的缓冲页面数量
     * @param limit 前后各缓存的数量
     */
    public void setOffscreenPageLimit(int limit){
        mViewPager.setOffscreenPageLimit(limit);
    }

    /**
     * 添加页面
     * @param tabText 页面标题
     * @param fragment 页面内容
     */
    public void addTab(int tabText, Fragment fragment){
        titles.add(context.getResources().getString(tabText));
        mFragments.add(fragment);
        notifyDataSetChanged();
    }

    /**
     * 添加页面
     * @param tabText 页面标题
     * @param fragment 页面内容
     */
    public void addTab(CharSequence tabText, Fragment fragment){
        titles.add(tabText);
        mFragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
