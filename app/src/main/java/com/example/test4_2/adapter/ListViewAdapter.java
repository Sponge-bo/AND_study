package com.example.test4_2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test4_2.R;
import com.example.test4_2.entity.Condition;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<Condition> conditionList;
    private LayoutInflater layoutInflater;
    private int selectedPosition = -1;
    private int selectColor = Color.GRAY;

    public ListViewAdapter(Context context, List<Condition> conditionList) {
        this.conditionList = conditionList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return conditionList.size();
    }

    @Override
    public Object getItem(int position) {
        return conditionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.product_condition_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.condition_icon);
            viewHolder.jiange = convertView.findViewById(R.id.image_jiange);
            viewHolder.textView = convertView.findViewById(R.id.condition_name);
            viewHolder.linearLayout = convertView.findViewById(R.id.item_bg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Condition condition = conditionList.get(position);
        if (condition != null) {
            viewHolder.imageView.setBackgroundResource(condition.getConditionIcon());
            viewHolder.textView.setText(condition.getConditionName());
            viewHolder.jiange.setBackgroundColor(Color.rgb(207, 207, 207));
            if (selectedPosition == position) {
                viewHolder.linearLayout.setBackgroundColor(selectColor);
            }

        }
        return convertView;
    }
    class ViewHolder {
        ImageView imageView, jiange;
        TextView textView;
        LinearLayout linearLayout;
    }
}

