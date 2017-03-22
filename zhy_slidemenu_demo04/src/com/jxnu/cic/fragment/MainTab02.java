package com.jxnu.cic.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxnu.cic.CommentActivity;
import com.jxnu.cic.LoginActivity;
import com.jxnu.cic.R;
import com.jxnu.cic.WelcomeActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment; 
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainTab02 extends Fragment {  
	
	private ListView club_listview;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>>dataList;
    private ArrayAdapter<String>arr_adapter;
    protected Context mContext;
    int i=0;


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View messageLayout = inflater.inflate(R.layout.main_tab_02, container, false);
		club_listview=(ListView)messageLayout.findViewById(R.id.club_listview);
		
		//simp_adapter=new SimpleAdapter(mContext, getData(),R.layout.item, new String[]{"pic","text"}, new int []{R.id.img,R.id.text});
		 
		simp_adapter = new SimpleAdapter(  
                 getActivity(),           
               getData(),  
               R.layout.club_list_item,  
               new String[]{"img","title","club","time","adress"},  
               new int[]{R.id.club_list_img,R.id.club_list_title,R.id.club_list_club_tv,R.id.club_list_time_tv,R.id.club_list_adress_tv}  
               );   
		if(club_listview==null)
          {
                  Log.d("debug","hislist null");
                  
          }
	      
		 club_listview.setAdapter(simp_adapter);
	
			
		 /*for (int i = 0; i < club_listview.getChildCount(); i++) {
		     LinearLayout layout = (LinearLayout)club_listview.getChildAt(i);// 获得子item的layout
		     final LinearLayout et = (LinearLayout) layout.findViewById(R.id.club_like1);// 从layout中获得控件,根据其id
		     final ImageButton x=(ImageButton) layout.findViewById(R.id.club_like);
		// EditText et = (EditText) layout.getChildAt(1)//或者根据位置,在这我假设TextView在前，EditText在后
		     et.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int i=0;
						if(i==0){	
							x.setImageResource(R.drawable.like2);
							
							i=1;
						}else if(i==1){
							x.setImageResource(R.drawable.like);
							i=0;
						}
						
					}
				});
		     System.out.println("the text of "+i+"'s EditText：----------->");
		}
			*/
			 
					 
		/* club_like.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int i=0;
					if(i==0){	
						Drawable like1= getResources().getDrawable(R.drawable.like2 );
						club_like.setImageDrawable(like1);
						i=1;
					}else if(i==1){
						Drawable like= getResources().getDrawable(R.drawable.like );
						club_like.setImageDrawable(like);
						i=0;
					}
					
				}
			});*/
	club_listview.setOnItemClickListener(new OnItemClickListener()
         {

            /* @Override
             public void onItemClick(AdapterView<?>parent , View view , int position , long id) 
             {
                 //想不明白了。如何补充。
                 在这里面直接打开activity就可以了 如果想传递当前item的数据可以 使用data.get(position)来获取当前item的数据
             }*/

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				LinearLayout club_like1 = (LinearLayout)view.findViewById(R.id.club_like1);
				 final ImageView club_like=(ImageView) view.findViewById(R.id.club_like);
				  final TextView 	club_tv_like=(TextView)view.findViewById(R.id.club_tv_like);
				  LinearLayout club_comment = (LinearLayout)view.findViewById(R.id.club_comment);
				System.out.println("xxxx--------");
				
				
				 club_like1.setOnClickListener(new View.OnClickListener() {		
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							
							if(i==0){	
								System.out.println("xxxx");
								club_like.setImageResource(R.drawable.like2);
								club_tv_like.setText(String.valueOf(i+1));
								i=1;
							}else{
								club_like.setImageResource(R.drawable.like);
								club_tv_like.setText(String.valueOf(i-1));
								i=0;
								
							}
							
						}
					});
				 club_comment.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(getActivity(),CommentActivity.class);
						startActivity(intent);
					}
				});
			}
              
         });
	     
		return messageLayout;
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity.getApplicationContext();
	
		//String[]arr_data={"1","1","1","1"};
	   /*  
	     */
	      //arr_adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arr_data);
	    
	}
	@Override
	public void onDestroy() {
	    super.onDestroy();
	  
	}

	private  List<? extends Map<String, ?>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("img", R.drawable.club_list1);
		map.put("title","和风日语俱乐部第七届 日语歌词大赛");
		map.put("club","2016-03-30");
		map.put("time","和风日语俱乐部");
		map.put("adress","二、三、四食堂门口");
		list.add(map);
		
		for(int i=0;i<3;i++)
		{
			Map<String,Object>map1=new HashMap<String,Object>();
			map1.put("img", R.drawable.club_list2);
			map1.put("title","V+轮舞协会招新啦！");
			map1.put("club","2015-09-25");
			map1.put("time","V+协会");
			map1.put("adress","方舟广场");
			list.add(map1);
			
		}return list;
		/*for(int i=0;i<20;i++)
		{
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("pic", R.drawable.ic_launcher);
			map.put("text","慕课网"+i);
			list.add(map);
		}
		Log.d("debug",""+list);
		return list;
		 */
	}

}
