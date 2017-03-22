package com.jxnu.cic;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.jxnu.cic.R;
import com.jxnu.cic.fragment.MainTab01;
import com.jxnu.cic.fragment.MainTab02;
import com.jxnu.cic.fragment.MainTab03;
import com.jxnu.cic.fragment.MainTab04;
import com.jxnu.cic.fragment.MenuLeftFragment;

public class MainActivity extends SlidingFragmentActivity implements OnClickListener
{

	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();

    private LinearLayout mTabShixun;
    private LinearLayout mTabShetuan;
    private LinearLayout mTabXuexi;
    private LinearLayout mTabTonggao;
    
    private ImageButton imgShixun;
    private ImageButton imgShetuan;
    private ImageButton imgXuexi;
    private ImageButton imgTonggao;
    private ImageButton imgsearch;
    
    private TextView tv_shixun;
    private TextView tv_shetuan;
    private TextView tv_xuexi;
    private TextView tv_tonggao;
    
    
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// 初始化SlideMenu
		initRightMenu();
		// 初始化ViewPager
		initViewPager();
		initEvents();

	}
	private void initEvents() {
		// TODO Auto-generated method stub
		mTabShixun.setOnClickListener(this);
		mTabShetuan.setOnClickListener(this);
		mTabXuexi.setOnClickListener(this);
		mTabTonggao.setOnClickListener(this);
		imgsearch.setOnClickListener(this);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				int currentItem =mViewPager.getCurrentItem();
				resetImg();
			    switch(currentItem){
			    case 0:
			    	imgShixun.setImageResource(R.drawable.information1);
			    	tv_shixun.setTextColor(Color.rgb(30, 144, 255)); 
			    	break;
			    case 1:
			    	imgShetuan.setImageResource(R.drawable.club1);
			    	tv_shetuan.setTextColor(Color.rgb(30, 144, 255)); 
			    	break;
			    case 2:
			    	imgXuexi.setImageResource(R.drawable.study1);
			    	tv_xuexi.setTextColor(Color.rgb(30, 144, 255)); 
			    	break;
			    case 3:
			    	imgTonggao.setImageResource(R.drawable.show1);
			    	tv_tonggao.setTextColor(Color.rgb(30, 144, 255)); 
			    	break;
			    default:
			    	break;
			    }
			}
			
		});
	}

	private void initViewPager()
	{

		mViewPager=(ViewPager) findViewById(R.id.id_viewpager);
		mTabShixun=(LinearLayout) findViewById(R.id.tab_shixun);
		mTabShetuan=(LinearLayout) findViewById(R.id.tab_shetuan);
		mTabXuexi=(LinearLayout) findViewById(R.id.tab_xuexi);
		mTabTonggao=(LinearLayout) findViewById(R.id.tab_tonggao);
		//Tabs
		imgShixun=(ImageButton) findViewById(R.id.img_shixun);
		imgShetuan=(ImageButton) findViewById(R.id.img_shetuan);
		imgXuexi=(ImageButton) findViewById(R.id.img_xuexi);
		imgTonggao=(ImageButton) findViewById(R.id.img_tonggao);
		imgsearch=(ImageButton)findViewById(R.id.imgsearch);
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		tv_shixun=(TextView)findViewById(R.id.tv_shixun);
		tv_shetuan=(TextView)findViewById(R.id.tv_shetuan);
		tv_xuexi=(TextView)findViewById(R.id.tv_xuexi);
		tv_tonggao=(TextView)findViewById(R.id.tv_tonggao);
		
		
		MainTab01 tab01 = new MainTab01();
		MainTab02 tab02 = new MainTab02();
		MainTab03 tab03 = new MainTab03();
		MainTab04 tab04 = new MainTab04();
		mFragments.add(tab01);
		mFragments.add(tab02);
		mFragments.add(tab03);
		mFragments.add(tab04);
		/**
		 * 初始化Adapter
		 */
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0)
			{
				return mFragments.get(arg0);
			}
		};
		mViewPager.setAdapter(mAdapter);
	}

	private void initRightMenu()
	{
		
		Fragment leftMenuFragment = new MenuLeftFragment();
		setBehindContentView(R.layout.left_menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT);
		// 设置触摸屏幕的模式
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		// 设置滑动菜单视图的宽度
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//		menu.setBehindWidth()
		// 设置渐入渐出效果的值
		menu.setFadeDegree(0.35f);
		// menu.setBehindScrollScale(1.0f);
		menu.setSecondaryShadowDrawable(R.drawable.shadow);
		//设置右边（二级）侧滑菜单
		//menu.setSecondaryMenu(R.layout.right_menu_frame);
		//Fragment rightMenuFragment = new MenuRightFragment();
		//getSupportFragmentManager().beginTransaction()
				//.replace(R.id.id_right_menu_frame, rightMenuFragment).commit();
	}

	public void showLeftMenu(View view)
	{
		getSlidingMenu().showMenu();
	}

	/*public void showRightMenu(View view)
	{
		getSlidingMenu().showSecondaryMenu();
	}*/
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		resetImg();
		switch(v.getId()){
		case R.id.tab_shixun:
			mViewPager.setCurrentItem(0);
			imgShixun.setImageResource(R.drawable.information1);
			tv_shixun.setTextColor(Color.rgb(30, 144, 255));
			break;
		case R.id.tab_shetuan:
			mViewPager.setCurrentItem(1);
			imgShetuan.setImageResource(R.drawable.club1);
			tv_shetuan.setTextColor(Color.rgb(30, 144, 255));
			break;
		case R.id.tab_xuexi:
			mViewPager.setCurrentItem(2);
			imgXuexi.setImageResource(R.drawable.study1);
			tv_xuexi.setTextColor(Color.rgb(30, 144, 255));
			break;
		case R.id.tab_tonggao:
			mViewPager.setCurrentItem(3);
			imgTonggao.setImageResource(R.drawable.show1);
			tv_tonggao.setTextColor(Color.rgb(30, 144, 255));
			break;
		case R.id.imgsearch:
			//Intent intent=new Intent(MainActivity.this,SearchActivity.class);
			//startActivity(intent);
			break;
		default :
			break;
		}
	}

/**
 * 将所有的图片切换为暗色
 */
private void resetImg()
{
	imgShixun.setImageResource(R.drawable.information);
	imgShetuan.setImageResource(R.drawable.club);
	imgXuexi.setImageResource(R.drawable.study);
	imgTonggao.setImageResource(R.drawable.show);
	tv_shixun.setTextColor(Color.rgb(105, 105, 105)); 
	tv_shetuan.setTextColor(Color.rgb(105, 105, 105)); 
	tv_xuexi.setTextColor(Color.rgb(105, 105, 105)); 
	tv_tonggao.setTextColor(Color.rgb(105, 105, 105)); 
	
}
}
