package com.bielsoft.uidesigner.item;

import android.graphics.drawable.Drawable;
import com.bielsoft.uidesigner.DesignerApp;
import com.bielsoft.uidesigner.widget.Widget;

public class WidgetGroup
{
    public String title;
    public Drawable icon;
    public Widget widget;
    public int holderType;

    public WidgetGroup(String title, Drawable icon, Widget widget)
    {
        setTitle(title);
        setIcon(icon);
        setWidget(widget);
        setHolderType(0);
    }

    public WidgetGroup(String title)
    {
        setTitle(title);
        setIcon(null);
        setWidget(new Widget(DesignerApp.getContext()));
        setHolderType(1);
    }

    public WidgetGroup()
    {}

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setIcon(Drawable icon)
    {
        this.icon = icon;
    }

    public Drawable getIcon()
    {
        return icon;
    }

    public void setWidget(Widget widget)
    {
        this.widget = widget;
    }

    public Widget getWidget()
    {
        return widget;
    }

    public void setHolderType(int holderType)
    {
        this.holderType = holderType;
    }

    public int getHolderType()
    {
        return holderType;
    }}
