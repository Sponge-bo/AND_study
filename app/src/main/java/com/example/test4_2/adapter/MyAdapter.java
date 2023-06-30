package com.example.test4_2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test4_2.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<String> productCategory;
    private LayoutInflater layoutInflater;
    private int selectionPosition = -1;
    public MyAdapter(List<String>productCategory, Context context){
        this.productCategory = productCategory;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return productCategory.size();
    }

    @Override
    public Object getItem(int position) {
        return productCategory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.category_list_item,null);
            Log.i("adapts","getView" + convertView);
            viewHolder.tv = convertView.findViewById(R.id.categor_titles);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(productCategory.get(position));
        if(selectionPosition == position){
            viewHolder.tv.setBackgroundColor(Color.YELLOW);
        }else{
            viewHolder.tv.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }
    public void setSelectionPosition(int position){
        this.selectionPosition = position;
    }
    class ViewHolder{
        TextView tv;
    }
}
