package com.jxnu.cic;

import java.util.ArrayList;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class LeadActivity extends Activity {
	
	private TextView lead_pass;
	private ViewPager viewPager;
	private ArrayList<View> pageViews;
	private ImageView imageView;
	private ImageView[] imageViews;
	private ViewGroup main;
	private ViewGroup group;
	
     

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		int[] img = new int[] { R.drawable.lead_demo1, R.drawable.lead_demo2, R.drawable.lead_demo3 };
		//lead_pass= (TextView) findViewById(R.id.lead_pass);
		//LinearLayout llBottom=(LinearLayout) this.findViewById(R.id.llBottom);

		LayoutInflater inflater = getLayoutInflater();
		pageViews = new ArrayList<View>();
		
		/*Animation animotion=AnimationUtils.loadAnimation(LeadActivity.this,R.anim.animotion);
	    viewPager.startAnimation(animotion);*/
		for (int i = 0; i < img.length; i++) 
		{
			LinearLayout layout = new LinearLayout(this);
			LayoutParams ltp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			final ImageView imageView = new ImageView(this);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setImageResource(img[i]);
			//imageView.setPadding(15, 30, 15, 30);
			layout.addView(imageView, ltp);
			pageViews.add(layout);
		}
		imageViews = new ImageView[pageViews.size()];
		main = (ViewGroup) inflater.inflate(R.layout.activity_lead, null);
		group = (ViewGroup) main.findViewById(R.id.viewGroup);
		viewPager = (ViewPager) main.findViewById(R.id.id_viewpager);
		lead_pass=(TextView) main.findViewById(R.id.lead_pass);
		lead_pass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println(2);
				Intent intent=new Intent(LeadActivity.this,WelcomeActivity.class);
				startActivity(intent);
			}
		});
		
		/**
		 * 有几张图片 下面就显示几个小圆点
		 */

		for (int i = 0; i < pageViews.size(); i++) 
		{
			LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			//设置每个小圆点距离左边的间距
			margin.setMargins(10, 0, 0, 0);
			imageView = new ImageView(LeadActivity.this);
			//设置每个小圆点的宽高
			imageView.setLayoutParams(new LayoutParams(15, 15));
			imageViews[i] = imageView;
			if (i == 0) 
			{
				// 默认选中第一张图片
				imageViews[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				//其他图片都设置未选中状态
				imageViews[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			}
			group.addView(imageViews[i], margin);
		}
		setContentView(main);
		 viewPager.setPageTransformer(true,new com.jxnu.cic.view.DepthPageTransformer());
		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
	}


	
	class GuidePageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).removeView(pageViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).addView(pageViews.get(arg1));
			return pageViews.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}
	}

	// 指引页面更改事件监听器
	class GuidePageChangeListener implements OnPageChangeListener {

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
			//遍历数组让当前选中图片下的小圆点设置颜色
			  if(arg0 <  imageViews.length)   
			  {}
			         else 
			         {
			             Intent intent = new Intent(LeadActivity.this, WelcomeActivity.class); 
			             startActivity(intent);
			         }
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0]
						.setBackgroundResource(R.drawable.page_indicator_focused);

				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.page_indicator_unfocused);
				}
			}
		}
	}

	
}
