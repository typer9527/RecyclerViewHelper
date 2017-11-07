package com.yl.recyclerviewhelper;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 不同节点类型的ViewHolder
 * Created by Luke on 2017/11/7.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private View itemView;

    public ViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public View getItemView() {
        return itemView;
    }
}
