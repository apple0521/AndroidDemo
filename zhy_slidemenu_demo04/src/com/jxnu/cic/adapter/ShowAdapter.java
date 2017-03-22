package com.jxnu.cic.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jxnu.cic.R;
import com.jxnu.cic.adapter.InfoAdapter.ViewHolder;
import com.jxnu.cic.entity.Sdtg;
import com.jxnu.cic.entity.Sdyw;

public class ShowAdapter extends BaseAdapter{
	 private List<Sdtg> showList;
	    private View view;
	    private Context mContext;
	    private ViewHolder viewHolder;

	    public ShowAdapter(Context mContext, List<Sdtg> showList) {
	        this.showList = showList;
	        this.mContext= mContext;
	    }

	    @Override
	    public int getCount() {
	        return showList.size();
	    }

	    @Override
	    public Object getItem(int position) {
	        return showList.get(position);
	    }

	    @Override
	    public long getItemId(int position) {
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        if (convertView == null) {
	            view = LayoutInflater.from(mContext).inflate(R.layout.show_list_item,
	                    null);
	            viewHolder = new ViewHolder();
	            viewHolder.showTitle = (TextView) view
	                    .findViewById(R.id.show_list_title);
	            
	           viewHolder.showTime = (TextView)view.findViewById(R.id.show_list_time);
	            view.setTag(viewHolder);
	        } else {
	            view = convertView;
	            viewHolder = (ViewHolder) view.getTag();
	        }
	        viewHolder.showTitle.setText(showList.get(position).getShowTitle());
	        //viewHolder.newsDesc.setText(newsList.get(position).getDesc());
	        viewHolder.showTime.setText("时间: "+showList.get(position).getShowTime());
	        return view;
	    }

	    class ViewHolder{
	        TextView showTitle;
	       // TextView newsDesc;
	        TextView showTime;
	    }

	}
