package com.yl.recyclerviewhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyclerView.Adapter封装
 * Created by Luke on 2017/10/20.
 */

abstract class SuperAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;
    private List<T> dataList;
    private int headerLayoutId, footerLayoutId, itemLayoutId;
    private Context context;

    SuperAdapter(List<T> dataList, int itemLayoutId,
                 int headerLayoutId, int footerLayoutId) {
        this.dataList = dataList;
        this.headerLayoutId = headerLayoutId;
        this.footerLayoutId = footerLayoutId;
        this.itemLayoutId = itemLayoutId;
    }

    abstract void bindItemLayout(View itemView, T data);

    abstract void bindHeaderLayout(View headerView);

    abstract void bindFooterLayout(View footerView);

    T getData(int position) {
        return dataList.get(position);
    }

    Context getContext() {
        return context;
    }

    @Override
    public int getItemViewType(int position) {
        if (headerLayoutId != -1 && position == 0) {
            return TYPE_HEADER;
        }
        if (footerLayoutId != -1 && position == dataList.size() + 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        context = parent.getContext();
        switch (viewType) {
            case TYPE_HEADER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(headerLayoutId, parent, false);
                break;
            case TYPE_FOOTER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(footerLayoutId, parent, false);
                break;
            case TYPE_NORMAL:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(itemLayoutId, parent, false);
                break;
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(itemLayoutId, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                bindHeaderLayout(holder.getItemView());
                break;
            case TYPE_FOOTER:
                bindFooterLayout(holder.getItemView());
                break;
            case TYPE_NORMAL:
                int layoutPos = holder.getLayoutPosition();
                final int realPos = headerLayoutId == -1 ? layoutPos : layoutPos - 1;
                bindItemLayout(holder.getItemView(), dataList.get(realPos));
                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        if (headerLayoutId != -1 && footerLayoutId != -1) {
            return dataList.size() + 2;
        } else if (headerLayoutId != -1 || footerLayoutId != -1) {
            return dataList.size() + 1;
        } else {
            return dataList.size();
        }
    }
}
