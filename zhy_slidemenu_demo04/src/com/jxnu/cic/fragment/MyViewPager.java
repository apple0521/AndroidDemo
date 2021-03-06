package com.jxnu.cic.fragment;
import java.util.List;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

  // 适配器  
    public  class  MyViewPager  extends PagerAdapter {  
        private Activity mActivity; // 上下文  
        private List<View> mListViews; // 图片组  
        public MyViewPager(){  
        }  
        public MyViewPager(Activity mActivity,List<View> mListViews){  
            this.mActivity=mActivity;  
            this.mListViews=mListViews;  
        }  
        public int getCount() {  
            if (mListViews.size() == 1) {// 一张图片时不用流动  
                return mListViews.size();  
            }  
            return Integer.MAX_VALUE;  
        }  
        /** 
            返回List中的图片元素装载到控件中 
*/  
        public Object instantiateItem(View v, int i) {  
            if (((ViewPager) v).getChildCount() == mListViews.size()) {  
                ((ViewPager) v)  
                        .removeView(mListViews.get(i % mListViews.size()));  
            }  
            ((ViewPager) v).addView(mListViews.get(i % mListViews.size()), 0);  
            return mListViews.get(i % mListViews.size());  
        }  
  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == (arg1);  
        }  
  
        public void destroyItem(ViewGroup view, int i, Object object) {  
            view.removeView(mListViews.get(i%mListViews.size()));  
        }  
          
    }  