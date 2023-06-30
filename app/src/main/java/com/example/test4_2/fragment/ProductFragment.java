package com.example.test4_2.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test4_2.R;
import com.example.test4_2.adapter.ListViewAdapter;
import com.example.test4_2.adapter.ProductAdapter;
import com.example.test4_2.entity.Condition;
import com.example.test4_2.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    private Spinner conditonListSpinner;
    private ListViewAdapter listViewAdapter;
    private List<Condition> conditionList;
    private GridView productGridView;
    private List<Product> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.content_product, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        conditonListSpinner = view.findViewById(R.id.product_select_condition);
        initCondList();
        listViewAdapter = new ListViewAdapter(getActivity(), conditionList);
        conditonListSpinner.setAdapter(listViewAdapter);
        productGridView = view.findViewById(R.id.product_list);
        initData();
        ProductAdapter productAdapter = new ProductAdapter(getActivity(), productList);
        productGridView.setAdapter(productAdapter);
    }

    private void initCondList() {
        conditionList = new ArrayList<>();
        Condition allCondition = new Condition();
        allCondition.setConditionIcon(R.drawable.all);
        allCondition.setConditionName("所有");
        Condition saleCondition = new Condition();
        saleCondition.setConditionIcon(R.drawable.salenum);
        saleCondition.setConditionName("热门分类");
        Condition timeCondition = new Condition();
        timeCondition.setConditionIcon(R.drawable.time);
        timeCondition.setConditionName("校内兼职");
        Condition priceCondition = new Condition();
        priceCondition.setConditionIcon(R.drawable.price);
        priceCondition.setConditionName("校外兼职");
        conditionList.add(allCondition);
        conditionList.add(saleCondition);
        conditionList.add(timeCondition);
        conditionList.add(priceCondition);
    }

    private void initData() {
        productList = new ArrayList<>();
        Product product = new Product();
        product.setImageUrlId(R.drawable.job_2);
        product.setProductName("");
        product.setProductPrice(new String("校园跑腿"));
        Product product1 = new Product();
        product1.setImageUrlId(R.drawable.job_1);
        product1.setProductName("");
        product1.setProductPrice(new String("辅导员助理"));
        Product product2 = new Product();
        product2.setImageUrlId(R.drawable.job_3);
        product2.setProductName("");
        product2.setProductPrice(new String("健身房管理员"));
        Product product3 = new Product();
        product3.setImageUrlId(R.drawable.job_4);
        product3.setProductName("");
        product3.setProductPrice(new String("大牛兼职"));
        Product product4 = new Product();
        product4.setImageUrlId(R.drawable.job_5);
        product4.setProductName("");
        product4.setProductPrice(new String("校内速运"));
        Product product5 = new Product();
        product5.setImageUrlId(R.drawable.job_6);
        product5.setProductName("");
        product5.setProductPrice(new String("代取快递"));
        productList.add(product);
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
    }
}
