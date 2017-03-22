package com.jxnu.cic.fragment;

import java.util.Arrays;
import java.util.List;

import com.jxnu.cic.PersonActivity;
import com.jxnu.cic.R;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MenuLeftFragment extends Fragment
{
	private View mView;
	private ListView mCategories;
//	private List<String> mDatas = Arrays
			//.asList("聊天", "发现", "通讯录", "朋友圈", "订阅号");
	private ListAdapter mAdapter;
	private LinearLayout cehua_personal;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		if (mView == null)
		{
			initView(inflater, container);
		}
		return mView;
	}
/*	Intent intent=new Intent(MainActivity.this,PersonActivity.class);
	startActivity(intent);
	finish();*/
	private void initView(LayoutInflater inflater, ViewGroup container)
	{
		mView = inflater.inflate(R.layout.left_menu, container, false);
		cehua_personal=(LinearLayout) mView.findViewById(R.id.cehua_personal);
		cehua_personal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),PersonActivity.class);
				startActivity(intent);
			}
		});
		
	//	mAdapter = new ArrayAdapter<String>(getActivity(),
			//	android.R.layout.simple_list_item_1, mDatas);
		//mCategories.setAdapter(mAdapter);
		
	}
}
