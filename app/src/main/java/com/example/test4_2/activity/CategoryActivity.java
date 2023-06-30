package com.example.test4_2.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.test4_2.R;
import com.example.test4_2.adapter.MyAdapter;
import com.example.test4_2.entity.Product;
import com.example.test4_2.fragment.SetDetailFragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends Activity {
    public OnChangeListener onchangedListener;
    private List<Product> productList;
    private List<String> productCategory = new ArrayList<>();
    private ListView titleList;
    private MyAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_category);
        initData();
        init();
        SetDetailFragment setDetailfragment = new SetDetailFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.category_detail,setDetailfragment);
        transaction.commit();
        titleList.setOnItemClickListener((parent, view, position, id) ->{
            adapter.setSelectionPosition(position);
            adapter.notifyDataSetInvalidated();
            if (onchangedListener != null) {
                onchangedListener.changeText(productList.get(position));
            }
        });

    }
    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.onchangedListener = onChangeListener;
    }

    public interface OnChangeListener {
        void changeText(Product product);
    }

    private void init() {
        titleList = findViewById(R.id.category_title_list);
        adapter = new MyAdapter(productCategory, CategoryActivity.this);
        titleList.setAdapter(adapter);
    }

    private void initData() {
        productList = new ArrayList<>();
        productCategory.add("橘子");
        productCategory.add("橙子");
        productCategory.add("柚子");
        Product product = new Product();
        product.setImageUrlId(R.drawable.arrow_down);
        product.setProductName("橘子");
        product.setProductPrice(new String("100"));
        Product product1 = new Product();
        product1.setImageUrlId(R.drawable.orange);
        product1.setProductName("橙子");
        product1.setProductPrice(new String("100"));
        Product product2 = new Product();
        product2.setImageUrlId(R.drawable.arrow_left);
        product2.setProductName("柚子");
        product2.setProductPrice(new String("100"));
        productList.add(product);
        productList.add(product1);
        productList.add(product2);
    }
}

