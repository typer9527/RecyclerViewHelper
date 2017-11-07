package com.yl.rvhelper;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * 不同节点类型的ViewHolder
 * Created by Luke on 2017/11/7.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private View itemView;
    private SparseArray<View> views;

    ViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }

    public View getItemView() {
        return itemView;
    }

    @SuppressWarnings("unchecked")
    public <V extends View> V findViewById(int id) {
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (V) view;
    }
}
