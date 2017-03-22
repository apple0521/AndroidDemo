package com.jxnu.cic.adapter;
 
import java.util.List;

import com.jxnu.cic.R;
import com.jxnu.cic.entity.Sdyw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InfoAdapter extends BaseAdapter {


    private List<Sdyw> infoList;
    private View view;
    private Context mContext;
    private ViewHolder viewHolder;

    public InfoAdapter(Context mContext, List<Sdyw> infoList) {
        this.infoList = infoList;
        this.mContext= mContext;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int position) {
        return infoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.info_list_item,
                    null);
            viewHolder = new ViewHolder();
            viewHolder.infoTitle = (TextView) view
                    .findViewById(R.id.info_list_title);
            
           viewHolder.infoTime = (TextView)view.findViewById(R.id.info_list_time);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.infoTitle.setText(infoList.get(position).getInfoTitle());
        //viewHolder.newsDesc.setText(newsList.get(position).getDesc());
        viewHolder.infoTime.setText("时间: "+infoList.get(position).getInfoTime());
        return view;
    }

    class ViewHolder{
        TextView infoTitle;
       // TextView newsDesc;
        TextView infoTime;
    }

}