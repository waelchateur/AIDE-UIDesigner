package com.bielsoft.uidesigner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder
{
    public BaseHolder(View itemView)
    {
        super(itemView);
    }

    public abstract void onBindView(T t, BaseAdapter adapter, int position);
}
