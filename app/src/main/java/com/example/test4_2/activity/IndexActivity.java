package com.example.test4_2.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.test4_2.R;
import com.example.test4_2.fragment.IndexFragment;
import com.example.test4_2.fragment.PearsonFragment;
import com.example.test4_2.fragment.ProductFragment;
import com.example.test4_2.fragment.ShoppingCartFragment;
import com.example.test4_2.service.TimeService;

public class IndexActivity extends Activity implements View.OnClickListener {
    private IndexFragment indexFragment;
    private ProductFragment productFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private PearsonFragment pearsonFragment;
    private LinearLayout indexLine, productLine, shoppingCartLine, pearsonLine;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initIndexFragment();
        Thread thread = new Thread(runnable);
        thread.start();
    }
    private void init() {
        indexLine = findViewById(R.id.content_index);
        indexLine.setOnClickListener(this);
        productLine = findViewById(R.id.content_product);
        productLine.setOnClickListener(this);
        shoppingCartLine = findViewById(R.id.content_cart);
        shoppingCartLine.setOnClickListener(this);
        pearsonLine = findViewById(R.id.content_pearson);
        pearsonLine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_index:
                initIndexFragment();
                break;
            case R.id.content_product:
                initproductFragment();
                break;
            case R.id.content_cart:
                initshoppingCartFragment();
                break;
            case R.id.content_pearson:
                initpearsonFragment();
                break;
        }
    }
    private void initIndexFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (indexFragment == null) {
            indexFragment = new IndexFragment();
        }
        transaction.replace(R.id.main_content, indexFragment);
        transaction.commit();
    }

    private void initproductFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (productFragment == null) {
            productFragment = new ProductFragment();
        }
        transaction.replace(R.id.main_content, productFragment);
        transaction.commit();
    }

    private void initshoppingCartFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (shoppingCartFragment == null) {
            shoppingCartFragment = new ShoppingCartFragment();
        }
        transaction.replace(R.id.main_content, shoppingCartFragment);
        transaction.commit();
    }
    private void initpearsonFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (pearsonFragment == null) {
            Intent intent = IndexActivity.this.getIntent();
            Bundle bundle = intent.getExtras();
            pearsonFragment = new PearsonFragment();
            pearsonFragment.setArguments(bundle);
        }
        transaction.replace(R.id.main_content, pearsonFragment);
        transaction.commit();
    }

    Runnable runnable = () -> {
        Intent intent = new Intent(IndexActivity.this, TimeService.class);
        startService(intent);
    };

}