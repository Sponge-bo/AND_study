package com.example.test4_2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test4_2.R;
import com.example.test4_2.entity.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private List<Product> productList;
    private LayoutInflater layoutInflater;

    public ProductAdapter(Context context, List<Product> productList) {
        this.productList = productList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            Log.i("aa", "getView: "+"aa");
            convertView = layoutInflater.inflate(R.layout.category_detail_content, null);
            viewHolder = new ViewHolder();
            viewHolder.productImage = convertView.findViewById(R.id.category_product_image);
            viewHolder.productName = convertView.findViewById(R.id.category_product_name);
            viewHolder.productPrice = convertView.findViewById(R.id.category_product_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Product product = productList.get(position);
        Log.i("product", "getView: "+product.toString());
        if (product != null) {
            viewHolder.productImage.setBackgroundResource(product.getImageUrlId());
            viewHolder.productName.setText(product.getProductName());
            viewHolder.productPrice.setText(String.valueOf(product.getProductPrice()));
        }
        return convertView;
    }

    class ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;
    }
}