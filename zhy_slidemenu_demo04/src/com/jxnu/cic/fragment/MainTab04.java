package com.jxnu.cic.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jxnu.cic.MainActivity;
import com.jxnu.cic.R;
import com.jxnu.cic.activity.NewsDisplayActivity;
import com.jxnu.cic.adapter.InfoAdapter;
import com.jxnu.cic.adapter.ShowAdapter;
import com.jxnu.cic.entity.Sdtg;
import com.jxnu.cic.entity.Sdyw;
import com.jxnu.cic.view.RefreshableView;
import com.jxnu.cic.view.RefreshableView.PullToRefreshListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment; 
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;


public class MainTab04 extends Fragment
{
	private ListView show_listview;
    private SimpleAdapter simp_adapter_show;
    protected Context show_mContext;
    private MyImgScroll show_myPager; // 图片容器  
    private LinearLayout show_ovalLayout; // 圆点容器  
    private List<View> show_listViews; // 图片组  
    private RelativeLayout info_click;
    private ShowAdapter showAdapter;
    private List<Sdtg>showList;
    private Handler handler;
    RefreshableView refreshableView; 


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View showLayout = inflater.inflate(R.layout.main_tab_04, container, false);
		show_listview=(ListView)showLayout.findViewById(R.id.show_listview);
		 show_myPager = (MyImgScroll) showLayout.findViewById(R.id.show_viewpager);  
	        show_ovalLayout = (LinearLayout) showLayout.findViewById(R.id.show_view); 
	        refreshableView = (RefreshableView) showLayout.findViewById(R.id.refreshable_show_view); 
	        showList=new ArrayList<Sdtg>();
	        getSdtg();
	        new Thread(new Runnable() {
	            @Override
	            public void run() {
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			
			@Override
			public void onRefresh() {
				try {
					
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);
	}
		 
  }).start();
	        handler = new Handler(){
	            @Override
	            public void handleMessage(Message msg) {
	                if(msg.what == 1){
	                    showAdapter = new ShowAdapter(getActivity(),showList);
	                    show_listview.setAdapter(showAdapter);
	                    /*refreshableView.setOnRefreshListener(new PullToRefreshListener() {  
	                        @Override  
	                        public void onRefresh() {  
	                            try {  
	                                Thread.sleep(3000);  
	                            } catch (InterruptedException e) {  
	                                e.printStackTrace();  
	                            }  
	                            refreshableView.finishRefreshing();  
	                        }  
	                    }, 0);  */
	                    show_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	                      

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int positon, long id) {
								// TODO Auto-generated method stub
								Sdtg sdtg = showList.get(positon);
	                            Intent intent = new Intent(getActivity(),NewsDisplayActivity.class);
	                            intent.putExtra("news_url",sdtg.getShowUrl());
	                            startActivity(intent);
								
							}
	                    });
	                }
	            }
	        };
	        InitViewPager();//初始化图片  
	        //开始滚动  
	        show_myPager.start(this.getActivity(),show_listViews, 5000, show_ovalLayout,  
	                R.layout.item, R.id.ad_item_v,
	                R.drawable.imgshape, R.drawable.imgshape_un);  
		//simp_adapter=new SimpleAdapter(mContext, getData(),R.layout.item, new String[]{"pic","text"}, new int []{R.id.img,R.id.text});
		 /*simp_adapter_show = new ShowAdapter(  
                 getActivity(),           
                showList,  
               R.layout.info_list_item,  
               new String[]{"img1","title1"},  
               new int[]{R.id.info_list_title,R.id.info_list_title}  
               );   
		 
		if(show_listview==null)
          {
                  Log.d("debug","hislist null");
                  
          }
	      */
	       
		/* show_listview.setAdapter(showAdapter);
		 show_listview.setOnItemClickListener(new OnItemClickListener(){
			 
	            @Override
	            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	                    long arg3) {
	                // TODO Auto-generated method stub
	            	info_click=(RelativeLayout)arg1.findViewById(R.id.info_click);
	            	info_click.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent intent=new Intent();
							intent.setClass(getActivity(), InfoActivity.class);
							startActivity(intent);link();
						}
					});
	            }
	             
	        });*/
		return showLayout;
	}
	  @Override
	public void onStart() {  
	        show_myPager.startTimer();  
	        super.onStart();  
	    }  
	      
	    @Override
		public void onStop() {  
	        show_myPager.stopTimer();  
	        super.onStop();  
	    }  
		private void InitViewPager() {
		// TODO Auto-generated method stub
			  show_listViews = new ArrayList<View>();  
		        int[] imageResId = new int[] { R.drawable.show_list1, R.drawable.show_list2,  
		                R.drawable.show_list3, R.drawable.information_list4, R.drawable.information_list5 };  
		        for (int i = 0; i < imageResId.length; i++) {  
		            ImageView show_imageView = new ImageView(show_mContext);  
		            show_imageView.setOnClickListener(new OnClickListener() {  
		                public void onClick(View v) {// 设置图片点击事件  
		                  
		                }  
		            });  
		            show_imageView.setImageResource(imageResId[i]);  
		            show_imageView.setScaleType(ScaleType.CENTER_CROP);  
		            show_listViews.add(show_imageView);  
		        }  
	}

		//String[]arr_data={"1","1","1","1"};
	   /*  
	     */
	      //arr_adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arr_data);
	    
	
	/*private  List<? extends Map<String, ?>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
		for(int i=0;i<3;i++)
		{
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("img1", R.drawable.show_list1);
			map.put("title1","校历");
			Map<String,Object>map1=new HashMap<String,Object>();
			map1.put("img1", R.drawable.show_list2);
			map1.put("title1", "“师大感动人物投票”");
			Map<String,Object>map2=new HashMap<String,Object>();
			map2.put("img1", R.drawable.show_list3);
			map2.put("title1","2017年高层次人才招聘");
			list.add(map);
			list.add(map1);
			list.add(map2);
		}
		//Log.d("debug",""+list);
		return list;*/
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
	
	private void link() {
		// TODO Auto-generated method stub
		String url = "http://www.jxnu.edu.cn/s/2/t/690/54/86/info87174.htm"; // web address
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		startActivity(intent);
		}
	 private void getSdtg(){

	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                try{
	                  /*  //获取虎扑新闻20页的数据，网址格式为：https://voice.hupu.com/nba/第几页 + Integer.toString(i)
	                    for(int i = 1;i<=1;i++) {
	                    	Date startdate=new Date();
	                        Document doc = Jsoup.connect("https://voice.hupu.com/nba/" + Integer.toString(i)).post();
	                        System.out.println("------------------chenggoong");
	                        Elements titleLinks = doc.select("div.list_hd");    //解析来获取每条新闻的标题与链接地址
	                       // Elements descLinks = doc.select("div.list-content");//解析来获取每条新闻的简介
	                        //Elements timeLinks = doc.select("div.otherInfo");   //解析来获取每条新闻的时间与来源
	                        Date enddate=new Date();
	                        Long time1=enddate.getTime()-startdate.getTime();
	                        for(Element linkStr:titleLinks){
	                        	System.out.println("------------");
	                            String title = titleLinks.select("a").text();
	                            String uri = titleLinks.select("a").attr("href");
	                          //  String desc = descLinks.get(j).select("span").text();
	                            String time = titleLinks.select("span.other-left").select("a").text();
	                            News news = new News(title,uri,time);
	                            newsList.add(news);
	                            System.out.println("------------"+newsList);
	                        }
	                        System.out.println("使用Jsoup耗时=="+time1);
	            			System.out.println("time=====end");*/
	                	String content="";
	        			try {
	        				System.out.println("time=====start");
	        				Date startdate=new Date();
	        				 Document doc = Jsoup.connect("http://www.jxnu.edu.cn/s/2/t/690/p/30/list.htm") 
	        						  .data("query", "Java")   // 请求参数
	        						  .userAgent("I ’ m jsoup") // 设置 User-Agent 
	        						  .cookie("auth", "token") // 设置 cookie 
	        						  .timeout(3000)           // 设置连接超时时间
	        						  .post();                 // 使用 POST 方法访问 URL 

	        				Date enddate=new Date();
	        				Long time=enddate.getTime()-startdate.getTime();
	        				System.out.println("使用Jsoup耗时=="+time);
	        				System.out.println("time=====end");
	        				content=doc.toString();//获取iteye网站的源码html内容
	        				//System.out.println(doc.title());//获取iteye网站的标题
	        			} catch (IOException e) {
	        				e.printStackTrace();
	        			}
	                	String divContent="";
	                	Document doc=Jsoup.parse(content);
	                	Elements divs=doc.getElementsByClass("ColumnStyle");
	                	divContent=divs.toString();
	                	String abs="http://www.jxnu.edu.cn/s/2/t/690/p/30/list.htm";
	                	Document doc2=Jsoup.parse(divContent,abs);
	                	Elements linkStrs=doc2.getElementsByTag("tr");
	                	System.out.println("链接==="+linkStrs.size());
	                	for(Element linkStr:linkStrs){
	                	    String url=linkStr.getElementsByTag("a").attr("abs:href");
	                	    String title=linkStr.getElementsByTag("a").text();
		                	   String date=linkStr.select("td:matchesOwn(1)").text();
	                	    System.out.println("标题:"+title+" url:"+url+date);
	                	    Sdtg sdtg = new Sdtg(title,url,date);
	                	    showList.add(sdtg);
	                	    
	                    }
	                	System.out.println(showList);
	                    Message msg = new Message();
	                    msg.what = 1;
	                    handler.sendMessage(msg);

	                }catch (Exception e){
	                    e.printStackTrace();
	                }
	            }
	        }).start();
	            }
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		show_mContext = activity.getApplicationContext();

}

	}
