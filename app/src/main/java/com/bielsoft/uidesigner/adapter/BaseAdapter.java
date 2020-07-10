package com.bielsoft.uidesigner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;

public abstract class BaseAdapter<Item> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public OnItemClickListener clickListener;
    public OnItemLongClickListener longClickListener;

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        clickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener)
    {
        longClickListener = listener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position)
    {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if (clickListener != null)
                    {
                        int pos = holder.getLayoutPosition();
                        clickListener.onItemClick(v, holder, pos);
                    }
                }
            });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v)
                {
                    if (longClickListener != null)
                    {
                        int pos = holder.getLayoutPosition() ;
                        return longClickListener.onItemLongClick(v, holder, pos);
                    }
                    return false;
                }
            });

        onBindViews(holder, position);
    }

    public abstract void onBindViews(RecyclerView.ViewHolder holder, int position);
    public abstract List<Item> getData();
    public abstract Item getItem(int positon);

    public interface OnItemClickListener
    {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public interface OnItemLongClickListener
    {
        boolean onItemLongClick(View view , RecyclerView.ViewHolder holder, int position);
    }
}
