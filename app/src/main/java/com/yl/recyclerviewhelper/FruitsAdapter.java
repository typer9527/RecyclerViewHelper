package com.yl.recyclerviewhelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yl.rvhelper.SuperAdapter;
import com.yl.rvhelper.ViewHolder;

import java.util.List;

/**
 * 测试SuperAdapter
 * Created by Luke on 2017/11/7.
 */

class FruitsAdapter extends SuperAdapter<Fruit> {
    FruitsAdapter(List<Fruit> dataList) {
        super(dataList, R.layout.item_fruit, R.layout.item_header,
                R.layout.item_footer);
    }

    @Override
    protected void onConvertItemView(ViewHolder holder, final Fruit data) {
        ImageView fruitImage = holder.findViewById(R.id.fruit_image);
        TextView fruitName = holder.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(data.getImageId());
        fruitName.setText(data.getName());
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), data.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onConvertHeaderView(ViewHolder holder) {
        TextView headerTitle = holder.findViewById(R.id.title_text);
        headerTitle.setText("头布局");
    }

    @Override
    protected void onConvertFooterView(ViewHolder holder) {
        TextView footerTitle = holder.findViewById(R.id.title_text);
        TextView loadText = holder.findViewById(R.id.load_text);
        footerTitle.setText("脚布局");
        loadText.setText("正在加载...");
    }
}
