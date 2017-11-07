package com.yl.recyclerviewhelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 测试SuperAdapter
 * Created by Luke on 2017/11/7.
 */

public class FruitsAdapter extends SuperAdapter<Fruit> {
    FruitsAdapter(List<Fruit> dataList) {
        super(dataList, R.layout.item_fruit, R.layout.item_header,
                R.layout.item_footer);
    }

    @Override
    void bindItemLayout(View itemView, Fruit data) {
        ImageView fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
        TextView fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(data.getImageId());
        fruitName.setText(data.getName());
    }

    @Override
    void bindHeaderLayout(View headerView) {
        TextView headerTitle = (TextView) headerView.findViewById(R.id.title_text);
        headerTitle.setText("头布局");
    }

    @Override
    void bindFooterLayout(View footerView) {
        TextView footerTitle = (TextView) footerView.findViewById(R.id.title_text);
        TextView loadText = (TextView) footerView.findViewById(R.id.load_text);
        footerTitle.setText("脚布局");
        loadText.setText("正在加载...");
    }
}
