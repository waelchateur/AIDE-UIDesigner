package com.bielsoft.uidesigner.adapter;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bielsoft.uidesigner.DesignerApp;
import com.bielsoft.uidesigner.R;
import com.bielsoft.uidesigner.activity.MainActivity;
import com.bielsoft.uidesigner.item.WidgetGroup;
import com.bielsoft.uidesigner.util.DesigerUtil;
import com.bielsoft.uidesigner.view.DesignerLayout;
import java.util.List;
import com.bielsoft.uidesigner.widget.Widget;

public class WidgetsAdapter extends BaseAdapter<WidgetGroup>
{
    public DesignerLayout dl;
    public List<WidgetGroup> list;

    public WidgetsAdapter(DesignerLayout dl, List<WidgetGroup> list)
    {
        this.dl = dl;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (viewType == 0)
        {
            return new WidgetHolder(LayoutInflater.from(DesignerApp.getContext())
                                    .inflate(R.layout.item_widget, parent, false));
        }
        else
        {
            return new TitleHolder(LayoutInflater.from(DesignerApp.getContext())
                                   .inflate(R.layout.item_widget_title, parent, false));
        }
    }

    @Override
    public int getItemCount()
    {
        return getData().size();
    }

    @Override
    public void onBindViews(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof WidgetHolder)
        {
            WidgetHolder mHolder = (WidgetHolder) holder;
            mHolder.onBindView(mHolder, this, position);
        }
        else if (holder instanceof TitleHolder)
        {
            TitleHolder mHolder = (TitleHolder) holder;
            mHolder.onBindView(mHolder, this, position);
        }
    }

    @Override
    public List<WidgetGroup> getData()
    {
        return list;
    }

    @Override
    public WidgetGroup getItem(int positon)
    {
        return getData().get(positon);
    }

    @Override
    public int getItemViewType(int position)
    {
        return getItem(position).getHolderType();
    }

    public class WidgetHolder extends BaseHolder<WidgetHolder>
    {
        public LinearLayout root;
        public ImageView icon;
        public TextView title;

        public WidgetHolder(View itemView)
        {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
        }

        @Override
        public void onBindView(WidgetHolder holder, BaseAdapter adapter, int position)
        {
            final WidgetGroup item = (WidgetGroup) adapter.getItem(position);

            root.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        ViewGroup vg = (ViewGroup) v.getParent();
                        dl.view_location.getLayoutParams().width = DesigerUtil.dip(120);
                        dl.view_location.getLayoutParams().height = DesigerUtil.dip(42);

                        if (dl == vg.getParent())
                        {
                            for (int i = 0; i < dl.getChildCount(); i++)
                            {
                                if (vg == dl.getChildAt(i))
                                {
                                    dl.defaultIndex = i;
                                }
                            }
                        }
                        else
                        {
                            dl.defaultIndex = -1;
                        }

                        if (v.startDrag(null, new View.DragShadowBuilder(v), v, 0))
                        {
                            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                            );

                            Context context = DesignerApp.getContext();

                            LinearLayout linear = new LinearLayout(context);
                            linear.setOnDragListener(dl.onDragListener);
                            linear.setLayoutParams(params);

                            try
                            {
                                linear.addView(item.getWidget());
                            }
                            catch (Exception e)
                            {
                                System.err.println("KK: " + e.getMessage());
                            }
                            dl.mWidget = linear;

                            if (dl == vg.getParent())
                            {
                                dl.removeView(vg);
                            }
                            DesigerUtil.vibrate();
                            MainActivity.drawer.closeDrawer(GravityCompat.START);
                        }
                        return true;
                    }
                });
            holder.icon.setImageDrawable(DesigerUtil.setTint(item.getIcon(),
                                                             DesignerApp.getContext().getResources().getColor(R.color.colorTint)));
            holder.title.setText(item.getTitle());
        }
    }

    public class TitleHolder extends BaseHolder<TitleHolder>
    {
        public TextView title;

        public TitleHolder(View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }

        @Override
        public void onBindView(TitleHolder holder, BaseAdapter adapter, int position)
        {
            WidgetGroup item = (WidgetGroup) adapter.getItem(position);
            holder.title.setText(item.getTitle());
        }
    }
}
